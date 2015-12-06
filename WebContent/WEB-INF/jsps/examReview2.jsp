<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">  
<style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/green_cup.png");
}
</style>
<title>Review Exam</title>
</head>
<body>
<div id='cssmenu'>
	<h1 align="center">Quiz Review</h1>
</div>

<c:forEach var="q" items="${requestScope.reviewQuestions}" varStatus="counter">
<br>
${counter.count}. ${q.question}<br/><br/>

<c:forEach var="option" items="${q.questionOptions}" varStatus="counter">
${counter.count}.   ${option}<br/><br/>
</c:forEach>

<font color="green">Correct Answer : ${q.correctOptionIndex+1}</font><br/>
<br>

<c:if test='${q.userSelected == -1}'>
<font color="#1334F1">Unanswered</font><br/>
</c:if>
<c:if test='${q.userSelected != -1}'>
<font color="#1334F1">You Selected : ${q.userSelected}</font><br/>
</c:if>
<br/>
<c:if test='${q.userSelected == q.correctOptionIndex+1}'>
<img height="30" width="30" src="${pageContext.request.contextPath}/images/correct.png"/>
</c:if>

<c:if test='${q.userSelected != q.correctOptionIndex+1}'>
<img height="30" width="30" src="${pageContext.request.contextPath}/images/redcross.png"/>
</c:if>
<br/>
<br/>
_____________________________________________________________________________________________________________________________________________________________________<br>
</c:forEach><br/><br/>


<div align="center">
  <a href='${pageContext.request.contextPath}/profile'>Home</a>
  </div>
</body>
</html>