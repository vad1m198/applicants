<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
 
<html>
<head>
<title>Shopping Cart</title>
<style>
.quantity {
    width: 45px
}

.quantity.error {
    border: 1px solid red
}
</style>
</head>
<body>

<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
     <a href="${pageContext.request.contextPath}/logout">Logout</a>
  </c:if>
  
</div>
<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
    <a href="${pageContext.request.contextPath}/productList">Back to product list</a>  
</div>

    <h2>Shopping Cart Page</h2>
    
   <c:if test="${empty cartForm or empty cartForm.cartLines}">
       <h2>There is no items in Cart</h2>       
   </c:if> 
   <c:if test="${not empty cartForm and not empty cartForm.cartLines   }">
	   <form:form method="POST" modelAttribute="cartForm" action="${pageContext.request.contextPath}/shoppingCart">
		   <c:forEach items="${cartForm.cartLines}" var="cartLineInfo" varStatus="varStatus">
		   		
		       <div style="border: 1px solid #ccc;padding:5px;margin:5px;">
		           <ul>
	                   <li>Code: ${cartLineInfo.productInfo.code} <form:hidden
	                              path="cartLines[${varStatus.index}].productInfo.code" />
	                   </li>
		               <li>Name: ${cartLineInfo.productInfo.name}</li>               
		               <li>Price: <fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/></li>
		               <li>Quantity:<form:input path="cartLines[${varStatus.index}].quantity" class="quantity" onchange="validateInput(this)" data-val="true"/>
		               	</li>
		               <li>Amount: <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/></li>
		           </ul>
		       </div>
		   </c:forEach>
		   <div style="clear: both"></div>
	       <input id="updateQuantity" type="submit" value="Update Quantity" />
	   </form:form> 
   </c:if>  
</body>
  <script type="text/javascript">  
  	  var updateQuantityElem = document.getElementById("updateQuantity");
      function validateInput(elem) {
    	  if(isNaN(elem.value.trim())) {
    		  elem.dataset.val = false;   
    		  elem.classList.add("error");
    	  } else {
    		  elem.dataset.val = true;
    		  elem.classList.remove("error");
    	  }
    	  verifyInputs();
      }
      
      function verifyInputs() {
    	  var elList = document.querySelector("input[data-val='false']");    	  
    	  if(elList != null) {
    		  updateQuantityElem.disabled = true;
    	  } else {
    		  updateQuantityElem.disabled = false;
    	  }
      }
  </script> 
</html>