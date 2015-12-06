<%@ page language="java" contentType="text/html; charset=charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/green_cup.png");
}
.button {
	padding: 10px 15px;
	font-size: 14px;
	line-height: 100%;
	text-shadow: 0 1px rgba(0, 0, 0, 0.4);
	color: #fff;
	
	vertical-align: middle;
	text-align: center;
	cursor: pointer;
	font-weight: bold;
	transition: background 0.1s ease-in-out;
	-webkit-transition: background 0.1s ease-in-out;
	-moz-transition: background 0.1s ease-in-out;
	-ms-transition: background 0.1s ease-in-out;
	-o-transition: background 0.1s ease-in-out;
	text-shadow: 0 1px rgba(0, 0, 0, 0.3);
	color: #fff;
	-webkit-border-radius: 40px;
	-moz-border-radius: 40px;
	border-radius: 40px;
	font-family: 'Helvetica Neue', Helvetica, sans-serif;
}

.button, .button:hover, .button:active {
	outline: 0 none;
	text-decoration: none;
        color: #fff;
}

.username {
	background-color: #2ecc71;
	box-shadow: 0px 3px 0px 0px #239a55;
}

.quiz {
	background-color: #0000FF;
	box-shadow: 0px 3px 0px 0px #239a55;
}

table {
    border-collapse: collapse;
    width: 60%;
}
td{
	text-align:center;
	height: 30px;
    vertical-align: center;
}
tr:nth-child(even) {
	background-color: #f2f2f2
}
tr:nth-child(odd) {
	background-color: yellow
}
th {
    background-color: #4CAF50;
    color: white;
    height: 50px;
}
</style>
<title>Profile</title>
</head>
<body>
<div id='cssmenu'>
	<h1 align="center">Profile Page</h1>
</div>

<div style="position:relative;top:40px;" align = "center">
	<a href="${pageContext.request.contextPath}/chooseQuiz" class="button quiz">Take Quiz</a>
</div>

<div style="position:absolute;top:70px;left:10px">
Logged as <a href="#" class="button username">${sessionScope.user}</a>
</div>

<div style="position:absolute;top:70px;right:10px">
<a href='${pageContext.request.contextPath}/logout'>Logout</a>
</div>

<div style="position:relative;top:70px" align = "center">
<c:if test="${sessionScope.profileList.size() == 0}">
	 <h1 align = "center">No Quizzes taken</h1>
</c:if>
<c:if test="${sessionScope.profileList.size() > 0}">
<table border=1>
	<tr>
            <th>
              	Subject
            </th>
            <th>
                Date
          	</th>
          	<th>
                Score
          	</th>
 	</tr>
	<c:forEach items="${sessionScope.profileList}" var="profile">
	    <tr>
	        <td>
	        	<span id="lblSubject"><c:out value="${profile.getSubject()}"/></span>
	     	</td>
	        <td>
	        	<span id="lblSubject"><c:out value="${profile.getDate()}"/></span>
	       	</td>
	        <td>
	        	<span id="lblSubject"><c:out value="${profile.getScore()}"/></span>
	       	</td>
	    </tr>
	</c:forEach>
</table>
</c:if>
</div>
</body>
</html>