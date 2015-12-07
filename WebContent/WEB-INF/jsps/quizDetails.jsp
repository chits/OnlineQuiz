<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<title>Quiz Instructions</title>
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
</style>
</head>
<body>
<div id='cssmenu'>
	<h1 align="center">Instructions</h1>
</div>

<div style="position:absolute;top:70px;left:10px">
Logged in as <a href="${pageContext.request.contextPath}/profile" class="button username">${sessionScope.user}</a>
</div>

<div style="position:absolute;top:70px;right:10px">
<a href='${pageContext.request.contextPath}/logout'>Logout</a>
</div>
<br><br><br>

<div style="position:relative;top:50px" align = "center">
<ul style="list-style-type:disc">
 <li><br><strong>Quiz contains ${sessionScope.totalNumberOfQuizQuestions} Multiple Choice Questions</strong></li>
 <li><br><strong>Total time for the Quiz is ${sessionScope.quizDuration} Minutes</strong></li>
 <li><br><strong>You can finish the exam at any time</strong></li>
 <li><br><strong>Read the question carefully before answering</strong></li>
 <li><br><strong>You can not go back to a question once you answer</strong></li>
 <li><br><strong>Good luck for the quiz.</strong></li>
</ul>  
<br><br><br>
</div>

<div style="position:relative;top:40px;" align = "center">
	<a href="${pageContext.request.contextPath}/exam" class="button quiz">Start Exam</a>
</div>

</body>
</html>