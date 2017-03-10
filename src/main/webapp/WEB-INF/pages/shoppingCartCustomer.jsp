<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Enter Customer Information</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="slds">
<jsp:include page="_header.jsp" />
	<nav role="navigation" aria-label="Breadcrumbs">
		<ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
			<li class="slds-breadcrumb__item slds-text-title--caps">
				<a href="${pageContext.request.contextPath}/shopping-cart/productList">Back to product list</a>
			</li>
		</ol>
	</nav>
	<div class="slds-text-heading--medium slds-m-around--medium">Enter Customer Information</div>
	<div class="custome-info-container slds-m-around--medium">
		<form:form class="slds-form--stacked" method="POST" modelAttribute="customerForm"
       			action="${pageContext.request.contextPath}/shopping-cart/shoppingCartCustomer">
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="name">Name:</label>
		    <div class="slds-form-element__control">
		    	<form:input path="name" id="name" class="slds-input" name="name" required="true"/>		      
		    </div>
		    <div id="error-message" class="slds-form-element__help"><form:errors path="name" class="error-message" /></div>
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="email">Email:</label>
		    <div class="slds-form-element__control">		      
		      <form:input path="email"  id="email" class="slds-input" type="email" required="true"/>	
		    </div>
		    <div id="error-message" class="slds-form-element__help"><form:errors path="email" class="error-message" /></div>
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="phone">Phone:</label>
		    <div class="slds-form-element__control">		      
		      <form:input path="phone"  id="phone" class="slds-input" type="text" required="true"/>	
		    </div>
		    <div id="error-message" class="slds-form-element__help"><form:errors path="phone" class="error-message" /></div>
		  </div>
		  <div class="slds-form-element">
		    <label class="slds-form-element__label" for="address">Address:</label>
		    <div class="slds-form-element__control">		      
		      <form:input path="address" id="address" class="slds-input" type="text" required="true"/>	
		    </div>
		    <div id="error-message" class="slds-form-element__help"><form:errors path="address" class="error-message" /></div>
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