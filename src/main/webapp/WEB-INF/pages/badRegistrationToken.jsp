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
<title>Aexample-Invalid Registration Token!</title>

<link href="/website/resources/css/aexample.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<span style="float: right"> <a href="?lang=en">en</a>
	</span>

	<h2 style="text-align: center;">Andrews Spring Example</h2>
	<h6 style="text-align: center;">Invalid Registration Token!</h6>

	<div style="text-align: center; margin-top: 70px; font-size: 110%;">
		<spring:message code="message.invalidToken" />
	</div>

	<div class="intro4">
		<h6>Request New Registration Token</h6>
		<div>

			<button onclick="resendToken()"
				th:text="#{label.form.resendRegistrationToken}">resend</button>

			<script src="jquery.min.js"></script>
			<script type="text/javascript">
 
	var serverContext = [[@{/}]];
	 
	function resendToken(){
	    $.get(serverContext + "user/resendRegistrationToken?token=" + token, 
	      function(data){
	            window.location.href = 
	              serverContext +"login.html?message=" + data.message;
	    })
	    .fail(function(data) {
	        if(data.responseJSON.error.indexOf("MailError") > -1) {
	            window.location.href = serverContext + "emailError.html";
	        }
	        else {
	            window.location.href = 
	              serverContext + "login.html?message=" + data.responseJSON.message;
	        }
	    });
	}
</script>












			<p class="update">

				<c:if test="${not empty error}">
					<div>
						<p id="perror">${error}</p>
					</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
			<form name='loginForm'
				action="<c:url value='/j_spring_security_check' />" method='POST'
				onsubmit="return validate()">

				<table>
					<tr>
						<td>Email:</td>
						<td><input type='text' name='username' value='' /></td>
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


</body>
</html>