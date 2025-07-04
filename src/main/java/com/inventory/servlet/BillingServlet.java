/*
 * package com.inventory.servlet;
 * 
 * import com.inventory.dao.BillingDAO; import com.inventory.dao.ProductDAO;
 * import com.inventory.model.Bill; import javax.servlet.*; import
 * javax.servlet.http.*; import java.io.IOException; import java.sql.*; import
 * java.util.List; import java.util.Map;
 * 
 * public class BillingServlet extends HttpServlet { protected void
 * doPost(HttpServletRequest request, HttpServletResponse response) throws
 * ServletException, IOException { try { Connection conn =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem",
 * "root", "Princy@123"); BillingDAO dao = new BillingDAO(conn);
 * 
 * int productId = Integer.parseInt(request.getParameter("product_id")); int
 * quantity = Integer.parseInt(request.getParameter("quantity")); double
 * unitPrice = Double.parseDouble(request.getParameter("unit_price")); double
 * tax = Double.parseDouble(request.getParameter("tax_percent")); double total =
 * (unitPrice * quantity) + ((unitPrice * quantity) * tax / 100);
 * 
 * Bill bill = new Bill(0, productId, quantity, unitPrice, tax, total, null);
 * dao.addBill(bill); response.sendRedirect("billing.jsp"); } catch (Exception
 * e) { e.printStackTrace(); } }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { try {
 * Class.forName("com.mysql.cj.jdbc.Driver"); Connection conn =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem",
 * "root", "Princy@123");
 * 
 * BillingDAO billingDAO = new BillingDAO(conn); ProductDAO productDAO = new
 * ProductDAO(conn); // You need this DAO
 * 
 * List<Map<String, Object>> billList = billingDAO.getAllBills();
 * List<Map<String, Object>> productList =
 * productDAO.getAllProductsWithDetails();
 * 
 * request.setAttribute("billList", billList);
 * request.setAttribute("productList", productList);
 * 
 * conn.close(); request.getRequestDispatcher("billing.jsp").forward(request,
 * response); } catch (Exception e) { e.printStackTrace();
 * response.getWriter().println("Error loading billing page: " +
 * e.getMessage()); } }
 * 
 * }
 
 */
package com.inventory.servlet;

import com.inventory.dao.BillingDAO;
import com.inventory.utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

//@WebServlet("/BillingServlet")
public class BillingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerName = request.getParameter("customer_name");
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double unitPrice = Double.parseDouble(request.getParameter("unit_price"));
        double gst = Double.parseDouble(request.getParameter("tax_percent"));
        String date = LocalDate.now().toString();

        double total = unitPrice * quantity;
        double gstAmount = total * gst / 100;
        double grandTotal = total + gstAmount;

        try (Connection conn = DBConnection.getConnection()) {
            BillingDAO dao = new BillingDAO(conn);
            int invoiceId = dao.addInvoice(customerName, date, gst, grandTotal);
            dao.addInvoiceItem(invoiceId, productId, quantity, unitPrice, total, date);
            response.sendRedirect("BillingServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Billing Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            BillingDAO dao = new BillingDAO(conn);
            request.setAttribute("invoiceList", dao.getAllInvoices());
            request.setAttribute("productList", dao.getAllProducts());
            request.getRequestDispatcher("billing.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading billing data: " + e.getMessage());
        }
    }
}
