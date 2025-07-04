<%@ include file="header.jsp" %>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Login - InventorySystem</title>
</head>
<body>
	<%-- <h2>Login</h2>
	<form action="LoginServlet" method="post">
		Username: <input type="text" name="username"><br>
		Password: <input type="password" name="password"><br> <input
			type="submit" value="Login">
	</form>
	<p style="color: red">${error}</p> --%>
	<div class="container mt-4">
		<h3 class="mb-4">Login</h3>
		<form action="LoginServlet" method="post" class="w-50 mx-auto">
			<div class="mb-3">
				<label for="username" class="form-label">Username:</label> <input
					type="text" name="username" class="form-control" required>
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" name="password" class="form-control" required>
			</div>

			<div class="text-center">
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>