<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="slds-form--stacked" method="POST" modelAttribute="userFormInfo"
           action="${pageContext.request.contextPath}/admin/userForm">
    <form:hidden path="userInfo.id" />
    <div class="slds-form-element">
        <label class="slds-form-element__label" for="firstName">First Name:</label>
        <div class="slds-form-element__control">
            <form:input path="userInfo.firstName" id="firstName" class="slds-input"/>
            <div class="slds-form-element__help"><form:errors path="userInfo.firstName" class="error-message" /></div>
        </div>
    </div>
    <div class="slds-form-element">
        <label class="slds-form-element__label" for="lastName">Last Name:</label>
        <div class="slds-form-element__control">
            <form:input path="userInfo.lastName" id="lastName" class="slds-input"/>
            <div class="slds-form-element__help"><form:errors path="userInfo.lastName" class="error-message" /></div>
        </div>
    </div>
    <div class="slds-form-element">
        <label class="slds-form-element__label" for="email">Email:</label>
        <div class="slds-form-element__control">
            <form:input path="userInfo.email" type="email" id="email" class="slds-input"/>
            <div class="slds-form-element__help"><form:errors path="userInfo.email" class="error-message" /></div>
        </div>
    </div>
    <div class="slds-form-element">
        <label class="slds-form-element__label" for="password">Password:</label>
        <div class="slds-form-element__control">
            <form:input path="userInfo.password" id="password" class="slds-input"/>
            <div class="slds-form-element__help"><form:errors path="userInfo.password" class="error-message" /></div>
        </div>
    </div>
    <div class="roles-container">
        <form:checkboxes path="rolesIds" items="${userFormInfo.rolesInfo}" itemLabel="role" itemValue="id" element="div"/>
        <div class="slds-form-element__help"><form:errors path="rolesIds" class="error-message" /></div>
    </div>

    <div class="slds-button-group" role="group">
        <button type="submit" class="slds-button slds-button--neutral">Submit</button>
        <button type="reset" class="slds-button slds-button--neutral">Reset</button>
    </div>

</form:form>
