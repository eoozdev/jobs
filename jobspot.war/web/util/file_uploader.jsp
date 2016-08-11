<%@page import="com.jobspot.common.Command"%>
<%@page import="com.jobspot.employer.form.FIELD_NAME"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<form action="/do.vupload?cc=<%=Command.PUBLISH_VACANCY.code()%>" method="POST" enctype='multipart/form-data'>



<div>
Upload an artwork
<input type = "file" name = "<%=FIELD_NAME.ARTWORK.value()%>"/>
</div>
<div>
<input type = "text" name = "<%=FIELD_NAME.EMPLOYER.value()%>" value = "1"/>
</div>
<div>
<input type="submit" value="submit"/>
</div>
</form>

</body>
</html>