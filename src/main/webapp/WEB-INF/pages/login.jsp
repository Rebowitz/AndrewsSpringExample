<%@include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<li>IoC with controller/service/persistence layers</li>
			<li>Use of the Java Persistence API</li>
			<li>Abstract JPA design pattern
			<li>Restful mapping of http GET & POST verbs</li>
			<li>Spring security 3.2</li>
			<li>Password BCrypt encryption</li>
			<li>Cross Site Request Forgery token protection</li>
			<li>JUnit and JRunner test cases</li>
			<li>Spring framework and JSTL Tag Libraries</li>
			<li>Simple logging facade for Log4j</li>
			<li>Abstract DAO design pattern</li>
			<li>Data validation design pattern</li>
			<li>Data delegation design pattern</li>
			<li>HATEOAS implementation</li>
			<li>MySql database</li>
			<li>SVN source code management</li>
			<li>Maven dependency resolution and build</li>
			<li></li>
			<li></li>
			<li></li>
		</ul>


		<div class="intro4">
			<h6>Login</h6>
			<div>

				<p class="update">

					<c:if test="${not empty error}">
						<div><p id="perror">${error}</p></div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>

					<c:url value="aexample_login" var="loginUrl" />
				<form name='loginForm' action="${loginUrl}" method='POST'>

					<table>
						<tr>
							<td>User:</td>
							<td><input type='text' name='username' value=''></td>
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
				<p>
					Forgot your password? <BR>
					<a href="index.html">Click here to reset it</a>.
				</p>
			</div>


		</div>


		<div id="footer">
			<span><a href="index.html"> homepage </a> | <a
				href="mailto:rbais@rbais.com">contact </a> | <a
				href="http://validator.w3.org/check?uri=referer">xhtml </a> | <a
				href="http://jigsaw.w3.org/css-validator">css </a>| &copy; 2015
				Rob Andrews Spring Example Application - 165 Savannah Drive - Strasburg, Virginia 22657</span>
		</div>

	</div>


</body>
</html>