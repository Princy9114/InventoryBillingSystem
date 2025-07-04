<%@ include file="header.jsp" %>
<%@ page import="java.util.*, java.util.Map"%>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Billing</title>
</head>
<body>
	<div class="container mt-4">
		<h3 class="mb-4">Create Invoice</h3>
		<br> <a href="dashboard.jsp" class="btn btn-light w-10">Home</a>
		<br><br>
		<form action="BillingServlet" method="post" class="row g-3">
			<div class="col-md-6">
				<label for="customer_name" class="form-label">Customer Name</label>
				<input type="text" name="customer_name" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="product_id" class="form-label">Product</label>
				<select name="product_id" class="form-select" required>
					<option value="">Select Product</option>
					<%
						List<Map<String, Object>> productList = (List<Map<String, Object>>) request.getAttribute("productList");
						if (productList != null) {
							for (Map<String, Object> product : productList) {
					%>
							<option value="<%= product.get("id") %>"><%= product.get("name") %></option>
					<%
							}
						}
					%>
				</select>
			</div>
			<div class="col-md-6">
				<label for="quantity" class="form-label">Quantity</label>
				<input type="number" name="quantity" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="unit_price" class="form-label">Unit Price</label>
				<input type="number" step=0.10 name="unit_price" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="tax_percent" class="form-label">GST/Tax (%): </label>
				<input type="number" step="0.10" name="tax_percent" class="form-control" required>
			</div>
			<div class="col-12">
				<button type="submit" class="btn btn-primary">Generate Invoice</button>
			</div>
		</form>
	</div>

	<div class="container mt-5">
		<h2 class="text-center">Invoice Records</h2>
		<table border="1" class="table table-striped table-bordered">
			<thead class="table-dark">
				<tr>
					<th>Invoice ID</th>
					<th>Customer Name</th>
					<th>Date</th>
					<th>GST</th>
					<th>Grand Total</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<%
				List<Map<String, Object>> invoiceList = (List<Map<String, Object>>) request.getAttribute("invoiceList");
				if (invoiceList != null) {
					for (Map<String, Object> invoice : invoiceList) {
			%>
				<tr>
					<td><%= invoice.get("invoice_id") %></td>
					<td><%= invoice.get("customer_name") %></td>
					<td><%= invoice.get("date") %></td>
					<td><%= invoice.get("gst") %></td>
					<td><%= invoice.get("grand_total") %></td>
					<td><a href="DownloadInvoice?invoice_id=<%= invoice.get("invoice_id") %>" class="btn btn-sm btn-success">PDF</a>
						<%-- <a href="EditInvoiceServlet?invoice_id=<%= invoice.get("invoice_id") %>" class="btn btn-sm btn-primary">Edit</a>
						<a href="SoftDeleteInvoiceServlet?invoice_id=<%= invoice.get("invoice_id") %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure to delete this invoice?')">Delete</a>
					 --%></td>
				</tr>
			<%
					}
				}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>