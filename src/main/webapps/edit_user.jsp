<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.inventory.model.User" %>
<%
    User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4">Edit User</h3>
    <form action="/UpdateUserServlet" method="post" class="row g-3">

        <input type="hidden" name="id" value="<%= user.getId() %>">

        <div class="col-md-6">
            <label class="form-label">Username</label>
            <input type="text" class="form-control" name="username" value="<%= user.getUsername() %>" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Password</label>
            <input type="password" class="form-control" name="password" value="<%= user.getPassword() %>" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Role</label>
            <select class="form-select" name="role" required>
                <option value="admin" <%= "admin".equals(user.getRole()) ? "selected" : "" %>>Admin</option>
                <option value="staff" <%= "staff".equals(user.getRole()) ? "selected" : "" %>>Staff</option>
            </select>
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Update User</button>
            <a href="users.jsp" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>