<%@page import="com.pms.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Your product</title>
</head>
<body>

<%Product product = (Product)request.getAttribute("product");
%>
	<form method="post" action="/product/createProduct">
	<table>
 
				<tr>
					<td><%=product.getpDisplayName() %></td>
					<%-- <td>${product.pDisplayName}</td>
					<td>${product.pPrice}</td>
					<td>${product.pAuditFields}    --%>
			
				</tr>
	 </table>
	</form>
</body>
</html>