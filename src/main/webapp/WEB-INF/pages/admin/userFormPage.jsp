<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>User Information</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="slds">
<jsp:include page="_adminHeader.jsp" />	
	<div class="slds-text-heading--medium slds-m-around--medium">Enter User Information</div>
	<div class="custome-info-container slds-m-around--medium">
		<form:form class="slds-form--stacked" method="POST" modelAttribute="userFormInfo"
       			action="${pageContext.request.contextPath}/admin/userForm">
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="firstName">First Name:</label>
		    <div class="slds-form-element__control">
		    	<form:input path="userInfo.firstName" id="firstName" class="slds-input"/>		    	
		    	<div class="slds-form-element__help"><form:errors path="userInfo.firstName" class="error-message" /></div>
		    </div>		    
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="lastName">Last Name:</label>
		    <div class="slds-form-element__control">		    	
		    	<form:input path="userInfo.lastName" id="lastName" class="slds-input"/>
		    	<div class="slds-form-element__help"><form:errors path="userInfo.lastName" class="error-message" /></div>
		    </div>		    
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="email">Email:</label>
		    <div class="slds-form-element__control">
		    	<form:input path="userInfo.email" type="email" id="email" class="slds-input"/>
		    	<div class="slds-form-element__help"><form:errors path="userInfo.email" class="error-message" /></div>
		    </div>		    
		  </div>
			<div class="slds-form-element">
				<label class="slds-form-element__label" for="password">Password:</label>
				<div class="slds-form-element__control">
					<form:input path="userInfo.password" id="password" class="slds-input"/>
					<div class="slds-form-element__help"><form:errors path="userInfo.password" class="error-message" /></div>
				</div>
			</div>
			<div class="roles-container">
				<form:checkboxes path="rolesIds" items="${userFormInfo.rolesInfo}" itemLabel="role" itemValue="id" element="div"/>
				<div class="slds-form-element__help"><form:errors path="rolesIds" class="error-message" /></div>
			</div>

                 <div class="slds-button-group" role="group">
				  <button type="submit" class="slds-button slds-button--neutral">Submit</button>
				  <button type="reset" class="slds-button slds-button--neutral">Reset</button>
				</div>
		</form:form>
		</div>
 </div>
</body>
</html>