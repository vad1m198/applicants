<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
<html>
<head>
	<title>Welcome</title>
	<link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
	<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
<body>
<div class="slds">
	<div class="slds slds-m-around--medium">
		<a href="<c:url value="/logout" />">Logout</a>
		<div class="slds-text-heading--medium slds-m-around--small">Welcome : ${pageContext.request.userPrincipal.name}</div>
		<div class="slds-text-longform">
			<p>Test task is a small replica of online shop. It does not place any orders or send products.</p>
			<h3 class="slds-text-heading--small">What should be done.</h3>
			<p>Please take a look at online shop functionality. After that open 'Shopping cart answers' link in the header and give answers for three questions:</p>
			<ul>
				<li>What bugs were found?</li>
				<li>What tests were performed? (describe it as ideas not test cases)</li>
				<li>What improvements you can suggest to make online shop more comfortable for customers?</li>
			</ul>
			<h3 class="slds-text-heading--small">What should not be tested.</h3>
			<ul>
				<li>Login/logout functionality</li>
				<li>Placing orders into database</li>
			</ul>
			<p>Click continue to navigate to test task.</p>
		</div>
		<a class="slds-button slds-button--brand" href="<c:url value="/shopping-cart/list" />">Continue</a>
	</div>
</div>
</body>
</html>