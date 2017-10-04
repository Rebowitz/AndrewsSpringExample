<%@include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Aexample-Resend Registration Token</title>

<link href="/website/resources/css/aexample.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
<span style="float: right">
	<a href="?lang=en">en</a> 
</span>

<div class="container">
		<h2 style="text-align:center;">Andrews Spring Example</h2>
		<h6 style="text-align:center;">Resend Registration Token</h6>
	<div style="text-align:center; margin-top:70px;font-size:110%;">
	Your account is not enabled in our database. To activate your account, you must click on the button in your
	confirmation email. Your confirmation will then be entered into our database.
	</div>	
		
    <div style="text-align:center; margin-top:70px;font-size:110%;"><spring:message code="message.resendToken"/></div>
 <!--  <a href="@{/login}" text="${message.login}">login</a>  -->
 
 		<div class="intro4">
			<h6>Login</h6>
			<div>

				<p class="update">

					<c:if test="${not empty error}">
						<div>
							<p id="perror">${error}</p>
						</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>

				<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'	
					onsubmit="return validate()">

					<table>
						<tr>
							<td>Email:</td>
							<td><input type='text' name='email' value='' /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td colspan='2'><input name="submit" type="submit"
								value="submit" /></td>
						</tr>
					</table>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>
			<c:if test="${param.error != null}">
				<div>${session[SPRING_SECURITY_LAST_EXCEPTION]}</div>
			</c:if>
			
			<div class="login-help">
				<a href="@{/resetPassword.html}">${message.resetPassword}</a>
			</div>
		</div>
 
</div>
</body>
</html>