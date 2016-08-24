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
import com.jobspot.dto.Language;
import com.jobspot.jobseeker.jdbc.operations.AddJobseekerToSession;
import com.jobspot.jobseeker.jdbc.operations.IsMyLangauge;

public class CmdUpdateLanguage extends AbstractCommand implements PutCommand {

	
	private ResponseWrap response;
	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public CmdUpdateLanguage(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}

	public CmdUpdateLanguage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		IsMyLangauge isMine = new IsMyLangauge();
		
		try{
			
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
				return toJson(page);
			}
			
			String jobseekerCode = super.getFromSession("JOBSEEKER_CODE", new AddJobseekerToSession());
			String code = request.getParameter("code");
			
			if(isMine.no(jobseekerCode, code)){
				page.setMessage(SYSTEM_MESAGE.NOT_AUTHORIZED.value());
				return toJson(page);
			}
			
			Map<String, String> params = request.getParameterMap();
			LanguageForm form = new LanguageForm(params);
			form.validate();
			if(form.validated()){
				update(form.getLanguage(), SecurityUtil.getUsername(), code);
			}
			
			page.setForm(form);
			
			
			}


		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		return toJson(page);
	}

	private void update(Language e, String jobseeker, String code) throws SQLException {
	
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE LANGUAGE SET NAME = ?, PROFICIENCY = ?, MODBY = ? WHERE CODE = ?";
		

		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, e.getName());
			ps.setString(2, e.getProficiency()); 
			ps.setString(3, jobseeker);
			ps.setString(4, code);
			
			ps.executeUpdate();
			
		}
		
		catch(SQLException ex){
			logger.error("--> update(): "+ex);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" update(): resource clean up: "+sqle);
			}
		}
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdUpdateLanguage(request, response);
	}

	

}
