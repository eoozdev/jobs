
<%-- 20160802 SAM created login file --%>
<!DOCTYPE html>

<%@page import="com.jobspot.member.Role"%>
<%@page import="com.jobspot.common.Command"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

	<!--signup form-->
	<form  method="post" action="do.register?cc=<%=Command.REGISTER_USER.code()%>">
	
		sign up as a jobseeker
		<input type = "radio"  name = "role" value="<%=Role.JOBSEEKER.value()%>">
		
		sign up as an employer
		<input type = "radio"  name = "role" value="<%=Role.EMPLOYER.value()%>">
		
		Username
		<input type = "text" name = "username"/>
		
		Email
		<input type = "text" name = "email"/>
		
		Password
		<input type = "password" name = "password" />
		
		<input type = "submit"/>
	</form>
	
</body>

</html>