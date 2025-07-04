<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.inventory.model.Company"%>
	<%@ include file="header.jsp" %>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h3 class="mb-4">Update Company</h3>
		<%
		Company company = (Company) request.getAttribute("company");
		if (company == null) {
			out.println("Product not found");
			return;
		}
		%>

		<form action="UpdateCompanyServlet" method="post" class="row g-3">
			<input type="hidden" name="id"
				value="<%=company.getId()%>">

			<div class="col-md-6">
				<label class="form-label">Company Name</label> <input type="text"
					name="name" value="<%=company.getName()%>"
					class="form-control" required>
			</div>

			<div class="col-md-6">
				<label class="form-label">Address</label> <input type="text"
					name="address" value="<%=company.getAddress()%>"
					class="form-control" required>
			</div>

			<div class="col-md-6">
				<label class="form-label">Contact</label> <input type="text"
					name="contact"
					value="<%=company.getContact()%>"
					class="form-control" required>
			</div>

			<div class="col-12">
				<button type="submit" class="btn btn-primary">Update
					Product</button>
				<a href="CompanyServlet" class="btn btn-secondary">Cancel</a>
			</div>
		</form>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>