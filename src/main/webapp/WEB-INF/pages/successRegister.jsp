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
<title>Aexample-Successful Registration</title>

<link href="/website/resources/css/aexample.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
<span style="float: right">
	<a href="?lang=en">en</a> 
</span>

<div class="container">
		<h2>Andrews Spring Example</h2>
		<h6>Registration Successful!</h6>
		
    <div style="text-align:center; margin-top:70px;font-size:110%;"><spring:message code="message.regSucc"/></div>
<!--    <a href="@{/login}" text="${message.login}">login</a> -->
</div>
</body>
</html>





</body>
</html>