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
		    	<form:input path="userInfo.firstName" id="firstName" class="slds-input" required="true"/>
		    	<!-- input type="text" id="first_name" class="slds-input" name='first_name' value='' required/ -->
		    </div>		    
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="lastName">Last Name:</label>
		    <div class="slds-form-element__control">
		    	<!--input type="text" id="last_name" class="slds-input" name='last_name' value='' required/-->
		    	<form:input path="userInfo.lastName" id="lastName" class="slds-input" required="true"/>
		    </div>		    
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="email">Email:</label>
		    <div class="slds-form-element__control">
		    	<form:input path="userInfo.email" type="email" id="email" class="slds-input" required="true"/>
		    </div>		    
		  </div>
			<div class="slds-form-element">
				<label class="slds-form-element__label" for="last_name">Password:</label>
				<div class="slds-form-element__control">
					<!--input type="text" id="last_name" class="slds-input" name='last_name' value='' required/-->
					<form:input path="userInfo.password" id="password" class="slds-input" required="true"/>
				</div>
			</div>


					<!-- form:checkbox path="rolesInfo[${i.index}].role" id="checkbox-${i.index}" value="rolesInfo[${i.index}].role"/ -->

					<%--c:forEach items="${userFormInfo.rolesInfo}" var="r" varStatus="i" begin="0">						
						<form:checkbox path="rolesInfo" id="checkbox-${i.index}" value="${r}.id"/>
						<label for="checkbox-${i.index}">${r.role}</label>
					</c:forEach --%>
					
					<%--c:forEach items="${userFormInfo.rolesInfo}" var="r" varStatus="i" begin="0">						
						<form:checkbox path="rolesInfo[${i.index}]" id="checkbox-${i.index}" value="${r}"/>
						<label for="checkbox-${i.index}">${r}</label>
					</c:forEach --%>
					
					<%-- form:checkboxes items="${allRoles}" path="roles" itemLabel="name" itemValue="id" delimiter="<br/>"/--%>
					
					<form:checkboxes path="rolesInfo" items="${userFormInfo.rolesInfo}" itemLabel="role" itemValue="id" />

                 <div class="slds-button-group" role="group">
				  <button type="submit" class="slds-button slds-button--neutral">Submit</button>
				  <button type="reset" class="slds-button slds-button--neutral">Reset</button>
				</div>
		</form:form>
		</div>
 </div>
</body>
</html>