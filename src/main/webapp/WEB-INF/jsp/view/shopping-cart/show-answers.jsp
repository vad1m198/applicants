<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<title>View Answers</title>
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
    	
        <div class="slds-text-longform slds-m-around--xx-large">
            <h3 class="slds-text-heading--small">Previously submitted answers</h3>
            <h3 class="slds-text-heading--small">Bugs:</h3>
            <p>${answersForm.bugs}</p>
            <h3 class="slds-text-heading--small">Ideas for tests:</h3>
            <p>${answersForm.ideas}</p>
            <h3 class="slds-text-heading--small">Improvements:</h3>
            <p>${answersForm.improvements}</p>
            <h3 class="slds-text-heading--small">Submitted?</h3>
            <p>${answersForm.submitted}</p>
        </div>
    </div>
</body>    
</html>