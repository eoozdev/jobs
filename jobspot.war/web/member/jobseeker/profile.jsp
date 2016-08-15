<%@page import="com.jobspot.common.FIELD_NAME"%>
<%@page import="com.jobspot.common.Command"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


Jobseeker details<br/>
Get data from: /do.jobseeker?cc=<%=Command.VIEW_MY_PROFILE.code()%>
<br/>

To Update post data to: /do.jobseeker?cc=<%=Command.UPDATE_JOBSEEKER.code()%>
FIELDS: <%=FIELD_NAME.JS_FNAME %>,  <%=FIELD_NAME.JS_LNAME %>, <%=FIELD_NAME.JS_DOB %>

<br/>

Add Work experience<br/>
Post data to: /do.jobseeker?cc=<%=Command.CREATE_JS_WORKEXPERIENCE.code()%>
<br/>

update Work experience<br/>
Post data to: /do.jobseeker?cc=<%=Command.UPDATE_JS_WORKEXPERIENCE.code()%>
<br/>


Add Education<br/>
Post data to: /do.jobseeker?cc=<%=Command.CREATE_JS_EDUCATION.code()%>
<br/>

Update Education<br/>
Post data to: /do.jobseeker?cc=<%=Command.CREATE_JS_EDUCATION.code()%>
<br/>

Add Language<br/>
Post data to: /do.jobseeker?cc=<%=Command.CREATE_JS_LANGUAGE.code()%>
<br/>

Update Language<br/>
Post data to: /do.jobseeker?cc=<%=Command.CREATE_JS_LANGUAGE.code()%>
<br/>

</body>
</html>