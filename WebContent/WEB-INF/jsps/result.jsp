<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
</style>
<title>Result</title>
</head>
<body>
<div id='cssmenu'>
	<h1 align="center">Quiz Result</h1>
</div>
<div style="position:relative;top:70px" align="center">
<table border=1>
        <tr>
            <td class="head" align="left">
                <strong>Quiz :</strong>
            </td>
            <td align="right">
                <span id="lblSubject">${sessionScope.exam}</span></td>
        </tr>
        <tr>
            <td class="head" align="left">
               <strong> Starting Time :</strong>
            </td>
            <td align="right">
                <span id="lblStime">${sessionScope.started}</span></td>
        </tr>
        
              
                <tr>
            <td class="head" align="left">
               <strong>No. of Questions :</strong>
            </td>
            <td align="right">
                <span id="lblNquestions">${sessionScope.totalNumberOfQuizQuestions}</span></td>
        </tr>
        
                <tr>
            <td class="head" align="left">
                <strong>No. of correct answers :</strong>
            </td>
            <td align="right">
                <span id="lblNcans">${requestScope.result}</span></td>
        </tr>
        
    </table>
<br>
<br>

<c:if test="${requestScope.result >= 5}">
   <h3 align="center"><font color="green">Passed</font></h3>
</c:if>
<c:if test="${requestScope.result < 5}">
   <h3 align="center"><font color="red">Failed</font></h3>
</c:if>
<br><br>
<h3 align="center"><a href='${pageContext.request.contextPath}/exam/review'>Review Answers</a></h3><br>
<h3 align="center"><a href='${pageContext.request.contextPath}/chooseQuiz'>Take Another Exam</a></h3><br>
<h3 align="center"><a href='${pageContext.request.contextPath}/profile'>Back to Profile</a></h3>
</div>

</body>
</html>