<%@page import="com.jobspot.common.Command"%>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>members area</title>
</head>
<body>


members area... 

<shiro:authenticated>
<a href = "<c:url value="/logout"/>"> log out man.</a>
</shiro:authenticated>

<br/>
<br/>

create vacancy: <a href = "/do.vacancy?cc=<%=Command.PUBLISH_VACANCY_VIEW.code()%>"> publish a vacancy.</a> 
<a href = "/member/employer/publish_vacancy.jsp"> html file</a>

<br/>
<br/>
<a href = "/do.vacancy?cc=<%=Command.EMPLOYER_VACANCY_PG.code()%>"> list me my vacancies.</a>

<br/>
<br/>

<a href = "/member/employer/update_vacancy.jsp"> edit vacancy html file</a>

<br/>
<br/>
set up my profile
<a href = "/do.employer?cc=<%=Command.EMPLOYER_PROFILE.code()%>">update my profile</a>
</body>
</html>