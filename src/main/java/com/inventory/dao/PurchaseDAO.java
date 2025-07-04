package com.inventory.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inventory.model.Purchase;

public class PurchaseDAO {
    private Connection conn;

    public PurchaseDAO(Connection conn) {
        this.conn = conn;
    }

    public void addPurchase(Purchase purchase) throws SQLException {
        if (purchase == null) {
            throw new IllegalArgumentException("Purchase cannot be null");
        }

        String query = "INSERT INTO purchases (product_id, company_id, quantity, date, unit_price, total_price, status) VALUES (?, ?, ?, ?, ?, ?, DEFAULT)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, purchase.getProductId());
            stmt.setInt(2, purchase.getCompanyId());
            stmt.setInt(3, purchase.getQuantity());
            stmt.setDate(4, purchase.getDate());
            stmt.setDouble(5, purchase.getUnitPrice());
            stmt.setDouble(6, purchase.getTotalPrice());
            stmt.executeUpdate();
        }
    }

    public List<Map<String, Object>> getAllPurchases() throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        String query = "SELECT p.id, p.quantity, p.date, p.unit_price, p.total_price, "
                     + "prod.name AS product_name, comp.name AS company_name "
                     + "FROM purchases p "
                     + "JOIN products prod ON p.product_id = prod.id "
                     + "JOIN companies comp ON p.company_id = comp.id "
                     + "WHERE p.status = 1"; // Assuming status = 1 means active
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", rs.getInt("id"));
                map.put("product_name", rs.getString("product_name"));
                map.put("company_name", rs.getString("company_name"));
                map.put("quantity", rs.getInt("quantity"));
                map.put("date", rs.getDate("date"));
                map.put("unit_price", rs.getDouble("unit_price"));
                map.put("total_price", rs.getDouble("total_price"));
                list.add(map);
            }
        }
        return list;
    }

    public Purchase getPurchaseById(int id) throws SQLException {
        String sql = "SELECT * FROM purchases WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Purchase purchase = new Purchase();
                    purchase.setId(rs.getInt("id"));
                    purchase.setProductId(rs.getInt("product_id"));
                    purchase.setCompanyId(rs.getInt("company_id"));
                    purchase.setQuantity(rs.getInt("quantity"));
                    purchase.setDate(rs.getDate("date"));
                    purchase.setUnitPrice(rs.getDouble("unit_price"));
                    purchase.setTotalPrice(rs.getDouble("total_price"));

                    return purchase;
                }
            }
        }
        return null;
    }

    public void updatePurchase(Purchase purchase) throws SQLException {
        if (purchase == null) {
            throw new IllegalArgumentException("Purchase cannot be null");
        }

        String sql = "UPDATE purchases SET product_id = ?, company_id = ?, quantity = ?, date = ?, unit_price = ?, total_price = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, purchase.getProductId());
            stmt.setInt(2, purchase.getCompanyId());
            stmt.setInt(3, purchase.getQuantity());
            stmt.setDate(4, purchase.getDate());
            stmt.setDouble(5, purchase.getUnitPrice());
            stmt.setDouble(6, purchase.getTotalPrice());
//            stmt.setInt(7, purchase.getStatus());
            stmt.setInt(7, purchase.getId());
            stmt.executeUpdate();
        }
    }

    public void softDeletePurchase(int id) throws SQLException {
        String sql = "UPDATE purchases SET status = 0 WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
