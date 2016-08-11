<%-- 20160804 SAM do_redirect.jsp: redirects to the logged on member landing page --%>
<%@page import="com.jobspot.jdbc.operations.AddEmployerToSession"%>
<%@page import="com.eooz.security.SecurityUtil"%>
	
	<%
	
	if(!SecurityUtil.isNotAuthenticated()){
		
		if(SecurityUtil.hasRole("JOBSEEKER")){
			response.sendRedirect("../member/jobseeker/index.jsp");
		}

		else if (SecurityUtil.hasRole("EMPLOYER")){
			
			new AddEmployerToSession().add();
			response.sendRedirect("../member/employer/index.jsp");
		}

		else if(SecurityUtil.isNotAuthenticated()){
			response.sendRedirect("login.jsp");
		}
		return;
		
	}

	else{
		response.sendRedirect("/do.login");
		return;
	}

	
	
	%>