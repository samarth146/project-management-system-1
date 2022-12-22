<%@page import="java.util.Date"%>
<%@page import="com.pms.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

 input[type=submit],input[type=button] {
  width: 8%;
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  margin: 5px 0;
  border: none;
  border-radius: 2px;
  cursor: pointer;
}

.floated {
   float:left;
   margin-right:5px;
}
 select {
  width: 10%;
  padding: 10px 15px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
</style>
</head>
<body>

<script>

/* function searchEvent(object,fieldId) {
  // Declare variables
  var  filter, table, tr, td, i, txtValue;
  filter = object.value;
  table = document.getElementById("ListTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[fieldId];
    if (td) {
    if (td.innerHTML.indexOf(filter) > -1 ){
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
} */

 function searchEvent(object){
	var searchstring=object.value;
	var name=object.name;
	 location.href="http://localhost:8080/product/getProducts?fieldId="+object.name+"&searchString="+searchstring;
} 

function changeFunc(){
	 var selectBox = document.getElementById("pageFiltersize");
	 var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	 location.href="http://localhost:8080/product/getProducts?page=0&size="+selectedValue;
	 return;
}

function home(){
	document.forms[0].action="http://localhost:8080/product/welcome";
	document.forms[0].method="GET";
	document.forms[0].submit();
}

function submitByDate(){
	var selectedDate=document.getElementById("start").value;
	if(document.getElementById("dateFilter1").checked==false && document.getElementById("dateFilter2").checked==false){
		alert("Please select filter Date Type");
		return false;
	}
	
	if(document.getElementById('start').value==null || document.getElementById('start').value==""){
		alert("Please select Date");
		return false;
	}
	document.forms[0].action="http://localhost:8080/product/filterProductByDate?date="+selectedDate+"&page=0&size=10";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
</script>

<%
String date ="";
String dateFilterType="";
if(request!=null && request.getAttribute("date")!=null){
	
date=request.getAttribute("date").toString();
dateFilterType=request.getAttribute("dateFilterType").toString();
System.out.println("dateFilterType value is "+dateFilterType);
}
%>
<form method="post" action="/" target="_blank">
	<h2>List of Products <%=dateFilterType %> <%=date%></h2>
			<label for="start">Choose date:</label>
			<input type="datetime-local" id="start" name="datePicked" value='<%=date%>' required>
			
	       <br><br>
			<div id="showDateFilter">
				<input type="radio" name="dateFilterType" id="dateFilter1" value="before"/>Before Date
				<input type="radio" name="dateFilterType" id="dateFilter2" value="after" /> After Date
			</div>
			<br>
			<div class="buttons_box">
				<input type="button" class="btn btn-default btn-lg active" value="Home"
			onclick="home()">
				<input type="button" class="btn btn-default btn-lg active" value="Filter Date"
			onclick="submitByDate()">
			</div>
			
			<label for="pageFiltersize">Items per page:</label>
		<select name="pageFiltersize" id="pageFiltersize" onchange="changeFunc()">
			<option value="25" ${size == '25' ? 'selected="selected"' : ''}>25</option>
			<option value="50" ${size == '50' ? 'selected="selected"' : ''}>50</option>
			<option value="100" ${size == '100' ? 'selected="selected"' : ''}>100</option>
		</select>
	
	<div>
	</div>
	
 <table id='ListTable'> 
		  <tr class="header">
			<th>Product Name</th>
			<th>Display Name</th>
			<th>Product Price</th>
			<th>Category</th>
			<th>Audit fields</th>
			<th>Date</th>
		 </tr>
			 <tr>
				 <th><input type="text" name="pName" placeholder="search Product Name" onkeyup="searchEvent(this)"></th>
				 <th><input type="text" name="pDisplayName" placeholder="Product Display Name" onkeyup="searchEvent(this)"></th>
				 <th><input type="text" name="pPrice" placeholder="Product Price " onkeyup="searchEvent(this)"><br><br></th>
				 <th><input type="text" name="category" placeholder="Category" onkeyup="searchEvent(this)"><br><br></th>
				 <th><input type="text" name="pAuditFields" placeholder="Audit fields" onkeyup="searchEvent(this)"><br><br></th>
			 <th></th>
			 </tr>
		<c:forEach var="product" items="${productList.content}">  
				<tr>
					<td>${product.pName}</td>
					<td>${product.pDisplayName}</td>
					<td>${product.pPrice}</td>
					<td>${product.category.cmDisplayName}</td>
					<td>${product.pAuditFields}
					<a href="getProductById/${product.pId}">Edit</a>  
					<a href="deleteProduct/${product.pId}">Delete</a>
					</td>
					<td>${product.createdDate}</td>
				</tr>
	
	</c:forEach>
	
		</table>
		
		<table>
		<tr>
		 <c:if test="${productList.content.size() > 0 }">
        <div class="panel-footer">
            Showing ${number+1} of ${size+1} of ${totalElements}
                <c:forEach begin="0" end="${totalPages-1}" var="page">
                <td>
                    <li class="page-item">
                        <a href="http://localhost:8080/product/getProducts?page=${page}&size=${size}" class="page-link">${page+1}</a>
                    </li>
                </td>
                </c:forEach>
        </div>
    </c:if>
		</tr></table>
		</form>
</body>
</html>