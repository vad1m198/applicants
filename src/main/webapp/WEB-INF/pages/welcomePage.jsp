<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
 
<html>
<head>
<title>User Info</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="slds-m-around--medium">
    <div class="slds-text-heading--medium slds-m-around--small">Welcome : ${pageContext.request.userPrincipal.name}</div>
    <a class="slds-button slds-button--brand" href="${pageContext.request.contextPath}/shopping-cart/productList">Continue</a>
</body>
</html>