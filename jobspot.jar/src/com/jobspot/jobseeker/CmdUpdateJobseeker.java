package com.jobspot.jobseeker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.command.PutCommand;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SQLConnection;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.security.SecurityUtil;
import com.jobspot.dto.Jobseeker;
import com.jobspot.jobseeker.jdbc.operations.AddJobseekerToSession;
import com.jobspot.jobseeker.jdbc.operations.IsMyProfile;

public class CmdUpdateJobseeker extends AbstractCommand implements PutCommand{

	private ResponseWrap response;
	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public CmdUpdateJobseeker(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}

	public CmdUpdateJobseeker() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		IsMyProfile isMyProfile = new IsMyProfile();
		
		try{
			
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
				return toJson(page);
			}
			
			String jobseekerCode = super.getFromSession("JOBSEEKER_CODE", new AddJobseekerToSession());
			
			if(isMyProfile.no(jobseekerCode)){
				page.setMessage(SYSTEM_MESAGE.NOT_AUTHORIZED.value());
				return toJson(page);
			}
			
			Map<String, String> params = request.getParameterMap();
			JobseekerProfile profile = new JobseekerProfile(params);
			
			if(profile.validated){
				updateJobseekerProfile(profile.getJobseeker(), SecurityUtil.getUsername());
			}
			
			page.setForm(profile);
			
			
			}


		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		return toJson(page);
	}

	private void updateJobseekerProfile(Jobseeker e, String jobseeker) throws SQLException {
	
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE JOBSEEKER SET FIRSTNAME = ?, LASTNAME = ?, DOB = ?, MODBY = ? WHERE CODE = ?";
		

		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, e.getFirstname());
			ps.setString(2, e.getLastname());
			ps.setString(3, e.getDob());
			ps.setString(4, jobseeker);
			
			ps.executeUpdate();
			
		}
		
		catch(SQLException ex){
			logger.error("--> updateJobseekerProfile(): "+ex);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" updateJobseekerProfile(): resource clean up: "+sqle);
			}
		}
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdUpdateJobseeker(request, response);
	}

	
}
