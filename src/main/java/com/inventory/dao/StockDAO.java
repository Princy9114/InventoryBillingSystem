package com.inventory.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockDAO {
	private Connection conn;

	public StockDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Map<String, Object>> getAllStocks() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		String sql = "SELECT s.*, p.name AS product_name FROM stocks s JOIN products p ON s.product_id = p.id";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", rs.getInt("id"));
				map.put("product_name", rs.getString("product_name"));
				map.put("quantity", rs.getInt("quantity"));
				list.add(map);
			}
		}
		return list;
	}

	public void updateStock(int productId, int quantity) throws SQLException {
		String checkSql = "SELECT quantity FROM stocks WHERE product_id = ?";
		PreparedStatement checkStmt = conn.prepareStatement(checkSql);
		checkStmt.setInt(1, productId);
		ResultSet rs = checkStmt.executeQuery();

		if (rs.next()) {
			String updateSql = "UPDATE stocks SET quantity = quantity + ? WHERE product_id = ?";
			PreparedStatement updateStmt = conn.prepareStatement(updateSql);
			updateStmt.setInt(1, quantity);
			updateStmt.setInt(2, productId);
			updateStmt.executeUpdate();
		} else {
			String insertSql = "INSERT INTO stocks (product_id, quantity) VALUES (?, ?)";
			PreparedStatement insertStmt = conn.prepareStatement(insertSql);
			insertStmt.setInt(1, productId);
			insertStmt.setInt(2, quantity);
			insertStmt.executeUpdate();
		}
	}
}
