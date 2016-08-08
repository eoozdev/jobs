package com.eooz.security;

public interface IUser {

	public IUser create(String username, String password, String email);
	
	public String getEmail();
	
	public String getUsername();
	
	public String getPassword();
}
