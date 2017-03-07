<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<div class="slds">

	<div class="sl-login-container">
			     <!-- /login?error=true -->
     	<c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
                Login Failed!!!<br />
                Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}                 
         </div>
    	</c:if>
	   <div class="slds-text-heading--medium">Enter user name and password:</div>
	   	<form class="slds-form--stacked"  name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="name">User:</label>
		    <div class="slds-form-element__control">
		      <input type="email" id="name" class="slds-input" name='username' value='' required/>
		    </div>
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="password">Password:</label>
		    <div class="slds-form-element__control">
		      <input type='password' name='password' id="password" class="slds-input" required/>
		    </div>
		  </div>
		  <div class="slds-form-element">
		    <button name="submit" type="submit" value="submit" class="slds-button slds-button--brand">Submit</button>
		  </div>
		</form>
	</div>
</div>
</body>
</html>