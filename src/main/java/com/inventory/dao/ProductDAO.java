package com.inventory.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inventory.model.Product;

public class ProductDAO {
	private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public void addProduct(Product p) throws SQLException {
        String sql = "INSERT INTO products (name, company_id, category_id, unit_price, purchase_price, gst, description, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getName());
            stmt.setInt(2, p.getCompanyId());
            stmt.setInt(3, p.getCategoryId());
            stmt.setDouble(4, p.getUnitPrice());
            stmt.setDouble(5, p.getPurchasePrice());
            stmt.setDouble(6, p.getGst());
            stmt.setString(7, p.getDescription());
            stmt.setInt(8, p.getStatus());
            stmt.executeUpdate();
        }
    }

	
	  public List<Map<String, Object>> getAllProductsWithDetails() throws
	  SQLException { List<Map<String, Object>> list = new ArrayList<>(); String sql
	  =
	  "SELECT p.*, c.name AS company_name, ct.name AS category_name FROM products p "
	  + "JOIN companies c ON p.company_id = c.id " +
	  "JOIN categories ct ON p.category_id = ct.id WHERE p.status = 1"; try (Statement stmt =
	  conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) { while
	  (rs.next()) { Map<String, Object> map = new HashMap<>(); map.put("id",
	  rs.getInt("id")); map.put("name", rs.getString("name"));
	  map.put("company_name", rs.getString("name"));
	  map.put("category_name", rs.getString("category_name"));
	  map.put("unit_price", rs.getDouble("unit_price")); map.put("purchase_price",
	  rs.getDouble("purchase_price")); map.put("gst", rs.getDouble("gst"));
	  map.put("description", rs.getString("description")); list.add(map); } }
	  return list; }
	 

    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCompanyId(rs.getInt("company_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setUnitPrice(rs.getDouble("unit_price"));
                product.setPurchasePrice(rs.getDouble("purchase_price"));
                product.setGst(rs.getDouble("gst"));
                product.setDescription(rs.getString("description"));

                return product;
            }
        }
        return null;
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, unit_price = ?, purchase_price = ?, gst = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getUnitPrice());
            stmt.setDouble(3, product.getPurchasePrice());
            stmt.setDouble(4, product.getGst());
            stmt.setString(5, product.getDescription());
            stmt.setInt(6, product.getId());
            stmt.executeUpdate();
        }
    }

	public void softDeleteProduct(int id) throws SQLException {
	    String sql = "UPDATE products SET status = 0 WHERE id = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        stmt.executeUpdate();
	    }
	}
	
}
