/*
 * package com.inventory.dao;
 * 
 * import java.sql.*; import java.util.ArrayList; import java.util.HashMap;
 * import java.util.List; import java.util.Map;
 * 
 * import com.inventory.model.Bill;
 * 
 * public class BillingDAO { private Connection conn;
 * 
 * public BillingDAO(Connection conn) { this.conn = conn; }
 * 
 * public void addBill(Bill bill) throws SQLException { String sql =
 * "INSERT INTO bills (product_id, quantity, unit_price, tax_percent, total_amount) VALUES (?, ?, ?, ?, ?)"
 * ; try (PreparedStatement stmt = conn.prepareStatement(sql)) { stmt.setInt(1,
 * bill.getProductId()); stmt.setInt(2, bill.getQuantity()); stmt.setDouble(3,
 * bill.getUnitPrice()); stmt.setDouble(4, bill.getTaxPercent());
 * stmt.setDouble(5, bill.getTotalAmount()); stmt.executeUpdate(); } }
 * 
 * public List<Map<String, Object>> getAllBills() throws SQLException {
 * List<Map<String, Object>> list = new ArrayList<>(); String sql =
 * "SELECT b.*, p.name AS product_name FROM bills b JOIN products p ON b.product_id = p.id"
 * ; try (Statement stmt = conn.createStatement(); ResultSet rs =
 * stmt.executeQuery(sql)) { while (rs.next()) { Map<String, Object> map = new
 * HashMap<>(); map.put("id", rs.getInt("id")); map.put("product_name",
 * rs.getString("product_name")); map.put("quantity", rs.getInt("quantity"));
 * map.put("unit_price", rs.getDouble("unit_price")); map.put("tax_percent",
 * rs.getDouble("tax_percent")); map.put("total_amount",
 * rs.getDouble("total_amount")); map.put("created_at",
 * rs.getTimestamp("created_at")); list.add(map); } } return list; } public
 * Map<String, Object> getInvoiceById(String invoiceId) throws SQLException {
 * Map<String, Object> map = new HashMap<>(); String sql =
 * "SELECT * FROM invoices WHERE invoice_id = ?"; PreparedStatement stmt =
 * conn.prepareStatement(sql); stmt.setString(1, invoiceId); ResultSet rs =
 * stmt.executeQuery(); if (rs.next()) { map.put("invoice_id",
 * rs.getInt("invoice_id")); map.put("customer_name",
 * rs.getString("customer_name")); map.put("date", rs.getString("date"));
 * map.put("gst", rs.getDouble("gst")); map.put("grand_total",
 * rs.getDouble("grand_total")); } return map; }
 * 
 * public List<Map<String, Object>> getInvoiceItems(String invoiceId) throws
 * SQLException { List<Map<String, Object>> list = new ArrayList<>(); String sql
 * = "SELECT * FROM invoice_items WHERE invoice_id = ?"; PreparedStatement stmt
 * = conn.prepareStatement(sql); stmt.setString(1, invoiceId); ResultSet rs =
 * stmt.executeQuery(); while (rs.next()) { Map<String, Object> item = new
 * HashMap<>(); item.put("product_name", rs.getString("product_name"));
 * item.put("quantity", rs.getInt("quantity")); item.put("date",
 * rs.getString("date")); item.put("unit_price", rs.getDouble("unit_price"));
 * item.put("total_price", rs.getDouble("total_price")); list.add(item); }
 * return list; }
 * 
 * }
 */
package com.inventory.dao;

import java.sql.*;
import java.util.*;

public class BillingDAO {
    private Connection conn;

    public BillingDAO(Connection conn) {
        this.conn = conn;
    }

    public int addInvoice(String customerName, String date, double gst, double grandTotal) throws SQLException {
        String sql = "INSERT INTO invoices (customer_name, date, gst, grand_total) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customerName);
            stmt.setString(2, date);
            stmt.setDouble(3, gst);
            stmt.setDouble(4, grandTotal);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  // invoice_id
            } else {
                throw new SQLException("Failed to retrieve invoice ID.");
            }
        }
    }

    public void addInvoiceItem(int invoiceId, int productId, int quantity, double unitPrice, double totalPrice, String date) throws SQLException {
        String getProductName = "SELECT name FROM products WHERE id = ?";
        String insertItem = "INSERT INTO invoice_items (invoice_id, product_id, product_name, quantity, unit_price, total_price, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String productName = "";
        try (PreparedStatement stmt = conn.prepareStatement(getProductName)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                productName = rs.getString("name");
            }
        }

        try (PreparedStatement stmt = conn.prepareStatement(insertItem)) {
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, productId);
            stmt.setString(3, productName);
            stmt.setInt(4, quantity);
            stmt.setDouble(5, unitPrice);
            stmt.setDouble(6, totalPrice);
            stmt.setString(7, date);
            stmt.executeUpdate();
        }
    }

    public List<Map<String, Object>> getAllInvoices() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM invoices ORDER BY invoice_id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> invoice = new HashMap<>();
                invoice.put("invoice_id", rs.getInt("invoice_id"));
                invoice.put("customer_name", rs.getString("customer_name"));
                invoice.put("date", rs.getString("date"));
                invoice.put("gst", rs.getDouble("gst"));
                invoice.put("grand_total", rs.getDouble("grand_total"));
                list.add(invoice);
            }
        }
        return list;
    }

    public List<Map<String, Object>> getAllProducts() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT id, name FROM products";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> product = new HashMap<>();
                product.put("id", rs.getInt("id"));
                product.put("name", rs.getString("name"));
                list.add(product);
            }
        }
        return list;
    }

    public Map<String, Object> getInvoiceById(String invoiceId) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT * FROM invoices WHERE invoice_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, invoiceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                map.put("invoice_id", rs.getInt("invoice_id"));
                map.put("customer_name", rs.getString("customer_name"));
                map.put("date", rs.getString("date"));
                map.put("gst", rs.getDouble("gst"));
                map.put("grand_total", rs.getDouble("grand_total"));
            }
        }
        return map;
    }

    public List<Map<String, Object>> getInvoiceItems(String invoiceId) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM invoice_items WHERE invoice_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, invoiceId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("product_name", rs.getString("product_name"));
                item.put("quantity", rs.getInt("quantity"));
                item.put("unit_price", rs.getDouble("unit_price"));
                item.put("total_price", rs.getDouble("total_price"));
                item.put("date", rs.getString("date"));
                list.add(item);
            }
        }
        return list;
    }
}
