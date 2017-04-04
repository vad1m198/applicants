<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <span class="slds-truncate" title="{ props.appName || &#x27;App Name&#x27; }">Admin Panel</span>
      </span>
    </div>
  </div>
  <nav class="slds-context-bar__secondary" role="navigation">
    <ul class="slds-grid">
      <li class="slds-context-bar__item">
      	<a class="slds-context-bar__label-action" href="<c:url value="/admin/dashboard" />">Dashboard</a>        
      </li>
      <li class="slds-context-bar__item">
      	<a class="slds-context-bar__label-action" href="<c:url value="/admin/edit" />">Create User</a>
      </li>
      <li class="slds-context-bar__item">
      	<a class="slds-context-bar__label-action" href="<c:url value="/logout" />">Logout</a>
      </li>
    </ul>
  </nav>
</div>