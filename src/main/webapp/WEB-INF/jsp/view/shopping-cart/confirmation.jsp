<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>Order Confirmation Page</title>
	<link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
	<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
<body>
<div class="slds">
	<jsp:include page="_header.jsp" />
    <nav role="navigation" aria-label="Breadcrumbs">
        <ol class="slds-breadcrumb slds-list--horizontal slds-m-around--small">
            <li class="slds-breadcrumb__item slds-text-title--caps"><a href="<c:url value="/shopping-cart/cart" />">Shopping Cart</a></li>
        </ol>
    </nav>
    <div class="slds-text-heading--medium slds-m-around--medium">Thank you for Order</div>

    <div class="slds-tile  confirmation-container slds-m-left--medium">
        <h2 class="slds-truncate" title="Customer Information">Customer Information</h2>
        <div class="slds-tile__detail slds-text-body--medium">
            <dl class="slds-list--horizontal slds-wrap">
                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Name">Name:</dt>
                <dd class="slds-item--detail slds-truncate">${cartInfo.customerInfo.name}</dd>
                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Phone">Phone:</dt>
                <dd class="slds-item--detail slds-truncate">${cartInfo.customerInfo.phone}</dd>
                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Address">Address:</dt>
                <dd class="slds-item--detail slds-truncate">
                    ${cartInfo.customerInfo.address}
                </dd>
            </dl>
        </div>
    </div>

    <div class="slds-text-heading--medium slds-m-around--medium">Cart Information:</div>
    <div class="sl-cart-container">
    <c:forEach items="${cartInfo.cartLines}" var="cartLineInfo">
        <article class="slds-card slds-m-around--medium">
            <div class="slds-card__header">
                <header class="slds-has-flexi-truncate">
                    <div class="slds-media__body">
                        <h2><span class="slds-text-heading--small slds-truncate">${cartLineInfo.product.name}</span></h2>
                    </div>
                </header>
            </div>
            <div class="slds-card__body">
                <div class="slds-card__body--inner slds-wrap">
                    <div class="slds-tile slds-card__tile slds-p-horizontal--small slds-hint-parent">
                        <div class="slds-tile__detail slds-text-body--small">
                            <dl class="slds-list--horizontal slds-wrap">
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Code">Code:</dt>
                                <dd class="slds-item--detail slds-truncate">${cartLineInfo.product.code}</dd>
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Price">Price:</dt>
                                <dd class="slds-item--detail slds-truncate">
                                    <fmt:formatNumber currencySymbol="$" value="${cartLineInfo.product.price}" type="currency"/>
                                </dd>
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Quantity">Quantity:</dt>
                                <dd class="slds-item--detail slds-truncate">${cartLineInfo.quantity}</dd>
                                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Subtotal">Subtotal:</dt>
                                <dd class="slds-item--detail slds-truncate">
                                    <fmt:formatNumber currencySymbol="$" value="${cartLineInfo.amount}" type="currency"/>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </c:forEach>
    </div>

    <div class="slds-tile slds-m-left--medium confirmation-container">
        <h2 class="slds-truncate" title="Customer Information">Summary</h2>
        <div class="slds-tile__detail slds-text-body--medium">
            <dl class="slds-list--horizontal slds-wrap">
                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Quantity">Quantity:</dt>
                <dd class="slds-item--detail slds-truncate">${cartInfo.quantityTotal}</dd>
                <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Total">Total:</dt>
                <dd class="slds-item--detail slds-truncate">
                    <fmt:formatNumber currencySymbol="$" value="${cartInfo.amountTotal}" type="currency"/>
                </dd>
            </dl>
        </div>
    </div>
	
	</div>

</body>
</html>