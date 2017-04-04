<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
<body>
<div class="slds">
	<jsp:include page="_adminHeader.jsp" />
	<c:if test="${empty userDetailsInfo}">
        <div class="slds-text-heading--small slds-m-around--medium">User not found</div>
	</c:if>
    <c:if test="${not empty userDetailsInfo}">
        <div class="slds-m-around--large user-details-container">
		<div class="slds-tile">
			<h2 class="slds-truncate" title="User Information">User Information</h2>
			<div class="slds-tile__detail slds-text-body--medium">
				<dl class="slds-list--horizontal slds-wrap">
					<dt class="slds-item--label slds-text-color--weak slds-truncate" title="First Name">First Name:</dt>
					<dd class="slds-item--detail slds-truncate">${userDetailsInfo.firstName}</dd>
					<dt class="slds-item--label slds-text-color--weak slds-truncate" title="Second Name">Second Name:</dt>
					<dd class="slds-item--detail slds-truncate">${userDetailsInfo.secondName}</dd>
                    <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Email">Email:</dt>
                    <dd class="slds-item--detail slds-truncate">${userDetailsInfo.email}</dd>
                    <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Roles">Roles:</dt>
                    <dd class="slds-item--detail slds-truncate">
                        <c:forEach items="${userDetailsInfo.roles}" var="r" varStatus="loop">
                            ${r.role}<c:if test="${!loop.last}">,</c:if>
                        </c:forEach><br/>
                    </dd>
				</dl>
			</div>
            <a class="slds-button slds-button--brand slds-m-vertical--small" href="<c:url value="/admin/edit?id=${userDetailsInfo.id}"/>">Edit</a>
		</div>
	    <div>
            <c:forEach items="${userDetailsInfo.roles}" var="r" varStatus="loop">
                <c:if test="${r.role eq 'SHOPPING_CART_USER'}">
                    <c:if test="${empty shoppingCartAnswersInfo}">
                        <div class="slds-text-heading--small slds-m-around--medium">User do not have shopping cart answers yet</div>
                    </c:if>
                    <c:if test="${not empty shoppingCartAnswersInfo}">
                        <div class="slds-text-longform">
                            <h3 class="slds-text-heading--small slds-align--absolute-center">Shopping cart answers</h3>
                            <h3 class="slds-text-heading--small">Bugs:</h3>
                            <p>${shoppingCartAnswersInfo.bugs}</p>
                            <h3 class="slds-text-heading--small">Ideas for tests:</h3>
                            <p>${shoppingCartAnswersInfo.ideas}</p>
                            <h3 class="slds-text-heading--small">Improvements:</h3>
                            <p>${shoppingCartAnswersInfo.improvements}</p>
                            <h3 class="slds-text-heading--small">Submitted?</h3>
                            <p>${shoppingCartAnswersInfo.submitted}</p>
                        </div>
                    </c:if>
                </c:if>
            </c:forEach>
        </div>
        </div>
    </c:if>
    

    
</div>
</body>
</html>
