<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
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
<body>
	<script type="text/javascript">
	
		function chooseAction(id){
			if(id==1){
				document.forms[0].action="http://localhost:8080/product/ProductForm";
				document.forms[0].submit();
			}else{
				document.forms[0].action="http://localhost:8080/product/getProducts";
				document.forms[0].submit();
			}
		}
</script>

	<div>
		<form action="/" method="get" target="_blank">
			<div class="buttons_box">
				<button type="button" class="btn btn-default btn-lg active"
					onclick="chooseAction(1)">Create product</button>
				<button type="button" class="btn btn-default btn-lg active"
					onclick="chooseAction(2)">Fetch products</button>
			</div>
		</form>
	</div>
</body>
</html>