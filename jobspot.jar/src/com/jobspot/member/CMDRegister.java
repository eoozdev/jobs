package com.jobspot.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.command.PostCommand;
import com.eooz.common.form.SignUpForm;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SQLConnection;
import com.eooz.security.SecurityUtil;

public class CMDRegister extends AbstractCommand implements PostCommand{


	private RequestWrap request;
	private ResponseWrap response;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CMDRegister( RequestWrap request, ResponseWrap response) {
		this.response = response;
		this.request = request;
	}

	

	@Override
	public String doWork() {
		
		IPage page = new Page();
		SignUpForm form = new SignUpForm(request);	
		
		
		try{
			
			form.validate(); //validate form details
			
			if(form.validated() && isNewAccount(form)){
				
				registerUser(form); //create the user and his role
				form.saved(true); //set success message
				setHref(page, form); //set the redirect url				
				SecurityUtil.logUserIn(form.username(), form.password());
				form.password(null); //clear password from form
				
				
			}
			
		}
		
		catch(SQLException sqle){
			logger.error("--> execute()"+sqle);
			page.setMessage(sqle.toString());
		}
		
		catch(Exception e){
			logger.error("--> execute()"+e);
			page.setMessage(e.toString());
		}
		

		page.setForm(form);// set the form to the page wrapper
		
		return toJson(page);
		
	
		
	}
	
	




	private boolean isNewAccount(SignUpForm form) throws SQLException {
		
		boolean exists = true;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT USERNAME FROM SYSTEMUSER WHERE USERNAME = ?";
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, form.username());
			rs = ps.executeQuery();
			
			exists = !rs.next();
			
			if(exists)
				form.userExists(true);
			
		}
		
		catch(SQLException e){
			System.out.println(e);
		}
		
		finally{
			
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		
		return exists;
	}

	
	private void registerUser(SignUpForm form)throws SQLException{
		

		String code = createUser(form);
		createUserRole(form, code);
		
	}

	
	private String createUser(SignUpForm form) throws SQLException {
		
		String code = "-1";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO SYSTEMUSER (PRIMARYEMAIL, USERNAME, PASSWORD, MASK) VALUES ( ?, ?, ?, ?)";
		
		try{
			
			String hashedPass = SecurityUtil.encryptPassword(form.password());
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, form.email());
			ps.setString(2, form.username());
			ps.setString(3, hashedPass);
			ps.setString(4, "0");
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
				code = rs.getString(1);
		}
		
		catch(SQLException e){
			System.out.println(e);
		}
		
		finally{
			
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
		
		return code;
	}
	
	
	private void createUserRole(SignUpForm form, String userCode) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO USER_ROLE (USERNAME, ROLENAME, USER, ROLE, AUTHORITY) VALUES ( ?, ?, ?, ?, ?)";
		
		try{
			
			String roleCode = Role.fromString(form.role()).getRolecode();
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, form.username());
			ps.setString(2, form.role());
			ps.setString(3, userCode);
			ps.setString(4, roleCode);
			ps.setString(5, "");
			ps.executeUpdate();
			
		}
		
		catch(SQLException e){
			logger.error("--> createUserRole(): "+e);
		}
		
		finally{
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		}
	}


	public void setHref(IPage page, SignUpForm form) {
		String role = form.role();
		
		if(role.equalsIgnoreCase(Role.JOBSEEKER.value()))
			page.setHref(Role.JOBSEEKER.onSignUpUrl());
		
		if(role.equalsIgnoreCase(Role.EMPLOYER.value()))
			page.setHref(Role.EMPLOYER.onSignUpUrl());
	}




	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CMDRegister(request, response);
	}	

	
	


	
	
}
