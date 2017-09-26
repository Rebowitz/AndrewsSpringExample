<%@include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1 class="alert alert-danger" text="${param.message[0]}">error</h1>
		<br /> <a class="btn btn-default" href="@{/registration.html}"
			text="#{label.form.loginSignUp}">signup</a>

		<div>
			<c:if="${param.expired[0]}">
				<br />
				<h1 text="${label.form.resendRegistrationToken}">resend</h1>
				<button onclick="resendToken()">resend</button>
			</c:if>

			<script
				src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
			<script>

var serverContext = [[@{/}]];
function resendToken(){
	var token = [[${param.token}]];
    $.get(serverContext + "user/resendRegistrationToken?token="+token, function(data){
        window.location.href = serverContext +"login?message=" + data.message;
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = serverContext + "emailError.html";
        }
        else{
            window.location.href = serverContext + "login?message=" + data.responseJSON.message;
        }
    });
}
$(document).ajaxStart(function() {
    $("title").html("LOADING ...");
});

</script>

		</div>
	</div>
</body>
</html>