<%@ include file="header.jsp" %>
<%@ page import="java.util.*, java.util.Map"%>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Stock Details</title>
</head>
<body>
	<div class="container mt-4">
		<h3 class="mb-4">Stock Inventory</h3>
		<br> <a href="dashboard.jsp" class="btn btn-light w-10">Home</a>
		<br>
		<br>
		<div class="container mt-5">
			<table border="1" class="table table-striped table-bordered">
				<thead class="table-dark">
					<tr>
						<th>ID</th>
						<th>Product Name</th>
						<th>Quantity</th>
					</tr>
				</thead>
				<%
				List<Map<String, Object>> stockList = (List<Map<String, Object>>) request.getAttribute("stockList");
				%>
				<%
				if (stockList != null) {
					for (Map<String, Object> stock : stockList) {
				%>
				<tbody>
					<tr>
						<td><%=stock.get("id")%></td>
						<td><%=stock.get("product_name")%></td>
						<td><%=stock.get("quantity")%></td>
					</tr>
				</tbody>
				<%
				}
				}
				%>
			</table>
		</div>
</body>
</html>
<%@ include file="footer.jsp" %>