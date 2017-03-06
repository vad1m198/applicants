<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
    <!-- div  style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
        <c:if test="${pageContext.request.userPrincipal.name != null}">        
		<a href="${pageContext.request.contextPath}/shoppingCartAnswers">Shopping Cart Answers</a>
         &nbsp;|&nbsp;
           <a href="${pageContext.request.contextPath}/logout">Logout</a>
 
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </c:if>
    </div -->
    

<div class="slds-context-bar">
  <div class="slds-context-bar__primary slds-context-bar__item--divider-right">
    <div class="slds-context-bar__item slds-context-bar__dropdown-trigger slds-dropdown-trigger slds-dropdown-trigger--click slds-no-hover">
      <div class="slds-context-bar__icon-action">
          <div class="slds-icon-waffle">
            <div class="slds-r1"></div>
            <div class="slds-r2"></div>
            <div class="slds-r3"></div>
            <div class="slds-r4"></div>
            <div class="slds-r5"></div>
            <div class="slds-r6"></div>
            <div class="slds-r7"></div>
            <div class="slds-r8"></div>
            <div class="slds-r9"></div>
          </div>          
      </div>
      <span class="slds-context-bar__label-action slds-context-bar__app-name">
        <span class="slds-truncate" title="{ props.appName || &#x27;App Name&#x27; }">Shopping Cart App</span>
      </span>
    </div>
  </div>
  <nav class="slds-context-bar__secondary" role="navigation">
    <ul class="slds-grid">
      <li class="slds-context-bar__item">
        <a href="${pageContext.request.contextPath}/logout" class="slds-context-bar__label-action">Logout</a>
      </li>
      <li class="slds-context-bar__item">
      <a href="${pageContext.request.contextPath}/shoppingCartAnswers" class="slds-context-bar__label-action">Shopping Cart Answers</a>        
      </li>
    </ul>
  </nav>
</div>