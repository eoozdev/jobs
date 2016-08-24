package com.jobspot.jobseeker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.command.PostCommand;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SQLConnection;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.security.SecurityUtil;
import com.jobspot.dto.Education;
import com.jobspot.jobseeker.jdbc.operations.AddJobseekerToSession;

public class CmdAddEducation extends AbstractCommand implements PostCommand {

	private ResponseWrap response;
	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public CmdAddEducation(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}

	public CmdAddEducation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
			
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
				return toJson(page);
			}
			
			String jobseekerCode = super.getFromSession("JOBSEEKER_CODE", new AddJobseekerToSession());
			
			Map<String, String> params = request.getParameterMap();
			EducationForm form = new EducationForm(params);
			
			form.validate();
			if(form.validated()){
				create(form.getEducation(), SecurityUtil.getUsername(), jobseekerCode);
			}
			
			page.setForm(form);
			
			
			}


		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		return toJson(page);
	}

	private void create(Education e, String jobseeker, String jobseekerCode) throws SQLException {
	
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO EDUCATION (PERIOD,QUALIFICATION,QUALFCATIONCODE, CRTBY, USER) VALUES(?,?,?,?,?)";
		

		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, e.getPeriod());
			ps.setString(2, e.getQualification());
			ps.setString(3, e.getQualificationCode());
			ps.setString(4, jobseeker);
			ps.setString(4, jobseekerCode);
			
			ps.executeUpdate();
			
		}
		
		catch(SQLException ex){
			logger.error("--> create(): "+ex);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" create (): resource clean up: "+sqle);
			}
		}
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdAddEducation(request, response);
	}

}

