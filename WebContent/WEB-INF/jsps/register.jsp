<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>
        Register
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
	<h1 align="center">New User Registration</h1>
</div>

<div id="wrapper" align="center">
	<form name="login-form" class="login-form" action="checkRegister" method="post">
	
		<div class="header" align="center">
		<h1>Register </h1>
		<span></span>
		</div>
	
		<div class="content" style="text-align:center">
			<input name="username" type="text" class="input username" placeholder="Username" />
			<input name="email" type="text" class="input password" placeholder="Email" />
			<input name="password" type="password" class="input password" placeholder="Password (Minimum 6 characters)" />	
		</div>

		<div class="footer" style="text-align:center">		
		<input type="submit" class="button" name="submit" value="Register" class="register" />
		</div>
	
	</form>

</div>
<div class="gradient"></div>

</body>
</html>
