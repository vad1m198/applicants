<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Enter Shopping Cart Answers</title>
<style>

</style>
  
</head>
<body>
<jsp:include page="_header.jsp" />
  <div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
    <a href="${pageContext.request.contextPath}/productList">Back to product list</a>  
</div>
 
<div class="page-title">Enter Shopping Cart Answers</div>
 
   <form:form method="POST" modelAttribute="answersForm"
       action="${pageContext.request.contextPath}/shoppingCartAnswers">
 
       <table>
           <tr>
               <td>Bugs</td>
               <td><form:textarea path="bugs" /></td>               
           </tr>
 
           <tr>
               <td>Test Cases</td>
               <td><form:textarea path="test_cases" /></td>               
           </tr>
 
           <tr>
               <td>Improvements</td>
               <td><form:textarea path="improvements" /></td>               
           </tr> 
           <tr>
               <td>&nbsp;</td>
               <td>
	               <input type="submit" value="Submit" formaction="${pageContext.request.contextPath}/shoppingCartAnswers?submit=true"/>
	               <input type="submit" value="Save" />
	               <input type="reset" value="Reset" />
               </td>
           </tr>
       </table>
 
   </form:form>
  
 
</body>
</html>