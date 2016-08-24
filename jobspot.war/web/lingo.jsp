<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

	<!--login form-->
	<form  method="post" action="/do.login">
		<input type = "text" name = "username"/>
		<input type = "password" name = "password" />
		<input type = "submit"/>
	</form>
	
	<%
	String error = (String) request.getAttribute("loginFailed");
	
	if(error != null){
	%>
	login attempt failed: <%=error%>
	<%} %>
	
</body>

</html>