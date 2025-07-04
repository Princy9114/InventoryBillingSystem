
package com.inventory.servlet;

import com.inventory.dao.CompanyDAO;
import com.inventory.dao.ProductDAO;
import com.inventory.dao.PurchaseDAO;
import com.inventory.model.Purchase;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

@WebServlet("/EditPurchaseServlet")
public class EditPurchaseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String purchaseId = request.getParameter("id"); // Get the purchase ID from the request

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");

            // Fetch the purchase details
            PurchaseDAO purchaseDAO = new PurchaseDAO(conn);
            Purchase purchase = purchaseDAO.getPurchaseById(Integer.parseInt(purchaseId)); // Implement this method in PurchaseDAO

            // Fetch the list of companies
            CompanyDAO companyDAO = new CompanyDAO(conn);
            List<Map<String, Object>> companyList = companyDAO.getAllCompanies();
            request.setAttribute("companyList", companyList);

            // Fetch the list of products
            ProductDAO productDAO = new ProductDAO(conn);
            List<Map<String, Object>> productList = productDAO.getAllProductsWithDetails();
            request.setAttribute("productList", productList);

            // Set the purchase object in the request
            request.setAttribute("purchase", purchase);

            // Close the connection
            conn.close();

            // Forward to the JSP page
            request.getRequestDispatcher("edit_purchase.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading data: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String productIdParam = request.getParameter("product_id");
        String companyIdParam = request.getParameter("company_id");
        String quantityParam = request.getParameter("quantity");
        String date = request.getParameter("date");
        String unitPriceParam = request.getParameter("unit_price");
        String totalPriceParam = request.getParameter("total_price");

        // Log the parameters for debugging
        System.out.println("id: " + idParam);
        System.out.println("product_id: " + productIdParam);
        System.out.println("company_id: " + companyIdParam);
        System.out.println("quantity: " + quantityParam);
        System.out.println("date: " + date);
        System.out.println("unit_price: " + unitPriceParam);
        System.out.println("total_price: " + totalPriceParam);

        // Proceed with parsing
        try {
            int id = Integer.parseInt(idParam);
            int productId = Integer.parseInt(productIdParam);
            int companyId = Integer.parseInt(companyIdParam);
            int quantity = Integer.parseInt(quantityParam);
            double unitPrice = Double.parseDouble(unitPriceParam);
            double totalPrice = Double.parseDouble(totalPriceParam);

            // Continue with your logic...
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error parsing numbers: " + e.getMessage());
        }
    }
    
}
