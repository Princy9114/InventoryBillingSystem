<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.inventory.model.Product"%>
	<%@ include file="header.jsp" %>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h3 class="mb-4">Update Product</h3>
		<%
		Product product = (Product) request.getAttribute("product");
		if (product == null) {
			out.println("Product not found");
			return;
		}
		%>

		<form action="UpdateProductServlet" method="post" class="row g-3">
			<input type="hidden" name="productId" value="<%=product.getId()%>">



			<div class="col-md-6">
				<label class="form-label">Product Name</label> <input type="text"
					name="name" value="<%=product.getName()%>"
					class="form-control" required>
			</div>

			<div class="col-md-6">
				<label class="form-label">Company ID</label> <input type="number"
					name="company_id" value="<%=request.getAttribute("company_id")%>"
					class="form-control" required>
			</div>

			<div class="col-md-6">
				<label class="form-label">Category ID</label> <input type="number"
					name="category_id"
					value="<%=request.getAttribute("category_id")%>"
					class="form-control" required>
			</div>

			<div class="col-md-3">
				<label class="form-label">Unit Price</label> <input type="number"
					name="unit_price" step="0.10"
					value="<%=product.getUnitPrice()%>"
					class="form-control" required>
			</div>

			<div class="col-md-3">
				<label class="form-label">Purchase Price</label> <input
					type="number" name="purchase_price" step="0.01"
					value="<%=product.getPurchasePrice()%>"
					class="form-control" required>
			</div>

			<div class="col-md-3">
				<label class="form-label">GST (%)</label> <input type="number"
					name="gst" step="0.01" value="<%=product.getGst()%>"
					class="form-control" required>
			</div>

			<div class="col-md-12">
				<label class="form-label">Description</label>
				<textarea name="description" class="form-control" rows="3"><%=product.getDescription()%></textarea>
			</div>

			<div class="col-12">
				<button type="submit" class="btn btn-primary">Update
					Product</button>
				<a href="ProductServlet" class="btn btn-secondary">Cancel</a>
			</div>
		</form>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>