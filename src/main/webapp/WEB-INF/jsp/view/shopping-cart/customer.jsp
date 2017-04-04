<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>

<title>Enter Customer Information</title>
	<link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
	<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
<body>
	<div class="slds">
		<jsp:include page="_header.jsp" />
        <nav role="navigation" aria-label="Breadcrumbs">
            <ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
                <li class="slds-breadcrumb__item slds-text-title--caps"><a href="<c:url value="/shopping-cart/cart" />">Shopping Cart</a></li>
            </ol>
        </nav>
        <div class="slds-text-heading--small slds-m-around--medium">Enter Customer Information</div>
        <div class="customer-info-container slds-m-around--medium">
            <form:form cssClass="slds-form--stacked" method="POST" modelAttribute="customerForm">
                <div class="slds-form-element">
                    <form:label cssClass="slds-form-element__label" path="name">Name:</form:label>
                    <div class="slds-form-element__control">
                        <form:input cssClass="slds-input" path="name" />
                    </div>
                    <form:errors cssClass="error-message" path="name"/>
                </div>
                <div class="slds-form-element">
                    <form:label cssClass="slds-form-element__label" path="phone">Phone:</form:label>
                    <div class="slds-form-element__control">
                        <form:input cssClass="slds-input" path="phone" />
                    </div>
                    <form:errors cssClass="error-message" path="phone" />
                </div>
                <div class="slds-form-element">
                    <form:label cssClass="slds-form-element__label" path="address">Address:</form:label>
                    <div class="slds-form-element__control">
                        <form:input cssClass="slds-input" path="address" />
                    </div>
                    <form:errors cssClass="error-message" path="address" />
                </div>
                <div class="slds-button-group slds-m-top--small" role="group">
                    <button class="slds-button slds-button--neutral" type="submit">Submit</button>
                    <button class="slds-button slds-button--neutral" type="reset" >Reset</button>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>