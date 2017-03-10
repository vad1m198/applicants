<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Enter Shopping Cart Answers</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="slds">
<jsp:include page="_header.jsp" />

<nav role="navigation" aria-label="Breadcrumbs">
    <ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
        <li class="slds-breadcrumb__item slds-text-title--caps">
            <a href="${pageContext.request.contextPath}/productList">Back to product list</a>
        </li>
    </ol>
</nav>

    <div class="slds-text-heading--medium slds-m-around--medium">Enter Shopping Cart Answers</div>
        <form:form class="slds-form--stacked slds-m-around--xx-large" method="POST" modelAttribute="answersForm"
                   action="${pageContext.request.contextPath}/shoppingCartAnswers">

        <div class="slds-form-element">
            <label class="slds-form-element__label" for="bugs-area">Bugs</label>
            <div class="slds-form-element__control">
                <form:textarea rows="5" id="bugs-area" class="slds-textarea" path="bugs" />
            </div>
        </div>
        <div class="slds-form-element">
            <label class="slds-form-element__label" for="test-cases-area">Test Cases</label>
            <div class="slds-form-element__control">
                <form:textarea rows="5" id="test-cases-area" class="slds-textarea" path="testCases" />
            </div>
        </div>
        <div class="slds-form-element">
            <label class="slds-form-element__label" for="improvements-area">Improvements</label>
            <div class="slds-form-element__control">
                <form:textarea rows="5" id="improvements-area" class="slds-textarea" path="improvements" />
            </div>
        </div>
        <div class="slds-form-element">
            <div class="slds-button-group" role="group">
                <button class="slds-button slds-button--neutral" type="submit" onclick="submitClickHandler(event)" formaction="${pageContext.request.contextPath}/shoppingCartAnswers?submit=true">Submit</button>
                <button class="slds-button slds-button--neutral" type="submit">Save</button>
                <button class="slds-button slds-button--neutral" type="reset">Reset</button>
            </div>
        </div>
    </form:form>
</div>
</body>
    <script type="text/javascript">
        function submitClickHandler (event) {
            var result = confirm("Please be aware that after confirmation you will be restricted from Shopping Cart App. Click OK to continue.");
            if(!result) {
                event.preventDefault();
            }
        }

    </script>
</html>