<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
 
<!DOCTYPE html>
<html>
<head> 
	<title>User Information</title>
	<link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
	<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
<body>
<div class="slds">
	<jsp:include page="_adminHeader.jsp" />

	<div class="slds-text-heading--small slds-m-around--medium">Enter User Information</div>
	<div class="customer-info-container slds-m-around--medium">
	<form:form cssClass="slds-form--stacked" method="POST" modelAttribute="userFormInfo">
		<form:hidden path="user.id" />
		<div class="slds-form-element">
			<form:label cssClass="slds-form-element__label" path="user.firstName">First Name:</form:label>
			<div class="slds-form-element__control">
				<form:input cssClass="slds-input" path="user.firstName" />
			</div>
			<form:errors cssClass="error-message" path="user.firstName"/>
		</div>
		<div class="slds-form-element">
			<form:label cssClass="slds-form-element__label" path="user.secondName">Second Name:</form:label>
			<div class="slds-form-element__control">
				<form:input cssClass="slds-input" path="user.secondName" />
			</div>
			<form:errors cssClass="error-message" path="user.secondName"/>
		</div>
		<div class="slds-form-element">
			<form:label cssClass="slds-form-element__label" path="user.email">Email:</form:label>
			<div class="slds-form-element__control">
				<form:input cssClass="slds-input" path="user.email" />
			</div>
			<form:errors cssClass="error-message" path="user.email"/>
		</div>
		<%--<div class="slds-form-element">
			<form:label cssClass="slds-form-element__label" path="user.password">Password:</form:label>
			<div class="slds-form-element__control">
				<form:input cssClass="slds-input" path="user.password" />
			</div>
			<form:errors cssClass="error-message" path="user.password"/>
		</div> --%>
		<div class="roles-container">
			<form:checkboxes path="selectedRoles" items="${userFormInfo.allRoles}" itemLabel="role" itemValue="id" element="div"/>
			<form:errors path="selectedRoles" class="error-message" />
		</div>
		<div class="slds-button-group" role="group">
			<button class="slds-button slds-button--neutral" type="submit">Submit</button>
			<button class="slds-button slds-button--neutral" type="reset">Reset</button>
		</div>
	</form:form>
	</div>
</div>
</body>
</html>