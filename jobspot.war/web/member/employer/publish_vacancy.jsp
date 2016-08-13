<%@page import="com.jobspot.common.FIELD_NAME"%>
<%@page import="com.jobspot.common.Command"%>

<html>
<head></head>

<body>



<form action="/do.vacancy?cc=<%=Command.PUBLISH_VACANCY.code()%>" method="POST" enctype='multipart/form-data'>


<p>TO SAM: add a date picker to select display duration. submit 'startDate' and 'endDate' with the request</p>
<p>ARTWORK url must be passed in with the request.</p>

<div>
Publish vacancy under field
<select name = "<%=FIELD_NAME.JOB_CATEGORY.value()%>">
	<option value = "1">software</option>
	<option value = "2">accounts</option>
</select>
</div>

<div>
What the job position you are offering
<input type = "text" name = "<%=FIELD_NAME.TITLE.value()%>" />
</div>

<div>
where is this job located
<select name = "town">
	<option value = "1">colombo</option>
	<option value = "2">colombo 2</option>
</select>
</div>

<div>
What is the basic salary you offer
<input type = "text" name = "<%=FIELD_NAME.BASIC.value()%>" />
</div>

<div>
For how long do you want to display the vacancy? 2 weeks cost RS 2800
<input type = "text" name = "<%=FIELD_NAME.START_DATE.value()%>" />
</div>
<div>
For how long do you want to display the vacancy? 2 weeks cost RS 2800
<input type = "text" name = "<%=FIELD_NAME.END_DATE.value()%>" />
</div>

<div>
Upload an artwork
<input type = "file" name = "<%=FIELD_NAME.ARTWORK.value()%>"/>
</div>

<div>
<input type="submit" value="submit"/>
</div>
</form>

</body>
</html>