<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>User Details</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="slds">
    <jsp:include page="_adminHeader.jsp" />
    <div class="slds-text-heading--medium slds-m-around--medium">User Information</div>
    <div class="slds-m-around--medium user-details-container">
        <div class="slds-tile">
            <h3 class="slds-truncate slds-text-heading--small" title="User Details">UserDetails</h3>
            <div class="slds-tile__detail slds-text-body--medium">
                <dl class="slds-list--horizontal slds-wrap">
                    <dt class="slds-item--label slds-text-color--weak slds-truncate" title="First Name">First Name:</dt>
                    <dd class="slds-item--detail slds-truncate">${userDetailsInfo.first_name}</dd>
                    <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Last Name">Last Name:</dt>
                    <dd class="slds-item--detail slds-truncate">${userDetailsInfo.last_name}</dd>
                    <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Email">Email:</dt>
                    <dd class="slds-item--detail slds-truncate">${userDetailsInfo.email}</dd>
                    <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Roles">Roles:</dt>
                    <dd class="slds-item--detail slds-truncate">
                        <c:forEach items="${userDetailsInfo.roles}" var="r" varStatus="loop">
                            ${r.role}
                            <c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="slds-button-group" role="group">
            <a class="slds-button slds-button--neutral" href="${pageContext.request.contextPath}/admin/userForm?id=${userDetailsInfo.id}">Edit</a>
            <%--a class="slds-button slds-button--neutral" href="${pageContext.request.contextPath}/shopping-cart/shoppingCartCustomer">Show answers</a--%>
        </div>
    </div>

</div>
</body>
</html>
