package com.jobspot.employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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
import com.jobspot.dto.Vacancy;
import com.jobspot.employer.jdbc.operations.AddEmployerToSession;
import com.jobspot.employer.jdbc.operations.IsMyVacancy;

public class CmdPublishVacancy extends AbstractCommand implements PostCommand {

	
	private RequestWrap request;
	private ResponseWrap response;
	private Logger logger = LoggerFactory.getLogger(CmdPublishVacancy.class);
	
	


	//default constructor
	public CmdPublishVacancy(){
		logger.info("--> default construction of command.");
	}

	


	public CmdPublishVacancy(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}


	@Override
	//the core of the logic takes place in this magical method
	public String doWork() {
		
		IPage page = new Page();
		
		try{
						
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
			}
			
			String employer = super.getFromSession("EMPLOYER_CODE", new AddEmployerToSession());
			
			if(employer == null){
				new AddEmployerToSession().add();
				employer = SecurityUtil.getFromSession("EMPLOYER_CODE");
			}
			
			else{
	
				
				Map<String, String> params = request.getParameterMap();
				PublishVacancyForm form = new PublishVacancyForm(params, new Vacancy());
				form.employer(employer);
				form.validate();
				
				if(form.validated()){
					
					createVacancy(form.title(), 
							form.jobcategory(), 
							form.town(), 
							form.basic(),  
							form.artwork(), 
							form.startdate(), 
							form.enddate(), 
							form.employer());
					
					
				}
				

				page.setForm(form);
				
				}
	
			}
		
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
		}

		return toJson(page);
	}





	private void createVacancy(String title, String jobcategory, String town, String basic, String artwork, String startdate, String enddate, String employer) {

		UserTransaction trx = null;
		boolean trxComplete = false;
		
		try{
			
			trx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			trx.begin();
			
			int id = insertVacancy(title, employer, basic, startdate, enddate, artwork);
			mapWithTown(id, town);
			mapWithJobCategory(id, jobcategory);
			
			trx.commit();
			trxComplete = true;
		}
		
		catch(Exception e){
			logger.error("--> createVacancy(): ERR: "+e);
		}
		
		if(!trxComplete){
			try {
				trx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e) {
				logger.error("--> createVacancy(): transaction failed: "+e);
			}
			
		}
			
		
	}


	private int insertVacancy(String title, String employer, String basic, String startdate, String enddate, String artwork) {
	
		int code = -1;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO VACANCY (TITLE, EMPLOYER, BASIC, STARTDATE, ENDDATE, ARTWORK) VALUES(?,?,?,?,?,?)";
		
		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, title);
			ps.setString(2, employer);
			ps.setString(3, basic);
			ps.setString(4, startdate);
			ps.setString(5, enddate);
			ps.setString(6, artwork);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
				code = rs.getInt(1);
		}
		
		catch(SQLException e){
			logger.error("--> insertVacancy(): "+e);
		}
		
		finally{
			
			try{
				
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" insertVacancy(): resource clean up: "+sqle);
			}
		}
		
		return code;
	}


	private void mapWithTown(int id, String town) {
	
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO VACANCY_TOWN (VACANCY, TOWN) VALUES(?,?)";
		
		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setString(2, town);
			ps.executeUpdate();
			
		}
		
		catch(SQLException e){
			logger.error("--> mapWithTown(): "+e);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" mapWithTown(): resource clean up: "+sqle);
			}
		}
	}


	private void mapWithJobCategory(int id, String jobcategory) {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO VACANCY_JOBCATEGORY (VACANCY, JOBCATEGORY) VALUES(?,?)";
		
		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setString(2, jobcategory);
			ps.executeUpdate();
			
		}
		
		catch(SQLException e){
			logger.error("--> mapWithJobCategory(): "+e);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" mapWithJobCategory(): resource clean up: "+sqle);
			}
		}
	}


	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdPublishVacancy(request, response);
	}

}
