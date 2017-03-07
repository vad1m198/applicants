<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart Confirmation</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="slds">
<jsp:include page="_header.jsp" />
<nav role="navigation" aria-label="Breadcrumbs">
    <ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
        <li class="slds-breadcrumb__item slds-text-title--caps">
            <a href="${pageContext.request.contextPath}/productList">Back to product list</a>
        </li>
    </ol>
</nav>
  <fmt:setLocale value="en_US" scope="session"/>
    <div class="slds-text-heading--medium slds-m-around--medium">Thank you for Order</div>
    <article class="slds-card slds-m-around--medium">
        <div class="slds-card__header">
            <header class="slds-has-flexi-truncate">
                <div class="slds-media__body">
                    <h2><span class="slds-text-heading--small slds-truncate">Customer Information:</span></h2>
                </div>
            </header>
        </div>
        <div class="slds-card__body">
            <div class="slds-card__body--inner slds-wrap">
                <div class="slds-tile slds-card__tile slds-p-horizontal--small slds-hint-parent">
                    <div class="slds-tile__detail slds-text-body--small">
                        <dl class="slds-list--horizontal slds-wrap">
                            <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Name">Name:</dt>
                            <dd class="slds-item--detail slds-truncate">${myCart.customerInfo.name}</dd>
                            <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Email">Email:</dt>
                            <dd class="slds-item--detail slds-truncate">${myCart.customerInfo.email}</dd>
                            <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Phone">Phone:</dt>
                            <dd class="slds-item--detail slds-truncate">${myCart.customerInfo.phone}</dd>
                            <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Address">Address:</dt>
                            <dd class="slds-item--detail slds-truncate">${myCart.customerInfo.address}</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <div class="slds-text-heading--medium slds-m-around--small">Cart</div>
    <div class="sl-cart-container">
    <c:forEach items="${myCart.cartLines}" var="cartLineInfo">
        <article class="slds-card slds-m-around--medium">
            <div class="slds-card__header">
                <header class="slds-has-flexi-truncate">
                    <div class="slds-media__body">
                        <h2><span class="slds-text-heading--small slds-truncate">${cartLineInfo.productInfo.name}</span></h2>
                    </div>
                </header>
            </div>
            <div class="slds-card__body">
                <div class="slds-card__body--inner slds-wrap">
                    <div class="slds-tile slds-card__tile slds-p-horizontal--small slds-hint-parent">
                        <div class="slds-tile__detail slds-text-body--small">
                            <dl class="slds-list--horizontal slds-wrap">
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Code">Code:</dt>
                                <dd class="slds-item--detail slds-truncate">${cartLineInfo.productInfo.code} <input
                                        type="hidden" name="code" value="${cartLineInfo.productInfo.code}" /></dd>
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Price">Price:</dt>
                                <dd class="slds-item--detail slds-truncate">
                                    <fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/></dd>
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Quantity">Quantity:</dt>
                                <dd class="slds-item--detail slds-truncate">${cartLineInfo.quantity}</dd>
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Subtotal">Subtotal:</dt>
                                <dd class="slds-item--detail slds-truncate">
                                    <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </c:forEach>
</div>

<div class="slds-text-heading--medium slds-m-around--small">Summary</div>
<div class="slds-list--horizontal slds-wrap slds-m-left--medium sl-summary">
    <div class="slds-item--label slds-text-color--weak slds-truncate" title="Quantity">Quantity:</div>
    <div class="slds-item--detail slds-truncate">${myCart.quantityTotal}</div>
    <div class="slds-item--label slds-text-color--weak slds-truncate" title="Total">Total:</div>
    <div class="slds-item--detail slds-truncate"> <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/></div>
</div>
</body>
</html>