/*
 * package com.inventory.servlet;
 * 
 * import com.inventory.dao.ProductDAO; import com.inventory.dao.CompanyDAO;
 * import com.inventory.dao.CategoryDAO; import com.inventory.model.Product;
 * import javax.servlet.*; import javax.servlet.http.*; import
 * java.io.IOException; import java.sql.*; import java.util.List; import
 * java.util.Map;
 * 
 * public class ProductServlet extends HttpServlet { protected void
 * doPost(HttpServletRequest request, HttpServletResponse response) throws
 * ServletException, IOException { try {
 * Class.forName("com.mysql.cj.jdbc.Driver"); Connection conn =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem",
 * "root", "Princy@123"); ProductDAO dao = new ProductDAO(conn);
 * 
 * String name = request.getParameter("product_name"); String companyIdStr =
 * request.getParameter("company_name"); // Get the company ID as a String
 * String categoryIdStr = request.getParameter("category_name"); // Get the
 * category ID as a String double unitPrice =
 * Double.parseDouble(request.getParameter("unit_price")); double purchasePrice
 * = Double.parseDouble(request.getParameter("purchase_price")); double gst =
 * Double.parseDouble(request.getParameter("gst")); String description =
 * request.getParameter("description"); int status =
 * Integer.parseInt(request.getParameter("status"));
 * 
 * // Validate companyId and categoryId if (companyIdStr == null ||
 * companyIdStr.isEmpty() || categoryIdStr == null || categoryIdStr.isEmpty()) {
 * response.getWriter().println("Company and Category must be selected.");
 * return; // Stop further processing }
 * 
 * int companyId = Integer.parseInt(companyIdStr); // Parse company ID int
 * categoryId = Integer.parseInt(categoryIdStr); // Parse category ID
 * 
 * Product p = new Product(0, name, companyId, categoryId, unitPrice,
 * purchasePrice, gst, description); dao.addProduct(p);
 * response.sendRedirect("ProductServlet"); } catch (NumberFormatException e) {
 * e.printStackTrace(); response.getWriter().println("Invalid number format: " +
 * e.getMessage()); } catch (Exception e) { e.printStackTrace();
 * response.getWriter().println("Error adding product: " + e.getMessage()); } }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { try {
 * Class.forName("com.mysql.cj.jdbc.Driver"); Connection conn =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem",
 * "root", "Princy@123");
 * 
 * ProductDAO productDAO = new ProductDAO(conn); CompanyDAO companyDAO = new
 * CompanyDAO(conn); // DAO for fetching companies CategoryDAO categoryDAO = new
 * CategoryDAO(conn); // DAO for fetching categories
 * 
 * List<Map<String, Object>> productList =
 * productDAO.getAllProductsWithDetails(); List<Map<String, Object>> companyList
 * = companyDAO.getAllCompanies(); // Fetch companies List<Map<String, Object>>
 * categoryList = categoryDAO.getAllCategories(); // Fetch categories
 * 
 * request.setAttribute("productList", productList);
 * request.setAttribute("companyList", companyList); // Set company list for
 * dropdown request.setAttribute("categoryList", categoryList); // Set category
 * list for dropdown
 * 
 * conn.close();
 * 
 * request.getRequestDispatcher("product.jsp").forward(request, response);
 * 
 * } catch (Exception e) { e.printStackTrace();
 * response.getWriter().println("Error loading product data: " +
 * e.getMessage()); } } }
 */

package com.inventory.servlet;

import com.inventory.dao.ProductDAO;
import com.inventory.dao.CompanyDAO;
import com.inventory.dao.CategoryDAO;
import com.inventory.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Connect to DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");
            ProductDAO dao = new ProductDAO(conn);

            // Get all request parameters
            String name = request.getParameter("product_name");
            String companyIdStr = request.getParameter("company_name");
            String categoryIdStr = request.getParameter("category_name");
            String unitPriceStr = request.getParameter("unit_price");
            String purchasePriceStr = request.getParameter("purchase_price");
            String gstStr = request.getParameter("gst");
            String description = request.getParameter("description");

            // Check for null/empty values
            if (companyIdStr == null || companyIdStr.isEmpty() || categoryIdStr == null || categoryIdStr.isEmpty()) {
                response.getWriter().println("Company and Category must be selected.");
                return;
            }

            // Convert numeric values
            int companyId = Integer.parseInt(companyIdStr);
            int categoryId = Integer.parseInt(categoryIdStr);
            double unitPrice = Double.parseDouble(unitPriceStr);
            double purchasePrice = Double.parseDouble(purchasePriceStr);
            double gst = Double.parseDouble(gstStr);

            // Default status (e.g., Active = 1)
            int status = 1;

            // Create and save product
            Product product = new Product(0, name, companyId, categoryId, unitPrice, purchasePrice, gst, description, 1);
            product.setStatus(status); // Optional: only if your Product.java supports it

            dao.addProduct(product);
            response.sendRedirect("ProductServlet");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error adding product: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");

            ProductDAO productDAO = new ProductDAO(conn);
            CompanyDAO companyDAO = new CompanyDAO(conn);
            CategoryDAO categoryDAO = new CategoryDAO(conn);

            List<Map<String, Object>> productList = productDAO.getAllProductsWithDetails();
            List<Map<String, Object>> companyList = companyDAO.getAllCompanies();
            List<Map<String, Object>> categoryList = categoryDAO.getAllCategories();

            request.setAttribute("productList", productList);
            request.setAttribute("companyList", companyList);
            request.setAttribute("categoryList", categoryList);

            conn.close();

            request.getRequestDispatcher("product.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading product data: " + e.getMessage());
        }
    }
}
