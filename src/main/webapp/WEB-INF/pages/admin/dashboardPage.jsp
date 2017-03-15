<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
 <!-- allUsers -->
<html>
<head>
<title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/salesforce-lightning-design-system.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="slds-m-around--medium">
	<div class="slds">
		<jsp:include page="_adminHeader.jsp" />
		
		<div class="slds-text-heading--medium slds-m-around--small">Dashboard Page</div>
		
	   <c:if test="${empty allUsers}">
	       <div class="slds-text-heading--small slds-m-around--medium">There are no Users</div>   
	   </c:if>
	   
	   <c:if test="${ not empty allUsers}">
	       <div class="dashboard-table-container">
		    <table class="slds-table slds-table--bordered slds-table--cell-buffer">
			  <thead>
			    <tr class="slds-text-title--caps">
			      <th scope="col">
			        <div class="slds-truncate" title="First Name">First Name</div>
			      </th>
			      <th scope="col">
			        <div class="slds-truncate" title="Last Name">Last Name</div>
			      </th>
			      <th scope="col">
			        <div class="slds-truncate" title="Email">Email</div>
			      </th>
			      <th scope="col">
			        <div class="slds-truncate" title="Roles">Roles</div>
			      </th>
		      	  <th scope="col">
			        <div class="slds-truncate" title="Details">Details</div>
			      </th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach items="${allUsers}" var="user">
			    <tr>
			      <td data-label="First Name">
			        <div class="slds-truncate" title="${user.first_name}">${user.first_name}</div>
			      </td>
			      <td data-label="Last Name">
			        <div class="slds-truncate" title="${user.last_name}">${user.last_name}</div>
			      </td>
			      <td data-label="Email">
			        <div class="slds-truncate" title="${user.email}">${user.email}</div>
			      </td>
			      <td data-label="Roles">
			        <div class="slds-truncate" title="roles">
			        	<c:forEach items="${user.roles}" var="r" varStatus="loop">
						    ${r.role}
							<c:if test="${!loop.last}">,</c:if>
						</c:forEach>
					</div>
			      </td>
			     <td data-label="Details">
			        <div class="slds-truncate" title="${user.email}"><a href="${pageContext.request.contextPath}/admin/view?id=${user.id}">Details</a></div>
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