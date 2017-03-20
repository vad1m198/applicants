<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
 
<html>
<head>
<title>User Info</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="slds-m-around--medium">
    <div class="slds-text-heading--medium slds-m-around--small">Welcome : ${pageContext.request.userPrincipal.name}</div
    <div class="slds-text-longform">
        <p>Test task is a small replica of internet shop. It does not place any orders or send products.</p>
        <h3 class="slds-text-heading--small">What should be done.</h3>
        <p>Please take a look at internet shop functionality. After that open 'Shopping cart answers' link in the header and give answers for three questions:</p>
        <ul>
            <li>1) What bugs were found?</li>
            <li>2) What tests were performed?(describe it as ideas not test cases)</li>
            <li>3) What improvements you can suggest to make internet shop more comfortable?</li>
        </ul>
        <h3 class="slds-text-heading--small">What should not be tested.</h3>
        <p>Login/logout functionality, placing orders into data base are out of scoop of test task.</p>
        <p>Click continue to navigate to test task.</p>
    </div>

    <a class="slds-button slds-button--brand" href="${pageContext.request.contextPath}/shopping-cart/productList">Continue</a>
</body>
</html>