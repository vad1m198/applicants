<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<title>Answers</title>
    <link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
<body>
<div class="slds">
	<jsp:include page="_header.jsp" />
	<nav role="navigation" aria-label="Breadcrumbs">
        <ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
            <li class="slds-breadcrumb__item slds-text-title--caps"><a href="<c:url value="/shopping-cart/list" />">Back to product list</a></li>
        </ol>
    </nav>
    <div class="slds-text-heading--medium slds-m-around--medium">Please fill fields with your answers</div>
    
    <c:if test="${answersFormSaved}">
        <div class="slds-text-heading--small slds-m-left--medium">Answers were saved successfully</div>
    </c:if>
    <div class="answers-form-container">
        <form:form cssClass="slds-form--stacked slds-m-around--large"
                method="POST" modelAttribute="answersForm" onsubmit="validateForm(event)">
        	<form:hidden path="id"/>
        	<form:hidden path="userId"/>
            <div class="slds-form-element">
                <form:label cssClass="slds-form-element__label" path="bugs">Bugs:</form:label>
                <div class="slds-form-element__control">
                    <form:textarea cssClass="slds-textarea" rows="5" path="bugs" />
                </div>
            </div>
            <div class="slds-form-element">
                <form:label cssClass="slds-form-element__label" path="ideas">Ideas for tests:</form:label>
                <div class="slds-form-element__control">
                    <form:textarea cssClass="slds-textarea" rows="5" path="ideas" />
                </div>
            </div>
            <div class="slds-form-element">
                <form:label cssClass="slds-form-element__label" path="improvements">Improvements:</form:label>
                <div class="slds-form-element__control">
                    <form:textarea cssClass="slds-textarea" rows="5" path="improvements" />
                </div>
            </div>
            <div class="slds-form-element">
                <form:label cssClass="slds-form-element__label" path="submitted">Submit?</form:label>
                <form:checkbox path="submitted"/>
            </div>
            <div class="slds-button-group" role="group">
                <button class="slds-button slds-button--neutral" type="submit">Save</button>
                <button class="slds-button slds-button--neutral" type="reset">Reset</button>
            </div>
    </form:form>
    </div>
</div>
</body>
    <script type="text/javascript">
    	function validateForm(event) {
    		    		
    		if(document.forms['answersForm']['submitted'].checked) {
    			var result = confirm("Please be aware that after submittion you could not change your answers. Click OK to continue.");
                if(!result) {
                    event.preventDefault();
                }	
    		}
    	}
    </script>
</html>