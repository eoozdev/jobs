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

<form action = "/do.employer">

<div>
What is your company name?
<input type = "text" name = "<%=FIELD_NAME.EMPLOYER_NAME.value()%>" />
</div>

<div>
Your industry
<select name = "<%=FIELD_NAME.JOB_CATEGORY.value()%>">
	<option value = "1">software</option>
	<option value = "2">accounts</option>
</select>
</div>


<div>
What is your company type
<select name = "<%=FIELD_NAME.COMPANY_TYPE.value()%>">
	<option value = "Private">Private</option>
	<option value = "Government">Government</option>
	<option value = "Non profit organisation (NPO)">Non profit organisation (NPO)</option>
</select>
</div>

<div>
Where is your location
<select name = "<%=FIELD_NAME.TOWN.value()%>">
	<option value = "1">colombo</option>
	<option value = "2">colombo 2</option>
</select>
</div>

<div>
Your company size?
<select name = "<%=FIELD_NAME.COMPANY_SIZE.value()%>">
	<option value = "1 - 10">1 - 10</option>
	<option value = "10 - 100">10 - 100</option>
	<option value = "100 - 500">100 - 500</option>
	<option value = "100 - 500">500 - 1000</option>
</select></div>

<div>
About the company
<textarea rows="" cols="" name = "<%=FIELD_NAME.ABOUT.value()%>"></textarea>
</div>

<div> upload a logo - integrate the single file upload - SAM</div>

<div>
<input type = "submit" value = "submit" />
</div>
</form>

</body>
</html>