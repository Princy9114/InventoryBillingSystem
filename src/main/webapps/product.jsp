<%@ page import="java.util.*, java.util.Map"%>
<%@ include file="header.jsp" %>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>Products</title>
</head>
<body>
	<div class="container mt-4">
		<h3 class="mb-4">Add New Product</h3>
		<br> <a href="dashboard.jsp" class="btn btn-light w-10">Home</a>
		<br><br>
		<form action="ProductServlet" method="post" class="row g-3">
			<div class="col-md-6">
				<label for="product_name" class="form-label">Product Name</label> <input
					type="text" name="product_name" class="form-control" required>
			</div>

			<div class="col-md-6">
				<label for="company_name" class="form-label">Company</label> <select
					name="company_name" class="form-select" required>
					<%
					List<Map<String, Object>> companyList = (List<Map<String, Object>>) request.getAttribute("companyList");
					if (companyList != null) {
						for (Map<String, Object> company : companyList) {
							String companyId = company.get("id").toString();
							String companyName = (String) company.get("name");
					%>
					<option value="<%=companyId%>"><%=companyName%></option>
					<%
					}
					} else {
					%>
					<option value="">No companies available</option>
					<%
					}
					%>
				</select>
			</div>

			<div class="col-md-6">
				<label for="category_name" class="form-label">Category</label> <select
					name="category_name" class="form-select" required>
					<%
					List<Map<String, Object>> categoryList = (List<Map<String, Object>>) request.getAttribute("categoryList");
					if (categoryList != null) {
						for (Map<String, Object> category : categoryList) {
							String categoryId = category.get("id").toString();
							String categoryName = (String) category.get("name");
					%>
					<option value="<%=categoryId%>"><%=categoryName%></option>
					<%
					}
					} else {
					%>
					<option value="">No categories available</option>
					<%
					}
					%>
				</select>
			</div>

			<div class="col-md-3">
				<label for="unit_price" class="form-label">Unit Price</label> <input
					type="number" name="unit_price" class="form-control" required>
			</div>

			<div class="col-md-3">
				<label for="purchase_price" class="form-label">Purchase
					Price</label> <input type="number" name="purchase_price"
					class="form-control" required>
			</div>

			<div class="col-md-3">
				<label for="gst" class="form-label">GST (%)</label> <input
					type="number" name="gst" class="form-control" required>
			</div>

			<div class="col-md-12">
				<label for="description" class="form-label">Description</label>
				<textarea name="description" class="form-control" rows="3"></textarea>
			</div>

			<div class="col-12">
				<button type="submit" class="btn btn-primary">Save Product</button>
			</div>
		</form>
	</div>

	<div class="container mt-5">
		<h2 class="text-center">Product List</h2>
		<table border="1" class="table table-striped table-bordered">
			<thead class="table-dark">

				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Company</th>
					<th>Category</th>
					<th>Unit Price</th>
					<th>Purchase Price</th>
					<th>GST %</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			</thead>
			<%
			List<Map<String, Object>> productList = (List<Map<String, Object>>) request.getAttribute("productList");
			if (productList == null) {
				out.println("<p style='color:red;'>No product data loaded. Please access via ProductServlet.</p>");
			} else {
				for (Map<String, Object> p : productList) {
			%>
			<tbody>
				<tr>
					<td><%=p.get("id")%></td>
					<td><%=p.get("name")%></td>
					<td><%=p.get("company_name")%></td>
					<td><%=p.get("category_name")%></td>
					<td><%=p.get("unit_price")%></td>
					<td><%=p.get("purchase_price")%></td>
					<td><%=p.get("gst")%></td>
					<td><%=p.get("description")%></td>
					<td><a href="EditProductServlet?id=<%=p.get("id")%>"
						class="btn btn-sm btn-warning">Edit</a> <a
						href="DeleteProductServlet?id=<%=p.get("id")%>"
						class="btn btn-sm btn-danger"
						onClick="return confirm('Are you sure?');">Delete</a></td>
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