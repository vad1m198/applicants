<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
 <!-- allUsers -->
<html>
<head>
<title>Dashboard</title>
    <link rel="stylesheet" href="<c:url value="/resource/css/salesforce-lightning-design-system.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" />
</head>
<body>
	<div class="slds">
		<jsp:include page="_adminHeader.jsp" />
	   <c:if test="${empty users}">
		   <div class="slds-text-heading--small slds-m-around--medium">There are no Users</div>
	   </c:if>

	   <c:if test="${ not empty users}">
		   <form:form cssClass="slds-m-around--medium slds-align--absolute-center slds-form--inline"
					  method="GET" modelAttribute="userSearch">
				<div class="slds-form-element">
					<form:label cssClass="slds-form-element__label" path="firstName">First Name</form:label>
					<div class="slds-form-element__control">
						<form:input cssClass="slds-input" path="firstName" />
					</div>
				</div>
				<div class="slds-form-element">
					<form:label cssClass="slds-form-element__label" path="secondName">Second Name</form:label>
					<div class="slds-form-element__control">
						<form:input cssClass="slds-input" path="secondName" />
					</div>
				</div>
				<div class="slds-form-element">
					<button class="slds-button slds-button--brand" type="submit">Search</button>
				</div>
		   </form:form>
           <div class="dashboard-table-container">
	   		<table class="slds-table slds-table--bordered slds-table--cell-buffer">
			  <thead>
			    <tr class="slds-text-title--caps">
                    <th scope="col"><div class="slds-truncate" title="First Name">First Name</div></th>
                    <th scope="col"><div class="slds-truncate" title="Second Name">Second Name</div></th>
                    <th scope="col"><div class="slds-truncate" title="Email Name">Email</div></th>
                    <th scope="col"><div class="slds-truncate" title="Roles">Roles</div></th>
                    <th scope="col"><div class="slds-truncate" title="Details">Details</div></th>
			    </tr>
			  </thead>
			  <tbody>
				  <c:forEach items="${users}" var="user">
				    <tr>
                        <td data-label="First Name"><div class="slds-truncate" title="${user.firstName}">${user.firstName}</div></td>
                        <td data-label="Second Name"><div class="slds-truncate" title="${user.secondName}">${user.secondName}</div></td>
                        <td data-label="Email"><div class="slds-truncate" title="${user.email}">${user.email}</div></td>
				      <td data-label="Roles"><div class="slds-truncate" title="roles"><c:forEach items="${user.roles}" var="r" varStatus="loop">
							    ${r.role}
								<c:if test="${!loop.last}">,</c:if>
							</c:forEach>
                        </div>
				      </td>
				     <td data-label="Details">
				       	<a href="<c:url value="/admin/details?id=${user.id}"/>">Details</a>				       
				      </td>  
				    </tr>
				  </c:forEach>			  
			  </tbody>
			  </table>
           </div>
	   </c:if>
	</div>
</body>
</html>