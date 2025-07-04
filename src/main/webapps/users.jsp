<%@ include file="header.jsp" %>
<%@ page
	import="java.util.*, java.sql.*,java.util.List, com.inventory.model.User, com.inventory.dao.UserDAO"%>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>Manage Users</title>
</head>
<body>
	<div class="container mt-4">
		<h3 class="mb-4">Add New User</h3>
		<br> <a href="dashboard.jsp" class="btn btn-light w-10">Home</a>
		<br><br>
		<form action="UserServlet" method="post" class="row g-3">
			<div class="col-md-6">
				<label for="username" class="form-label">Username</label> <input
					type="text" name="username" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="password" class="form-label">Password</label> <input
					type="password" name="password" class="form-control" required>
			</div>
			<div class="col-md-6">
				<label for="role" class="form-label">Role</label> <select
					name="role" class="form-control">
					<option value="admin">Admin</option>
					<option value="staff">Staff</option>
				</select>
			</div>
			<div class="col-md-6">
				<label for="role" class="form-label">Status</label> <select
					name="role" class="form-control">
					<option value="admin">active</option>
					<option value="staff">inactive</option>
				</select>
			</div>
			<div class="col-12">
				<button type="submit" class="btn btn-primary">Add User</button>
			</div>
		</form>
	</div>
	<%
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");
	UserDAO dao = new UserDAO(conn);
	List<User> users = dao.getAllUsers(); // Ensure this method exists in UserDAO
	%>
	<div class="container mt-5">
		<h2 class="text-center">User List</h2>
		<table border="1" class="table table-striped table-bordered">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Username</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
			</thead>
			<%-- <%
			List<User> userList = (List<User>) request.getAttribute("userList");
			if (userList != null) {
				for (User u : userList) {
			%> --%>
			<tbody>
			<% for (User u : users) { %>
				<tr>
					<td><%=u.getId()%></td>
					<td><%=u.getUsername()%></td>
					<td><%=u.getRole()%></td>
					<td><a href="EditUserServlet?id=<%=u.getId()%>"class="btn btn-sm btn-warning">Edit</a> 
					<a href="DeleteUserServlet?id=<%=u.getId()%>"class="btn btn-sm btn-danger" onClick="return confirm('Are you sure?');">Delete</a></td>
				</tr>
			</tbody>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>

<%@ include file="footer.jsp" %>