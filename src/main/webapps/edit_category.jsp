<%@ page import="com.inventory.model.Category" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Edit Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3>Edit Category</h3>
    <form action="UpdateCategoryServlet" method="post" class="row g-3">
        <input type="hidden" name="id" value="${category.id}">
        <div class="col-md-6">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" value="${category.name}" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Description</label>
            <input type="text" name="description" class="form-control" value="${category.description}" required>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-success">Update</button>
            <a href="CategoryServlet" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>