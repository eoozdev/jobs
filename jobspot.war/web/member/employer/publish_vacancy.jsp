<%@page import="com.jobspot.common.Command"%>

<html>
<head></head>

<body>



<form action="/do.vacancy?cc=<%=Command.PUBLISH_VACANCY.code()%>" method="POST" enctype='multipart/form-data'>




<div>
Publish vacancy under field
<select name = "<%=FIELD_NAME.JOB_CATEGORY.value()%>">
	<option value = "1">software</option>
	<option value = "2">accounts</option>
</select>
</div>

<div>
What the job position you are offering
<input type = "text" name = "title" />
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
<input type = "text" name = "basicSalary" />
</div>

<div>
For how long do you want to display the vacancy? 2 weeks cost RS 2800
<input type = "text" name = "displayDuration" />
</div>


<div>
Upload an artwork
<input type = "file" name = "artwork"/>
</div>

<div>
<input type="submit" value="submit"/>
</div>
</form>

</body>
</html>