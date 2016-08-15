this is the jobspot index page :)




<%@page import="com.jobspot.common.Command"%>
<br/>
<br/>
<a href = "/do.login"> login to an account</a> || <a href = "/do.signup"> create and account</a>

<br/>
<br/>

Vacancy file uploader <br/>
<a href = "/util/file_uploader.jsp">Upload a file:  </a>


<div>
<a href = "/do.public?cc=<%=Command.GET_EMPLOYERS.code()%>"> list employers</a>
</div>


<div>
<a href = "/do.public?cc=<%=Command.SHOW_SEARCH_ENGINE.code()%>"> search for vacancies</a>
</div>

<div>
POST SEARCH HERE: /do.public?cc=<%=Command.SEARCH_FOR_VACANCY.code()%>&keyword=some_keyword&town=some_town&field=some_jobcategory
</div>