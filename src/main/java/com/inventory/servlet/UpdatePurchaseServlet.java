package com.inventory.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.dao.PurchaseDAO;
import com.inventory.model.Purchase;
import com.inventory.utils.DBConnection;

@WebServlet("/UpdatePurchaseServlet")
public class UpdatePurchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DBConnection.getConnection()) {
            // Read and validate parameters
            int id = Integer.parseInt(request.getParameter("id"));
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int companyId = Integer.parseInt(request.getParameter("company_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String dateStr = request.getParameter("date");
            double unitPrice = Double.parseDouble(request.getParameter("unit_price"));
            double totalPrice = Double.parseDouble(request.getParameter("total_price"));

            // Convert String date to java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dateStr);
            Date sqlDate = new Date(utilDate.getTime());

            // Update Purchase
            Purchase purchase = new Purchase(id, productId, companyId, quantity, sqlDate, unitPrice, totalPrice);
            PurchaseDAO dao = new PurchaseDAO(conn);
            dao.updatePurchase(purchase);

            response.sendRedirect("PurchaseServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error updating purchase: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("PurchaseServlet");
    }
}
