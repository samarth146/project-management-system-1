<%@page import="com.pms.entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pms.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

 input[type=submit],input[type=button] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  margin: 5px 0;
  border: none;
  border-radius: 2px;
  cursor: pointer;
}

input[type=submit],input[type=button]:hover {
  background-color: #45a049;
}
 
div {
  border-radius: 4px;
  background-color: #f2f2f2;
  padding: 15px;
}
</style>
<script type="text/javascript">
function cancel(){
	document.forms[0].action="http://localhost:8080/product/getProductsPost";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%Product product=(Product)request.getAttribute("product"); 
Category fetchedcategory=(Category)product.getCategory(); 
request.setAttribute("fetchedcategoryId", fetchedcategory.getCmId());
ArrayList<Category> categoryList = (ArrayList)request.getAttribute("categoryList");
%>

       <h1>Edit Product</h1>
      <form method="post" action="/product/createProduct"> 
      <input type='hidden' name="pId" value=<%=product.getpId()%> > 
          	<%-- <input type="text" name="pName"  placeholder="Product Name" value="<%=product.getpName()%>"><br>
		<br> <input type="text" name="pDisplayName"
			placeholder="Product Display Name " value="<%=product.getpDisplayName()%>"><br>
		<br> <input type="text" name="pPrice"
			placeholder="Product price " value="<%=product.getpPrice()%>"><br>
			
			<br>
			 <select  name="category.cmId">
      <c:forEach var="item" items="${categoryList}">
        <option value="${item.cmId}" ${item.cmId == fetchedcategoryId ? 'selected="selected"' : ''}>${item.cmDisplayName}</option>
      </c:forEach>
    </select>
		<br> <input type="text" name="pAuditFields"
			placeholder="Audit Fields" value="<%=product.getpAuditFields()%>"><br>
			
			<div class="buttons_box">
				<input type="submit" class="btn btn-default btn-lg active" value="submit" />
				<input type="button" class="btn btn-default btn-lg active" value="cancel" onclick="cancel()">
			</div>
			 --%>
			 <table class='table table-hover table-responsive table-bordered'>
 
        <tr>
            <td><b>Product Name</b></td>
            <td><input type="text" name="pName"  placeholder="Product Name " value="<%=product.getpName()%>" required></td>
        </tr>
 
        <tr>
            <td><b>Display Name</b></td>
            <td> <input type="text" name="pDisplayName"
			placeholder="Product Display Name" value="<%=product.getpDisplayName()%>" required></td>
        </tr>
 
        <tr>
            <td><b>Product price (Rupees)</b></td>
            <td><input type="text" name="pPrice"
			placeholder="Product price " value="<%=product.getpPrice()%>" required></td>
            
        </tr>
        <tr>
            <td><b>Category Type</b></td>
            <td> <select  name="category.cmId">
      <c:forEach var="item" items="${categoryList}">
        <option value="${item.cmId}" ${item.cmId == fetchedcategoryId ? 'selected="selected"' : ''}>${item.cmDisplayName}</option>
      </c:forEach>
    </select></td>
            
        </tr>
        <tr>
            <td><b>Audit Details</b></td>
            <td><input type="text" name="pAuditFields"
			placeholder="Audit Fields" value="<%=product.getpAuditFields()%>" required></td>
            
        </tr>
 
        <tr>
            <td>
            <input type="submit" class="btn btn-default btn-lg active" value="submit" /></td>
            <td>
				
				<input type="button" class="btn btn-default btn-lg active" value="cancel" onclick="cancel()">
            </td>
        </tr>
 
    </table> 
			
			
       </form>  
</body>
</html>