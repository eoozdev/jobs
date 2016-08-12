package com.jobspot.employer;

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
import com.jobspot.dto.Employer;
import com.jobspot.employer.form.EmployerProfile;
import com.jobspot.employer.jdbc.operations.AddEmployerToSession;
import com.jobspot.employer.jdbc.operations.IsMyProfile;

public class CmdUpdateEmployerProfile extends AbstractCommand implements PostCommand{

	private ResponseWrap response;
	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdUpdateEmployerProfile(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}

	public CmdUpdateEmployerProfile() {
		logger.info("--> default construction of command.");
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		IsMyProfile isMyProfile = new IsMyProfile();
		
		try{
						
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
			}
			

			else{
	
				
				Map<String, String> params = request.getParameterMap();
				EmployerProfile profile = new EmployerProfile(params);
				Employer e = profile.getEmployer();
				
				String employer = super.getFromSession("EMPLOYER_CODE", new AddEmployerToSession());
				e.setCode(employer);
				if(isMyProfile.no(employer))
					page.setMessage(SYSTEM_MESAGE.NOT_AUTHORIZED.value());
				

				else{


					profile.validate();
					
					if(profile.validated()){
						
						updateProfile(e);
						page.setMessage(SYSTEM_MESAGE.SUCCESSFUL.value());
						
						
					}
					
				}

				page.setForm(profile);
				
				}
	
			}
		
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
			e.printStackTrace();
		}	
		
		
		

		
		return toJson(page);
	}

	private void updateProfile(Employer e)throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "";
		
		if(e.getLogo() == null)
			sql = "UPDATE EMPLOYER SET NAME = ?, TOWN = ?, INDUSTRY = ?, COMPANYSIZE = ?, ABOUT = ?, TYPE = ? WHERE CODE = ?";
		else
			sql = "UPDATE EMPLOYER SET NAME = ?, TOWN = ?, INDUSTRY = ?, COMPANYSIZE = ?, ABOUT = ?, TYPE = ?, LOGO = ? WHERE CODE = ?";
		

		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setString(2, e.getTown());
			ps.setString(3, e.getIndustry());
			ps.setString(4, e.getCompanySize());
			ps.setString(5, e.getAbout());
			ps.setString(6, e.getType());
			
			ps.setString(7, e.getLogo() == null? e.getCode():e.getLogo());
			if(e.getLogo() != null)
			ps.setString(8, e.getCode());
			
			ps.executeUpdate();
			
		}
		
		catch(SQLException ex){
			logger.error("--> updateProfile(): "+ex);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" updateProfile(): resource clean up: "+sqle);
			}
		}
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdUpdateEmployerProfile(request, response);
	}

}
