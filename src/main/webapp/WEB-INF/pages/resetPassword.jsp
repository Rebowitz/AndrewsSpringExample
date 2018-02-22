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

		<div id="form-div">

			<form:form id="resetPassword-form" action="/website/user/resetPassword" modelAttribute="passwordDto" method="POST" enctype="utf8">

				<div class="formdiv">
					<form:label path="email"><spring:message code="label.user.email"/></form:label>
					<form:input id="email" type="email" path="email" />
					<form:errors path="email" cssClass="valErrors" element="div" />
					<div>
						<c:if test="${not empty errorMessage}">
	  						<span class="valErrors">${errorMessage}</span>
						</c:if>
					</div>
       				<div class="valErrors"></div>  <!-- errors via jquery -->						


				<div class="formdiv">
					<form:label path="oldPassword"><spring:message code="label.user.oldPassword"/></form:label>
					<form:input id="oldPassword" type="password" path="oldPassword" />
					<form:errors path="oldPassword" cssClass="valErrors" element="div" />
       				<div class="valErrors"></div>  <!-- errors via jquery -->						
				</div>



				<div class="formdiv">
					<form:label path="password"><spring:message code="label.user.newPassword"/></form:label>
					<form:input id="password" type="password" path="password" />
					<form:errors path="password" cssClass="valErrors" element="div" />
       				<div class="valErrors"></div>  <!-- errors via jquery -->						
				</div>
					<div class="pswd_info" id="pswd_info">
						<h4 id="pswd_header" class="notverified">Password must meet the following requirements:</h4>
						
						<ul>
							<li id="letter" class="invalid">At least <strong>one
									letter</strong></li>
							<li id="capital" class="invalid">At least <strong>one
									capital letter</strong></li>
							<li id="number" class="invalid">At least <strong>one
									number</strong></li>
							<li id="special" class="invalid">At least <strong>one special 
									character</strong></li>									
							<li id="length" class="invalid">Be at least <strong>8
									characters</strong></li>
						</ul>
					</div>
					
				<div class="formdiv">
					<form:label
						path="matchingPassword"><spring:message code="label.user.confirmPass"/></form:label>
					<form:input id="matchingPassword" type="password"
						path="matchingPassword" />
					<form:errors path="matchingPassword" cssClass="valErrors" element="div" />
       				<div class="valErrors"></div>  <!-- errors via jquery -->						
				</div>
				<div class="buttondiv">
					<!-- <button type="submit" text="${label.form.submit}">Submit</button>  -->
					<input id="submit" type="submit" text="${label.form.submit}" value="submit"
						onClick="submitForm();" /> <a href="/website/login.html"
						text="${label.form.loginLink}">Login</a>
				</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />				

			</form:form>

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