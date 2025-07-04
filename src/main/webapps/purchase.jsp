<%@ page import="java.util.*, java.util.Map"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Purchase Entry</title>
</head>
<body>
    <div class="container mt-4">
        <h3 class="mb-4">Add Purchase</h3>
        <br> <a href="dashboard.jsp" class="btn btn-light w-10">Home</a>
		<br><br>
        <form action="PurchaseServlet" method="post" class="row g-3">
            <div class="col-md-6">
                <label for="product_id" class="form-label">Product: </label>
                <select name="product_id" class="form-select" required>
                    <option value="">Select a product</option>
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
                <label for="company_id" class="form-label">Company: </label>
                <select name="company_id" class="form-select" required>
                    <option value="">Select a company</option>
                    <%
                    List<Map<String, Object>> companyList = (List<Map<String, Object>>) request.getAttribute("companyList");
                    if (companyList != null) {
                        for (Map<String, Object> company : companyList) {
                    %>
                    <option value="<%= company.get("id") %>"><%= company.get("name") %></option>
                    <%
                        }
                    }
                    %>
                </select>
            </div>
            <div class="col-md-6">
                <label for="quantity" class="form-label">Quantity: </label>
                <input type="number" name="quantity" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label for="date" class="form-label">Date: </label>
                <input type="date" name="date" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label for="unit_price" class="form-label">Unit Price: </label>
                <input type="number" step="0.01" name="unit_price" class="form-control" required>
            </div>
            <div class="col-12">
                <button type="submit" class="btn btn-primary">Add Purchase</button>
            </div>
        </form>
    </div>
    <div class="container mt-5">
        <h2 class="text-center">Purchase List</h2>
        <table border="1" class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Product</th>
                    <th>Company</th>
                    <th>Quantity</th>
                    <th>Date</th>
                    <th>Unit Price</th>
                    <th>Total Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
            List<Map<String, Object>> purchaseList = (List<Map<String, Object>>) request.getAttribute("purchaseList");
            if (purchaseList != null) {
                for (Map<String, Object> purchase : purchaseList) {
            %>
                <tr>
                    <td><%= purchase.get("id") %></td>
                    <td><%= purchase.get("product_name") %></td>
                    <td><%= purchase.get("company_name") %></td>
                    <td><%= purchase.get("quantity") %></td>
                    <td><%= purchase.get("date") %></td>
                    <td><%= purchase.get("unit_price") %></td>
                    <td><%= purchase.get("total_price") %></td>
                    <td>
                        <a href="EditPurchaseServlet?id=<%= purchase.get("id") %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="DeletePurchaseServlet?id=<%= purchase.get("id") %>" class="btn btn-sm btn-danger" onClick="return confirm('Are you sure?');">Delete</a>
                    </td>
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