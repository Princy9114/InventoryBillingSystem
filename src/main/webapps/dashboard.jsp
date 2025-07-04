<%@ page import="javax.servlet.http.HttpSession"%>
<%@ include file="header.jsp" %>

<%
session = request.getSession(false);
if (session == null || session.getAttribute("username") == null) {
	response.sendRedirect("login.jsp");
	return;
}
String role = (String) session.getAttribute("role");
%>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>Dashboard</title>
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center mb-4">Welcome to Inventory Dashboard</h2><br>
		<br> <a href="logout.jsp" class="btn btn-light w-10">Logout</a>
		<br><br>
		<%
		if ("admin".equals(role)) {
		%>
		<div class="row row-cols-1 row-cols-md-3 g-4">
			<!-- Company Card -->
			<div class="col">
				<div class="card text-white bg-primary h-100">
					<div class="card-body">
						<h5 class="card-title">Company</h5>
						<p class="card-text">Manage company details.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="CompanyServlet" class="btn btn-light w-100">Go to
							Company</a>
					</div>
				</div>
			</div>
			
			<!-- Category Card -->
			<div class="col">
				<div class="card text-white bg-info h-100">
					<div class="card-body">
						<h5 class="card-title">Category</h5>
						<p class="card-text">Manage categories.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="CategoryServlet" class="btn btn-light w-100">Go to
							Categories</a>
					</div>
				</div>
			</div>
			
			<!-- Product Card -->
			<div class="col">
				<div class="card text-white bg-success h-100">
					<div class="card-body">
						<h5 class="card-title">Products</h5>
						<p class="card-text">Add or view product info.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="ProductServlet" class="btn btn-light w-100">Go to
							Products</a>
					</div>
				</div>
			</div>

			<!-- Purchase Card -->
			<div class="col">
				<div class="card text-white bg-info h-100">
					<div class="card-body">
						<h5 class="card-title">Purchases</h5>
						<p class="card-text">Track and manage purchases.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="PurchaseServlet" class="btn btn-light w-100">Go to
							Purchases</a>
					</div>
				</div>
			</div>

			<!-- Stock Card -->
			<div class="col">
				<div class="card text-white bg-warning h-100">
					<div class="card-body">
						<h5 class="card-title">Stock</h5>
						<p class="card-text">View available stock.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="StockServlet" class="btn btn-light w-100">Go to Stock</a>
					</div>
				</div>
			</div>

			<!-- Billing Card -->
			<div class="col">
				<div class="card text-white bg-danger h-100">
					<div class="card-body">
						<h5 class="card-title">Billing</h5>
						<p class="card-text">Create and manage invoices.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="BillingServlet" class="btn btn-light w-100">Go to
							Billing</a>
					</div>
				</div>
			</div>

			<!-- Users Card (Only for Admin) -->
			<div class="col">
				<div class="card text-white bg-dark h-100">
					<div class="card-body">
						<h5 class="card-title">Users</h5>
						<p class="card-text">Manage staff/admin users.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="UserServlet" class="btn btn-light w-100">Go to Users</a>
					</div>
				</div>
			</div>
		</div>
		<%-- <%}
	else if("company".equals(role)) {
	%>
		<div class="row row-cols-1 row-cols-md-3 g-4">
			<!-- Company Card -->
			<div class="col">
				<div class="card text-white bg-primary h-100">
					<div class="card-body">
						<h5 class="card-title">Company</h5>
						<p class="card-text">Manage company details.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="CompanyServlet" class="btn btn-light w-100">Go to
							Company</a>
					</div>
				</div>
			</div>

			<!-- Product Card -->
			<div class="col">
				<div class="card text-white bg-success h-100">
					<div class="card-body">
						<h5 class="card-title">Products</h5>
						<p class="card-text">Add or view product info.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="ProductServlet" class="btn btn-light w-100">Go to
							Products</a>
					</div>
				</div>
			</div>

			<!-- Purchase Card -->
			<div class="col">
				<div class="card text-white bg-info h-100">
					<div class="card-body">
						<h5 class="card-title">Purchases</h5>
						<p class="card-text">Track and manage purchases.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="PurchaseServlet" class="btn btn-light w-100">Go to
							Purchases</a>
					</div>
				</div>
			</div>

			<!-- Stock Card -->
			<div class="col">
				<div class="card text-white bg-warning h-100">
					<div class="card-body">
						<h5 class="card-title">Stock</h5>
						<p class="card-text">View available stock.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="StockServlet" class="btn btn-light w-100">Go to Stock</a>
					</div>
				</div>
			</div>

			<!-- Billing Card -->
			<div class="col">
				<div class="card text-white bg-danger h-100">
					<div class="card-body">
						<h5 class="card-title">Billing</h5>
						<p class="card-text">Create and manage invoices.</p>
					</div>
					<div class="card-footer bg-transparent border-top-0">
						<a href="BillingServlet" class="btn btn-light w-100">Go to
							Billing</a>
					</div>
				</div>
			</div> --%>
		<%
		} else {
		%>
		<!-- Purchase Card -->
		<div class="col">
			<div class="card text-white bg-info h-10">
				<div class="card-body">
					<h5 class="card-title">Purchases</h5>
					<p class="card-text">Track and manage purchases.</p>
				</div>
				<div class="card-footer bg-transparent border-top-0">
					<a href="PurchaseServlet" class="btn btn-light w-100">Go to
						Purchases</a>
				</div>
			</div>
		</div>
		<br>
		<!-- Billing Card -->
		<div class="col">
			<div class="card text-white bg-danger h-10">
				<div class="card-body">
					<h5 class="card-title">Billing</h5>
					<p class="card-text">Create and manage invoices.</p>
				</div>
				<div class="card-footer bg-transparent border-top-0">
					<a href="BillingServlet" class="btn btn-light w-100">Go to
						Billing</a>
				</div>
			</div>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>
