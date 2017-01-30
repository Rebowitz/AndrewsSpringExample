<%@include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aexample</title>
		
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
                        <th colspan="2"><spring:message code="label.form.title"></spring:message></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label>
                        <spring:message code="label.user.organizationName"></spring:message>
                        </label>
                        </td>
                        <td><form:input type="text" path="orgName" value="" size="50" maxlength="50"/></td>
                        <td><form:errors path="orgName" cssStyle="color: #ff0000;"/></td>
                    </tr>
                    <tr>
                        <td><label>
              				<spring:message code="label.user.firstName"></spring:message>
            				</label>
            			</td>
                        <td><form:input type="text" path="firstName" value="" size="15" maxlength="15"/></td>
                        <td><form:errors path="firstName" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                        <td><label>
              				<spring:message code="label.user.lastName"></spring:message>
            				</label>
            			</td>
                        <td><form:input type="text" path="lastName" value=""  size="25" maxlength="25"/></td>
                        <td><form:errors path="lastName" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
						<td><label>
              				<spring:message code="label.user.email"></spring:message>
            				</label>
            			</td>
                        <td><form:input type="text" path="email" value=""  size="25" maxlength="50"/></td>
                        <td><form:errors path="email" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                        <td><label>
              				<spring:message code="label.user.loginId"></spring:message>
            				</label>
            			</td>
                        <td><form:input pattern=".{8,15}" type="text" path="loginId" value="" size="15" maxlength="15"/></td>
                        <td><form:errors path="loginId" cssStyle="color: #ff0000;"/></td>                        
                    </tr>
                    <tr>
                       <td><label>
              				<spring:message code="label.user.password"></spring:message>
            				</label>
            			</td>
                        <td><form:input pattern=".{8,15}" path="password" value="" type="password" size="15" maxlength="15"/></td>
                        <form:errors path="password" element="div" cssStyle="color: #ff0000;"/>                        
                    </tr>
                    <tr>
                        <td><label>
              				<spring:message code="label.user.confirmPass"></spring:message>
            				</label>
            			</td>
                        <td><form:input pattern=".{8,15}" path="matchingPassword" value="" type="password" size="15" maxlength="15"/></td>
                        <td><form:errors element="div" cssStyle="color: #ff0000;"/></td>                        
                    </tr>                    
                    <tr>
                      <td><button type="submit">
          				<spring:message code="label.form.submit"></spring:message>
        				</button>
        			  </td>
                      <td><button type="reset">
                      	<spring:message code="label.form.reset"></spring:message>
                      	</button>
                      </td>
                    </tr>

                </tbody>
            </table>
      </div>      

    </form:form>
    <br>
    <a href="<c:url value="login.html" />">
        <spring:message code="label.form.loginLink"></spring:message>
    </a>

	</div>        
	
<!-- form used to be here -->

		</div>




		<div id="footer"><span><a href="index.html"> homepage </a> | <a href="mailto:rbais@rbais.com">contact </a> | <a href="http://validator.w3.org/check?uri=referer">xhtml </a> | <a href="http://jigsaw.w3.org/css-validator">css </a>|  &copy; 2017 Robert B. Andrews - 165 Savannah Drive - Strasburg, Virginia 22657</span> 
		</div>

</div>	


	</body>
</html>