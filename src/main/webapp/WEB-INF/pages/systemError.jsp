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
<title>Aexample-System Error</title>

<link href="/website/resources/css/aexample.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
<span style="float: right">
	<a href="?lang=en">en</a> 
</span>

<div class="container">
		<h2>Andrews Spring Example</h2>
		<h2>SYSTEM ERROR</h2>
		
    <div style="text-align:center; margin-top:70px;font-size:110%;">
	GRRR! Darn. Crap! Of course it happened to YOU!  It's like the world went out of its way to inconvenience
	YOU.<P> 
	YOU'RE RIGHT!
	<P>
	But if you email us at $(message.emaillink) or call us at ${message.supportphonenumber }, WE will make 
	everything cool again.  We kinda need your money, so we will try -- like real hard.
	
	AExample

    </div>
</div>
</body>
</html>