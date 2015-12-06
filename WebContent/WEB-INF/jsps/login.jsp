<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   
    <title>
        Login Page
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/green_cup.png");
}
.user-icon {
	top:153px; /* Positioning fix for slide-in, got lazy to think up of simpler method. */
	background: rgba(65,72,72,0.75) url('${pageContext.request.contextPath}/images/user-icon.png') no-repeat center;	
}

.pass-icon {
	top:201px;
	background: rgba(65,72,72,0.75) url('${pageContext.request.contextPath}/images/pass-icon.png') no-repeat center;
}


</style>
</head>
<body>

<div id='cssmenu'>
	<h1 align="center">Welcome to Online Quiz Portal</h1>
</div>

<div id="wrapper" align="center">
	<form name="login-form" class="login-form" action="checkLogin" method="post">
	
		<div class="header" align="center">
		<h1>Login</h1>
		<span></span>
		</div>
	
		<div class="content" style="text-align:center">
			<input name="username" type="text" class="input username" placeholder="Username" />
			<input name="password" type="password" class="input password" placeholder="Password" />
		</div>

		<div class="footer" style="text-align:center">
			<input type="submit" name="submit" value="Login" class="button" />
		</div>
	
	</form>
</div>
<div class="gradient"></div>
<div style="position:relative;" align="center">
	<h3 align="center">${errorMessage}</h3>
</div>
<div style="position:relative;" align="center">
	Don`t have an account, click here to <a href='${pageContext.request.contextPath}/register'>Register</a>
	<br/>
</div>

</body>
</html>
