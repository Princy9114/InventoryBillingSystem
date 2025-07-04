<%@ include file="header.jsp" %>
<%@ page import="java.util.*, java.util.Map, java.text.SimpleDateFormat, com.inventory.model.Purchase, com.inventory.model.Product, com.inventory.model.Company" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Edit Purchase</title>
</head>
<body>
    <div class="container mt-4">
        <h3 class="mb-4">Edit Purchase</h3>
        <form action="UpdatePurchaseServlet" method="post" class="row g-3">
            <input type="hidden" name="id" value="${purchase.id}">
            
            <div class="col-md-6">
                <label for="product_id" class="form-label">Product: </label>
                <select name="product_id" class="form-select" required>
                    <option value="">Select a product</option>
                    <%
                    List<Map<String, Object>> productList = (List<Map<String, Object>>) request.getAttribute("productList");
                    Purchase purchase = (Purchase) request.getAttribute("purchase");
                    if (productList != null) {
                        for (Map<String, Object> product : productList) {
                            String selected = (purchase.getProductId() == (Integer)product.get("id")) ? "selected" : "";
                    %>
                    <option value="<%= product.get("id") %>" <%= selected %>><%= product.get("name") %></option>
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
                            String selected = (purchase.getCompanyId() == (Integer)company.get("id")) ? "selected" : "";
                    %>
                    <option value="<%= company.get("id") %>" <%= selected %>><%= company.get("name") %></option>
                    <%
                        }
                    }
                    %>
                </select>
            </div>
            
            <div class="col-md-6">
                <label for="quantity" class="form-label">Quantity: </label>
                <input type="number" name="quantity" class="form-control" required 
                       value="<%= purchase.getQuantity() %>">
            </div>
            
            <div class="col-md-6">
                <label for="date" class="form-label">Date: </label>
                <input type="date" name="date" class="form-control" required 
                       value="<%= new SimpleDateFormat("yyyy-MM-dd").format(purchase.getDate())%>">
            </div>
            
            <div class="col-md-6">
                <label for="unit_price" class="form-label">Unit Price: </label>
                <input type="number" step="0.01" name="unit_price" class="form-control" required
                       value="<%= purchase.getUnitPrice() %>">
            </div>
            
            <div class="col-md-6">
                <label for="total_price" class="form-label">Total Price: </label>
                <input type="number" step="0.01" name="total_price" class="form-control" readonly
                       value="<%= purchase.getTotalPrice() %>">
            </div>
            
            <div class="col-12">
                <button type="submit" class="btn btn-primary">Update Purchase</button>
                <a href="PurchaseServlet" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>

    <script>
        // Calculate total price automatically when quantity or unit price changes
        document.addEventListener('DOMContentLoaded', function() {
            const quantityInput = document.querySelector('input[name="quantity"]');
            const unitPriceInput = document.querySelector('input[name="unit_price"]');
            const totalPriceInput = document.querySelector('input[name="total_price"]');
            
            function calculateTotal() {
                const quantity = parseFloat(quantityInput.value) || 0;
                const unitPrice = parseFloat(unitPriceInput.value) || 0;
                totalPriceInput.value = (quantity * unitPrice).toFixed(2);
            }
            
            quantityInput.addEventListener('input', calculateTotal);
            unitPriceInput.addEventListener('input', calculateTotal);
        });
    </script>
</body>
</html>

<%@ include file="footer.jsp" %>
