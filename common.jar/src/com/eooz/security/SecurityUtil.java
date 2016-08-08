/**
 * 20160804 SAM security util wrapping the apache shiro security util authentication and autherization check
 * @author your lord and savior.
 * **/
package com.eooz.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.subject.Subject;

public abstract class SecurityUtil {

	public static boolean hasRole(String roleName){
		return SecurityUtils.getSubject().hasRole(roleName);
	}
	
	public  static boolean isNotAuthenticated() throws IllegalStateException {

		Subject user = getSubject();
		return !user.isAuthenticated();
		
	}
	
	public  static boolean isNotAuthorized(String permissions) {
		// TODO: well, i won't be handling permissions right now...
		return !true;
	}
	
	public static boolean [] isAuthorizedForAll(String permissions) {
		// TODO: well, i won't be handling permissions right now...
		return null;
	}
	
	public static String encryptPassword(String plainTextPass){
		
		DefaultPasswordService  passwordService = new DefaultPasswordService();
		return  passwordService.encryptPassword(plainTextPass);
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static void logUserIn(String username, String password) throws AuthenticationException {

		Subject subject = getSubject();
		
		if(!subject.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(true);
			subject.login(token);
		}
		
	
	

		
	}
}
