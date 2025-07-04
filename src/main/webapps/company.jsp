<%@ page import="java.util.*,com.inventory.model.Company"%>
<%@ include file="header.jsp" %>
<html>
<head>
<title>Companies</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h3 class="mb-4">Add Company</h3>
		<br> <a href="dashboard.jsp" class="btn btn-light w-10">Home</a>
		<br><br>
		<form action="CompanyServlet" method="post" class="row g-3">
			<div class="col-md-6">
				<label for="company_name" class="form-label">Company Name</label> <input
					type="text" name="name" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="address" class="form-label">Address</label> <input
					type="text" name="address" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="contact" class="form-label">Contact</label> <input
					type="text" name="contact" class="form-control" required>
			</div>

			<div class="col-12">
				<button type="submit" class="btn btn-primary">Add Company</button>
			</div>
		</form>
	</div>
	<div class="container mt-5">
		<h2 class="text-center">Company List</h2>
		<table border="1" class="table table-striped table-bordered">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Address</th>
					<th>Contact</th>
					<th>Action</th>
				</tr>
				<%
				List<Map<String, Object>> companyList = (List<Map<String, Object>>) request.getAttribute("companyList");
				%>
			
			<tbody>
				<%
				if (companyList != null && !companyList.isEmpty()) {
				    for (Map<String, Object> company : companyList) {
				%>
				<tr>
					<td><%=company.get("id")%></td>
					<td><%=company.get("name")%></td>
					<td><%=company.get("address")%></td>
					<td><%=company.get("contact")%></td>
					<td><a href="EditCompanyServlet?id=<%=company.get("id")%>"
						class="btn btn-sm btn-warning">Edit</a> <a
						href="DeleteCompanyServlet?id=<%=company.get("id")%>"
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