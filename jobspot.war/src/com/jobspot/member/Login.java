package com.jobspot.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.security.SecurityUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jobspot.employer.jdbc.operations.AddEmployerToSession;
import com.jobspot.jobseeker.jdbc.operations.AddJobseekerToSession;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * Servlet implementation class Login
 */
@WebServlet("/do.login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass()); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		
		try{
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			SecurityUtil.logUserIn(username, password);
			String redirectTo = getRole();
			
			json.addProperty("REDIRECT_TO",redirectTo);
			json.addProperty(SYSTEM_MESAGE.LOGGED_IN.value(), true);
		}
		
		catch(AuthenticationException authex){
			logger.error("--> doPost(): login error: "+authex);
			json.addProperty(SYSTEM_MESAGE.AUTHENTICATION_EXCEPTION.value(), true);
		}
		
		catch(Exception e){
			logger.error("--> doPost(): login error: "+e);
			json.addProperty(SYSTEM_MESAGE.UNEXPECTED_ERROR.value(), true);
		}
		
		finally{
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(json));
			writer.flush();
		}
		
	}

	private String getRole() {
		String role = "/";
		if(SecurityUtil.hasRole("JOBSEEKER")){
			new AddJobseekerToSession().add();
			role = "/member/jobseeker/index.jsp";
		}

		else if (SecurityUtil.hasRole("EMPLOYER")){
			
			new AddEmployerToSession().add();
			role = "/member/employer/index.jsp";
		}
		
		return role;
		

	}

}
