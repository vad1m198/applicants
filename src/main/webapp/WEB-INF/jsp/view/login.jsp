<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
 
<html>
<head>
<title>Login</title>
	<link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
	<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
	<body>
        <div class="slds">
            <div class="sl-login-container">
                <c:if test="${param.error != null}">
                    <div style="color:red;margin:10px 0px;">
                        Login Failed!!!<br />
                        Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </div>
                </c:if>
                <div class="slds-text-heading--medium">Enter user name and password:</div>

                <form:form  cssClass="slds-form--stacked" method="post" modelAttribute="loginForm" >
                    <div class="slds-form-element">
                        <form:label cssClass="slds-form-element__label" path="username">Username:</form:label>
                        <div class="slds-form-element__control">
                            <form:input cssClass="slds-input" path="username" required="true"/>
                        </div>
                        <form:errors path="username" cssClass="error-message" />
                    </div>
                    <div class="slds-form-element">
                        <form:label cssClass="slds-form-element__label" path="password">Password</form:label>
                        <div class="slds-form-element__control">
                            <form:password cssClass="slds-input" path="password" required="true"/>
                        </div>
                        <form:errors path="password" cssClass="error-message" />
                    </div>
                    <div class="slds-form-element">
                        <button class="slds-button slds-button--brand" type="submit" value="submit">Submit</button>
                    </div>
                </form:form>
            </div>
        </div>
	</body>
</html>
