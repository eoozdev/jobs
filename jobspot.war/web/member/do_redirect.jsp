<%-- 20160804 SAM do_redirect.jsp: redirects to the logged on member landing page --%>
<%@page import="com.eooz.security.SecurityUtil"%>
	
	<%
	

	if(SecurityUtil.hasRole("JOBSEEKER")){
		response.sendRedirect("../member/jobseeker/index.jsp");
	}

	else if (SecurityUtil.hasRole("EMPLOYER")){
		response.sendRedirect("../member/employer/index.jsp");
	}

	else if(SecurityUtil.isNotAuthenticated()){
		response.sendRedirect("login.jsp");
	}
	return;
	
	
	%>