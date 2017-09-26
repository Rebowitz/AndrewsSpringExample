<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<style>
.password-verdict{
color:#000;
}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script th:src="@{/resources/pwstrength.js}"></script>
<title th:text="#{message.updatePassword}">update password</title>
</head>
<body>
<div sec:authorize="hasAuthority('CHANGE_PASSWORD_PRIVILEGE')">
    <div class="container">
        <div class="row">
            <h1 th:text="#{message.resetYourPassword}">reset</h1>
            <form >
                <br/>
                
                    <label class="col-sm-2" th:text="#{label.user.password}">password</label>
                    <span class="col-sm-5"><input class="form-control" id="password" name="newPassword" type="password" value="" /></span>
                    <div class="col-sm-12"></div>
<br/><br/>
                    <label class="col-sm-2" th:text="#{label.user.confirmPass}">confirm</label>
                    <span class="col-sm-5"><input class="form-control" id="matchPassword" type="password" value="" /></span>
                    <div id="globalError" class="col-sm-12 alert alert-danger" style="display:none" th:text="#{PasswordMatches.user}">error</div>
                   
                <div class="col-sm-12">
                <br/><br/>
                <button class="btn btn-primary" type="submit" onclick="savePass()" th:text="#{message.updatePassword}">submit</button>
                </div>
            </form>
            
        </div>
    </div>
   
<script th:inline="javascript">
/*<![CDATA[*/
var serverContext = [[@{/}]];

$(document).ready(function () {
    $('form').submit(function(event) {
        savePass(event);
    });
    
    $(":password").keyup(function(){
        if($("#password").val() != $("#matchPassword").val()){
            $("#globalError").show().html(/*[[#{PasswordMatches.user}]]*/);
        }else{
            $("#globalError").html("").hide();
        }
    });
    
    options = {
            common: {minChar:8},
            ui: {
                showVerdictsInsideProgressBar:true,
                showErrors:true,
                errorMessages:{
                      wordLength: /*[[#{error.wordLength}]]*/,
                      wordNotEmail: /*[[#{error.wordNotEmail}]]*/,
                      wordSequences: /*[[#{error.wordSequences}]]*/,
                      wordLowercase: /*[[#{error.wordLowercase}]]*/,
                      wordUppercase: /*[[#{error.wordUppercase}]]*/,
                      wordOneNumber: /*[[#{error.wordOneNumber}]]*/,
                      wordOneSpecialChar: /*[[#{error.wordOneSpecialChar}]]*/'
                    }
                }
        };
     $('#password').pwstrength(options);
});

function savePass(event){
    event.preventDefault();
    $(".alert").html("").hide();
    $(".error-list").html("");
    if($("#password").val() != $("#matchPassword").val()){
        $("#globalError").show().html(/*[[#{PasswordMatches.user}]]*/);
        return;
    }
    var formData= $('form').serialize();
    $.post(serverContext + "user/savePassword",formData ,function(data){
        window.location.href = serverContext + "login?message="+data.message;
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("InternalError") > -1){
        	window.location.href = serverContext + "login?message=" + data.responseJSON.message;
        }
        else{
            var errors = $.parseJSON(data.responseJSON.message);
            $.each( errors, function( index,item ){
                $("#globalError").show().html(item.defaultMessage);
            });
            errors = $.parseJSON(data.responseJSON.error);
            $.each( errors, function( index,item ){
                $("#globalError").show().append(item.defaultMessage+"<br/>");
            });
        }
    });
}
/*]]>*/ 

</script>    
</div>
</body>

</html>