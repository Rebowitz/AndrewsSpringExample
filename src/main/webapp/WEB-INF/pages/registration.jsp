<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Orgify</title>
		
		<link href="/website/resources/css/aexample.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- <script src="orgify.js"></script>  -->
	

	</head>
	<body>
<!-- DO NOT MOVE! The following AllWebMenus linking code section must always be placed right AFTER the BODY tag-->
<!-- ******** BEGIN ALLWEBMENUS CODE FOR menu ******** -->
<script type="text/javascript">var MenuLinkedBy="AllWebMenus [4]",awmMenuName="menu",awmBN="908";</script><script charset="UTF-8" src="/website/resources/scripts/menu.js" type="text/javascript"></script><script type="text/javascript">awmBuildMenu();</script>
<!-- ******** END ALLWEBMENUS CODE FOR menu ******** -->

<!-- -->
    

        

<div id="container">


	<div id="css-row2">
<div id="registration">	
<form:form id="registrationForm" method="post" action="registration" modelAttribute="registrationBean">


<div id="form-div">

            <table border="1" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Organization Name</td>
                        <td><input type="text" name="orgName" value="" /></td>
                        <td><form:errors path="orgName" cssStyle="color: #ff0000;"/></td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="firstName" value="" /></td>
                        <td><form:errors path="firstName" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="lastName" value="" /></td>
                        <td><form:errors path="lastName" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="" /></td>
                        <td><form:errors path="email" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                        <td>Login Id</td>
                        <td><input type="text" name="loginId" value="" /></td>
                        <td><form:errors path="loginId" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password"/></td>
                        <td><form:errors path="password" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                        <td>Confirm Password</td>
                        <td><input type="password" name="confirmPassword"/></td>
                        <td><form:errors path="confirmPassword" cssStyle="color: #ff0000;"/></td>                        
                    </tr>                    
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>

                </tbody>
            </table>
      </div>      
        </form:form>

	</div>        
	
<!-- form used to be here -->

		</div>




		<div id="footer"><span><a href="index.html"> homepage </a> | <a href="mailto:rbais@rbais.com">contact </a> | <a href="http://validator.w3.org/check?uri=referer">xhtml </a> | <a href="http://jigsaw.w3.org/css-validator">css </a>|  &copy; 2015 Orgify - 165 Savannah Drive - Strasburg, Virginia 22657</span> 
		</div>

</div>	


	</body>
</html>