<%@page import="java.util.ArrayList"%>
<%@page import="com.pms.entity.Category"%>
<html>
<head>
<title>Create Product</title>
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
<%ArrayList<Category> categoryList = (ArrayList)request.getAttribute("categoryList");
String message="";
 message=request.getAttribute("errorMessage").toString();
%>

	<form method="post" action="/product/createProduct">
	<%-- 	<input type="text" name="pName"  placeholder="Product Name "><br>
		<br> <input type="text" name="pDisplayName"
			placeholder="Product Display Name " ><br>
		<br> <input type="text" name="pPrice"
			placeholder="Product price " ><br>
		<br> 
			<select name="cmId.cmId">
			<%
			for(int i=0;i<categoryList.size();i++) {%>
			    <option value="<%=categoryList.get(i).getCmId()%>">
			        <%=categoryList.get(i).getCmDisplayName()%>
			    </option>
			  <%} %>
			</select>
			
			<br>
		<br> <input type="text" name="pAuditFields"
			placeholder="Audit Fields" ><br>
		<br> <input type="submit" value="submit" /> --%>

	
	
	 <table class='table table-hover table-responsive table-bordered'>
 <%=message %><tr>
 </tr>
 
        <tr>
            <td><b>Product Name</b></td>
            <td><input type="text" name="pName"  placeholder="Product Name " required></td>
        </tr>
 
        <tr>
            <td><b>Display Name</b></td>
            <td> <input type="text" name="pDisplayName"
			placeholder="Product Display Name" required></td>
        </tr>
 
        <tr>
            <td><b>Product price (Rupees)</b></td>
            <td><input type="text" name="pPrice"
			placeholder="Product price " required></td>
            
        </tr>
        <tr>
            <td><b>Category Type</b></td>
            <td><select name="category.cmId" required>
			<%
			for(int i=0;i<categoryList.size();i++) {%>
			    <option value="<%=categoryList.get(i).getCmId()%>">
			        <%=categoryList.get(i).getCmDisplayName()%>
			    </option>
			  <%} %>
			</select></td>
            
        </tr>
        <tr>
            <td><b>Audit Details</b></td>
            <td><input type="text" name="pAuditFields"
			placeholder="Audit Fields" required></td>
            
        </tr>
 
        <tr>
            <td></td>
            <td>
               <br> <input type="submit" value="submit" />
            </td>
        </tr>
 
    </table> 
	</form>
</body>
</html>