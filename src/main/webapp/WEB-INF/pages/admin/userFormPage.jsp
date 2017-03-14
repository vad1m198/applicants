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
		    <label class="slds-form-element__label" for="first_name">First Name:</label>
		    <div class="slds-form-element__control">
		    	<form:input path="user.first_name" id="first_name" class="slds-input" name="first_name" required="true"/>
		    	<!-- input type="text" id="first_name" class="slds-input" name='first_name' value='' required/ -->
		    </div>		    
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="last_name">Last Name:</label>
		    <div class="slds-form-element__control">
		    	<!--input type="text" id="last_name" class="slds-input" name='last_name' value='' required/-->
		    	<form:input path="user.last_name" id="last_name" class="slds-input" name="last_name" required="true"/>
		    </div>		    
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="email">Email:</label>
		    <div class="slds-form-element__control">
		    	<form:input path="user.email" type="email" id="email" class="slds-input" name="email" required="true"/>
		    </div>		    
		  </div>
			<div class="slds-form-element">
				<label class="slds-form-element__label" for="last_name">Password:</label>
				<div class="slds-form-element__control">
					<!--input type="text" id="last_name" class="slds-input" name='last_name' value='' required/-->
					<form:input path="user.password" id="password" class="slds-input" name="password" required="true"/>
				</div>
			</div>

					<c:forEach items="${userFormInfo.allRoles}" var="r" varStatus="i" begin="0" >
						<form:checkbox path="allRoles[${i.index}]" id="checkbox-${i.index}" value="allRoles[${i.index}].role"/>
						<label for="checkbox-${i.index}">${r.role}</label>
					</c:forEach>

                 <div class="slds-button-group" role="group">
				  <button type="submit" class="slds-button slds-button--neutral">Submit</button>
				  <button type="reset" class="slds-button slds-button--neutral">Reset</button>
				</div>
		</form:form>
		</div>
 </div>
</body>
</html>