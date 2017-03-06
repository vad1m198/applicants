<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
 
<html>
<head>
<title>User Info</title>
</head>
<body>
	
    <h2>Welcome Page</h2>
    <h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
    <a href="${pageContext.request.contextPath}/productList">Continue</a>
</body>
</html>