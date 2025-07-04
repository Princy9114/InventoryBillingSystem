package com.inventory.servlet;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import com.inventory.utils.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
        	String productIdStr = request.getParameter("productId");
            if (productIdStr == null || productIdStr.trim().isEmpty()) {
                response.getWriter().println("Error: Product ID is missing from form submission.");
                return;
            }

            int id = Integer.parseInt(productIdStr); 
            String name = request.getParameter("name");
            double unitPrice = Double.parseDouble(request.getParameter("unit_price"));
            double purchasePrice = Double.parseDouble(request.getParameter("purchase_price"));
            double gst = Double.parseDouble(request.getParameter("gst"));
            String description = request.getParameter("description");
            
            String statusStr = request.getParameter("status");
            int status = (statusStr != null && !statusStr.isEmpty()) ? Integer.parseInt(statusStr) : 1;  

            Product product = new Product(id, name, 0, 0, unitPrice, purchasePrice, gst, description, status);
            ProductDAO dao = new ProductDAO(conn);
            dao.updateProduct(product);

            response.sendRedirect("ProductServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");

            ProductDAO dao = new ProductDAO(conn);
            List<Map<String, Object>> productList = dao.getAllProductsWithDetails();
            request.setAttribute("productList", productList);
            conn.close();
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading company data: " + e.getMessage());
        }
    }
}
