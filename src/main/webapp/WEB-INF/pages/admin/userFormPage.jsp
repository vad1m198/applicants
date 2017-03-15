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

    <c:if test="${userFormInfo.userInfo.id > 0}">
        <nav role="navigation" aria-label="Breadcrumbs">
            <ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
                <li class="slds-breadcrumb__item slds-text-title--caps">
                    <a href="${pageContext.request.contextPath}/admin/view?id=${userFormInfo.userInfo.id}">Back to details</a>
                </li>
            </ol>
        </nav>
    </c:if>

	<div class="slds-text-heading--medium slds-m-around--medium">Enter User Information</div>
	<div class="customer-info-container slds-m-around--medium">
		<%@ include file="_userForm.jsp" %>
	</div>
 </div>
</body>
</html>