package com.inventory.dao;

import com.inventory.model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDAO {
    private Connection conn;

    public CategoryDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO categories (name, description, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getStatus());
            stmt.executeUpdate();
        }
    }

    public List<Map<String, Object>> getAllCategories() throws SQLException {
        List<Map<String, Object>> categories = new ArrayList<>();
        String query = "SELECT * FROM categories WHERE status = 1"; 
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> category = new HashMap<>();
                category.put("id", rs.getInt("id"));
                category.put("name", rs.getString("name"));
                category.put("description", rs.getString("description")); 
                categories.add(category);
            }
        }
        return categories;
    }

    public Category getCategoryById(int id) throws SQLException {
        Category category = null;
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                    category.setStatus(rs.getInt("status"));
                }
            }
        }
        return category;
    }

    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE categories SET name = ?, description = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getStatus());
            stmt.setInt(4, category.getId());
            stmt.executeUpdate();
        }
    }

    public void softDeleteCategory(int id) throws SQLException {
        String sql = "UPDATE categories SET status = 0 WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
