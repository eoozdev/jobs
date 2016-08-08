package com.eooz.common.form;

import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ValidationUtil;

public class SignUpForm implements Form {

	
	

	private boolean success;
	private boolean validated;
	
	private boolean userExists = false;
	
	private boolean validUsername = false;
	private boolean validPassword = false;
	private boolean validEmail = false;
	private boolean validRole = false;
	
	private String role;
	private String email;
	private String username;
	private String password;
	
	public SignUpForm(String email, String username, String password, String role){
		
		this.role = role;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	
	public SignUpForm(RequestWrap request) {
		
		role = request.getParameter("role");
		email = request.getParameter("email");
		username = request.getParameter("username");
		password = request.getParameter("password");
		
	}


	public String role(){
		return this.role;
	}
	
	public String email(){
		return this.email;
	}
	
	public String username(){
		return this.username;
	}
	
	public String password(){
		return this.password;
	}
	
	public void password(String value) {
		this.password = value;
	}
	
	
	public void validUsername(boolean status){
		this.validUsername = status;
	}
	
	public boolean validUsername(){
		return validUsername;
	}
	
	public void validPassword(boolean status){
		validPassword = status;
	}
	
	public boolean validPassword(){
		return validPassword;
	}
	
	public void validEmail(boolean status){
		this.validEmail = status;
	}
	
	public boolean validEmail(){
		return validEmail;
	}


	public void validRole(boolean status) {
		validRole = status;
	}
	
	public boolean validRole(){
		return this.validRole;
	}
	
	
	public void saved(boolean status){
		this.success = status;
	}
	
	public boolean saved(){
		return this.success;
	}


	public void userExists(boolean b) {
		this.userExists = b;
	}
	
	public boolean userExists(){
		return userExists;
	}


	@Override
	public boolean validated() {
		return validated;
	}


	@Override
	public void validate() {

		validated = true;
		
		if(ValidationUtil.validEmail(email)){
			validEmail(true);
			
		}
		
		else{
			validated = false;
			validEmail(false);
		}
		
		
		if(ValidationUtil.exists(username) && ValidationUtil.length(">", 255)){
			validUsername(true);
			
		}
		
		else{
			validated = false;
			validUsername(false);
		}
		
		
		if(ValidationUtil.exists(password) && ValidationUtil.length(">", 6)){
			validPassword(true);
			
		}
		
		else{
			validated = false;
			validPassword(false);
		}
		
		if(ValidationUtil.exists(role) && ValidationUtil.isRole(role)){
			validRole(true);
			
		}
		
		else{
			validated = false;
			validRole(false);
		}
		
		
	}


	@Override
	public void clear() {

		this.role = null;
		this.email = null;
		this.password = null;
		this.username = null;
		
	}






}
