<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
 
<html>
<head>
<title>Product List</title>
</head>
<body>

<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
     <a href="${pageContext.request.contextPath}/logout">Logout</a>
  </c:if>  
</div>
<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
    <a href="${pageContext.request.contextPath}/shoppingCart">Shopping Cart</a>  
</div>

    <h2>Product List Page</h2>

   <c:forEach items="${products}" var="prodInfo">
       <div style="border: 1px solid #ccc;padding:5px;margin:5px;">
           <ul>
               <li>Code: ${prodInfo.code}</li>
               <li>Name: ${prodInfo.name}</li>
               <li>Price: <fmt:formatNumber currencySymbol="$" value="${prodInfo.price}" type="currency"/></li>
               <li><a href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">Add to cart</a></li>
           </ul>
       </div>
 
   </c:forEach>   
</body>
</html>