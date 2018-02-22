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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AExample-Reset Password</title>


<link href="/website/resources/css/aexample.css" rel="stylesheet"
	type="text/css" />
<script src="/website/resources/scripts/jquery-3.2.1.js"></script>
<script src="/website/resources/scripts/jquery.validate.js"></script>
<script src="/website/resources/scripts/additional-methods.js"></script>
<script src="/website/resources/scripts/pswd_info.js"></script>

</head>
<body>
<div>
	<div id="container">
		<h2>Andrews Spring Example</h2>
		<h6>Reset Password</h6>

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
					
					
					

				<form name='forgotPassword' action="<c:url value='/user/resetPasswordToken' />" method='POST'	
					onsubmit="return validate()">

					<table>
						<tr>
							<td>Email:</td>
							<td><input type='text' name='email' value='' /></td>
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

	</div>

		<div id="footer">
			<span><a href="index.html"> homepage </a> | <a
				href="mailto:rbais@rbais.com">contact </a> | <a
				href="http://validator.w3.org/check?uri=referer">xhtml </a> | <a
				href="http://jigsaw.w3.org/css-validator">css </a>| &copy; 2015 Rob
				Andrews Spring Example Application - 165 Savannah Drive - Strasburg,
				Virginia 22657</span>
		</div>

	</div>
</body>
</html>