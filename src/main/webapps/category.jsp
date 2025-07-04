<%@ page import="java.util.*,com.inventory.model.Category"%>
<%@ include file="header.jsp" %>
<html>
<head>
<title>Categories</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h3 class="mb-4">Add Categories</h3>
		<br> <a href="dashboard.jsp" class="btn btn-light w-10">Home</a>
		<br><br>
		<form action="CategoryServlet" method="post" class="row g-3">
			<div class="col-md-6">
				<label for="category_name" class="form-label">Category Name</label> <input
					type="text" name="name" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="description" class="form-label">Description</label> <input
					type="text" name="description" class="form-control" required>
			</div>
			
			<div class="col-12">
				<button type="submit" class="btn btn-primary">Add Category</button>
			</div>
		</form>
	</div>
	<div class="container mt-5">
		<h2 class="text-center">Category List</h2>
		<table border="1" class="table table-striped table-bordered">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
				<%
				List<Map<String, Object>> categoryList = (List<Map<String, Object>>) request.getAttribute("categoryList");
				%>
			
			<tbody>
				<%
				if (categoryList != null && !categoryList.isEmpty()) {
				    for (Map<String, Object> category : categoryList) {
				%>
				<tr>
					<td><%=category.get("id")%></td>
					<td><%=category.get("name")%></td>
					<td><%=category.get("description")%></td>
					<td><a href="EditCategoryServlet?id=<%=category.get("id")%>"
						class="btn btn-sm btn-warning">Edit</a> <a
						href="DeleteCategoryServlet?id=<%=category.get("id")%>"
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