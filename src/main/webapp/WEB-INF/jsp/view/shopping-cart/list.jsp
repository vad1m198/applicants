<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
 
<html>
<head>
    <title>Product List</title>
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

    <div class="slds-text-heading--small slds-m-around--medium">Product List Page</div>


        <div class="sl-products-container">
            <c:forEach items="${products}" var="prodInfo">
                <article class="slds-card slds-m-around--medium">
                    <div class="slds-card__header">
                        <header class="slds-has-flexi-truncate">
                            <div class="slds-media__body">
                                <h2><span class="slds-text-heading--small slds-truncate">${prodInfo.name}</span></h2>
                            </div>
                        </header>
                    </div>
                    <div class="slds-card__body">
                        <div class="slds-card__body--inner slds-wrap">
                            <div class="slds-tile slds-card__tile slds-p-horizontal--small slds-hint-parent">
                                <div class="slds-tile__detail slds-text-body--small">
                                    <dl class="slds-list--horizontal slds-wrap">
                                        <dt class="slds-item--label slds-text-color--weak slds-truncate" title="First Label">Code:</dt>
                                        <dd class="slds-item--detail slds-truncate">${prodInfo.code}</dd>
                                        <dt class="slds-item--label slds-text-color--weak slds-truncate" title="Second Label">Price:</dt>
                                        <dd class="slds-item--detail slds-truncate"><fmt:formatNumber currencySymbol="$" value="${prodInfo.price}" type="currency"/></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="slds-card__footer">
                        <a href="<c:url value="/shopping-cart/buy?code=${prodInfo.code}"/>">Add to cart</a>
                    </div>
                </article>


            </c:forEach>
        </div>
    </div>
</body>
</html>