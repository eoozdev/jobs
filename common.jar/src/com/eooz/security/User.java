package com.eooz.security;

public class User implements IUser{

	private String username, password, email;
	
	/**constructor**/
	public User(String username, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public IUser create(String username, String password, String email) {
		return new User(username, password, email);
	}

}
