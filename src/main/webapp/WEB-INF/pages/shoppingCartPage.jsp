<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
 
<html>
<head>
<title>Shopping Cart</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

</head>
<body>
<div class="slds">
	<jsp:include page="_header.jsp" />	
	<nav role="navigation" aria-label="Breadcrumbs">
	  <ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
	    <li class="slds-breadcrumb__item slds-text-title--caps"><a href="${pageContext.request.contextPath}/shopping-cart/productList">Back to product list</a></li>    
	  </ol>
	</nav>

    <div class="slds-text-heading--medium slds-m-around--medium">Shopping Cart Page</div>
    
   <c:if test="${empty cartForm or empty cartForm.cartLines}">
       <div class="slds-text-heading--small slds-m-around--medium">There is no items in Cart</div>   
   </c:if> 
   <c:if test="${not empty cartForm and not empty cartForm.cartLines}">
	   <form:form method="POST" modelAttribute="cartForm" action="${pageContext.request.contextPath}/shopping-cart/shoppingCart">
		   <div class="sl-cart-container">
			   <c:forEach items="${cartForm.cartLines}" var="cartLineInfo" varStatus="varStatus">
			   
			     <article class="slds-card slds-m-around--medium">
				  <div class="slds-card__header">
				    <header class="slds-has-flexi-truncate">
				      <div class="slds-media__body">
				        <h2><span class="slds-text-heading--small slds-truncate">${cartLineInfo.product.name}</span></h2>
				      </div>
				    </header>
				  </div>
				  <div class="slds-card__body">
				    <div class="slds-card__body--inner slds-grid slds-wrap slds-grid--pull-padded">
				      <div class="slds-tile slds-card__tile slds-p-horizontal--small slds-hint-parent">
				          <div class="slds-tile__detail slds-text-body--small">
				            <dl class="slds-list--horizontal slds-wrap">
				              <dt class="slds-item--label slds-text-color--weak slds-truncate" title="First Label">Code:</dt>
				              <dd class="slds-item--detail slds-truncate"> ${cartLineInfo.product.code} <form:hidden
		                              path="cartLines[${varStatus.index}].product.code" /></dd>
				              <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Second Label">Price:</dt>
				              <dd class="slds-item--detail slds-truncate"><fmt:formatNumber  currencySymbol="$" value="${cartLineInfo.product.price}" type="currency"/></dd>
				              <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Second Label">Quantity:</dt>
				              <dd class="slds-item--detail slds-truncate"><form:input path="cartLines[${varStatus.index}].quantity" class="quantity slds-input" onchange="validateInput(this)" data-val="true"/></dd>
				              <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Second Label">Amount:</dt>
				              <dd class="slds-item--detail slds-truncate"> <fmt:formatNumber  currencySymbol="$" value="${cartLineInfo.amount}" type="currency"/></dd>
				            </dl>          
				        </div>
				      </div>
				    </div>
				  </div>
				</article>
			   </c:forEach>
		   </div>		   
			<div class="slds-button-group slds-m-left--medium" role="group">
			  <button class="slds-button slds-button--neutral" id="updateQuantity" type="submit" value="Update Quantity">Update Quantity</button>			  
			  <a class="slds-button slds-button--neutral" href="${pageContext.request.contextPath}/shopping-cart/shoppingCartCustomer">Place Order</a>  
			</div>	       
	   </form:form> 
	   
   </c:if>  
</div>
</body>
  <script type="text/javascript">  
  	  var updateQuantityElem = document.getElementById("updateQuantity");
      function validateInput(elem) {
          var value = elem.value.trim();
          if(isNaN(value) || parseInt(value) != value || !Number.isSafeInteger(parseInt(value))
              || parseInt(value) < 0) {
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