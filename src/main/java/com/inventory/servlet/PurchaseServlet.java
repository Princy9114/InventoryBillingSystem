package com.inventory.servlet;

import com.inventory.dao.CompanyDAO;
import com.inventory.dao.ProductDAO;
import com.inventory.dao.PurchaseDAO;
import com.inventory.model.Purchase;
import com.inventory.utils.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

public class PurchaseServlet extends HttpServlet {

    private int safeParseInt(String param) {
        try {
            return (param != null && !param.trim().isEmpty()) ? Integer.parseInt(param) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double safeParseDouble(String param) {
        try {
            return (param != null && !param.trim().isEmpty()) ? Double.parseDouble(param) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = safeParseInt(request.getParameter("product_id"));
        int companyId = safeParseInt(request.getParameter("company_id"));
        int quantity = safeParseInt(request.getParameter("quantity"));
        String dateStr = request.getParameter("date");
        double unitPrice = safeParseDouble(request.getParameter("unit_price"));
        double totalPrice = unitPrice * quantity;

        try (Connection conn = DBConnection.getConnection()) {
            PurchaseDAO dao = new PurchaseDAO(conn);

            // Convert String date to java.sql.Date
            java.sql.Date sqlDate = null;
            if (dateStr != null && !dateStr.trim().isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(dateStr);
                sqlDate = new java.sql.Date(utilDate.getTime());
            } else {
                sqlDate = new java.sql.Date(System.currentTimeMillis()); // fallback to current date
            }

            Purchase purchase = new Purchase(0, productId, companyId, quantity, sqlDate, unitPrice, totalPrice);
            dao.addPurchase(purchase);

            response.sendRedirect("PurchaseServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error adding purchase: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            PurchaseDAO dao = new PurchaseDAO(conn);
            List<Map<String, Object>> purchaseList = dao.getAllPurchases();
            request.setAttribute("purchaseList", purchaseList);

            ProductDAO productDao = new ProductDAO(conn);
            List<Map<String, Object>> productList = productDao.getAllProductsWithDetails();
            request.setAttribute("productList", productList);

            CompanyDAO companyDao = new CompanyDAO(conn);
            List<Map<String, Object>> companyList = companyDao.getAllCompanies();
            request.setAttribute("companyList", companyList);

            request.getRequestDispatcher("purchase.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading purchase data: " + e.getMessage());
        }
    }
}
