package com.jobspot.employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.jobspot.employer.form.PublishVacancyForm;
import com.jobspot.master.Vacancy;
import com.jobspot.operation.AddEmployerToSession;
import com.jobspot.operation.MyVacancy;

public class CmdUpdateVacancy extends AbstractCommand implements PostCommand{

	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdUpdateVacancy(RequestWrap request) {
		this.request = request;
	}

	public CmdUpdateVacancy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doWork() {
		IPage page = new Page();
		MyVacancy myVacancy = new MyVacancy();
		
		try{
						
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
			}
			

			else{
	
				
				Map<String, String> params = request.getParameterMap();
				PublishVacancyForm form = new PublishVacancyForm(params, new Vacancy());
				
				String employer = super.getFromSession("EMPLOYER_CODE", new AddEmployerToSession());
				
				if(myVacancy.no(employer, form.vacancyCode()))
					page.setMessage(SYSTEM_MESAGE.NOT_AUTHORIZED.value());
				

				else{
					
					form.employer(employer);
					form.validate();
					
					if(form.validated()){
						
						
						
						updateVacancy(form.title(), 
								form.jobcategory(), 
								form.town(), 
								form.basic(),  
								form.artwork(), 
								form.startdate(), 
								form.enddate(), 
								form.employer(), form.vacancyCode());
						
						
					}
					
				}

				page.setForm(form);
				
				}
	
			}
		
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
			e.printStackTrace();
		}	
		
		
		return toJson(page);
	}

	private void updateVacancy(String title, String jobcategory, String town, String basic, String artwork,
			String startdate, String enddate, String employer, String code) {

		UserTransaction trx = null;
		boolean trxComplete = false;
		
		try{
			
			trx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			trx.begin();
			
			updateVacancy(title, employer, basic, startdate, enddate, artwork, code);
			mapWithTown(code, town);
			mapWithJobCategory(code, jobcategory);
			
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



	private void updateVacancy(String title, String employer, String basic, String startdate, String enddate, String artwork, String code) {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE VACANCY SET TITLE = ?, EMPLOYER = ?,  BASIC = ?,  STARTDATE = ?,  ENDDATE = ?,  ARTWORK = ? WHERE CODE = ?";
		
		if(artwork == null)
			sql = "UPDATE VACANCY SET TITLE = ?,  EMPLOYER = ?,  BASIC = ?,  STARTDATE = ?,  ENDDATE = ?  WHERE CODE = ?";

		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, title);
			ps.setString(2, employer);
			ps.setString(3, basic);
			ps.setString(4, startdate);
			ps.setString(5, enddate);
			ps.setString(6, artwork == null?code:artwork);
			
			if(artwork!= null)
				ps.setString(7, code);
			
			ps.executeUpdate();
			
		}
		
		catch(SQLException e){
			logger.error("--> updateVacancy(): "+e);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" updateVacancy(): resource clean up: "+sqle);
			}
		}
	}

	private void mapWithJobCategory(String id, String jobcategory) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE VACANCY_JOBCATEGORY SET JOBCATEGORY = ? WHERE VACANCY = ?";
		
		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, jobcategory);
			ps.setString(2, id);
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
				logger.error(" updateVacancy(): resource clean up: "+sqle);
			}
		}
	}

	private void mapWithTown(String id, String town) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE VACANCY_TOWN SET TOWN = ? WHERE VACANCY = ?";
		
		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, town);
			ps.setString(2, id);
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
				logger.error(" updateVacancy(): resource clean up: "+sqle);
			}
		}
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdUpdateVacancy(request);
	}

}
