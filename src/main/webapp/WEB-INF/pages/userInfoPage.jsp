<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
 
<html>
<head>
<title>User Info</title>
</head>
<body>

<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
     <a href="${pageContext.request.contextPath}/logout">Logout</a>
  </c:if>
  
</div>
    <h2>User Info Page</h2>
    <h3>Welcome : ${pageContext.request.userPrincipal.name}</h3> 
</body>
</html>