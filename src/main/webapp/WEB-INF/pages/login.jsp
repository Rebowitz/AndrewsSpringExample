<%@include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Andrews Spring Example</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<spring:url value="/resources/css/aexample.css" var="aexampleCss" />
<spring:url value="/resources/scripts/menu.js" var="aexampleMenu" />

<link href="${aexampleCss}" rel="stylesheet" type="text/css" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

</head>
<body>


	<div id="container">
		<h2>Andrews Spring Example</h2>	
		
		<p>Coding example that uses the following:
		<ul>
			<li>Spring 4 with java based configuration and annotations</li>
			<li>Spring Boot 1.5.0 framework</li>
			<li>Spring security 3.2</li>
			<li>Use of sessions, timeouts and remember me</li>
			<li>Standard JSP with CSS managed content</li>
			<li>JQuery 3.2.1 and validate libraries</li>			
			<li>IoC with controller/service/persistence layers</li>
			<li>Java Persistence API(JPA) with Hibernate support</li>
			<li>Restful mapping of http GET & POST verbs</li>
			<li>JUnit and JRunner test cases</li>
			<li>Slf4j via Logback</li>
			<li>Repository persistence design pattern</li>
			<li>Custom HTML Email Sender class with JSON configuration<li>
			<lI>Jackson based JSON library usage</li>
			<li>JSON based email configuration file</li>
			<li>Token based authentication and new password emails<li>
			<li>Custom Validation Annotations-Email, Password, Model objects</li>
			<li>Jasypt encrypted passwords<li>
			<li>Aspect oriented logging</li>
			<li>Event publishers and listeners</li>
			<li>Task pattern based expired tokens cleanup</li>
			<li>Data validation design pattern</li>
			<li>Data delegation design pattern</li>
			<li>HATEOAS implementation</li>
			<li>MySql database</li>
			<li>SVN and GitHub source code management</li>
			<li>Maven dependency resolution and build</li>
			<li>Integration with Artifactory archive</li>
			<li></li>
			<li></li>
		</ul>


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
		
			<div class="login-help">
				<a href="/resetPassword.html">Reset Password</a>
			</div>
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