package com.inventory.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.inventory.model.User;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	public User login(String username, String password) throws SQLException {
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("role"));
			}
		}
		return null;
	}

	public boolean insertUser(User user) throws SQLException {
		String sql = "INSERT INTO users (username, password, role, status) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getRole());
		return stmt.executeUpdate() > 0;
	}

	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE status = 'active'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setRole(rs.getString("role"));
			u.setPassword(rs.getString("password")); // optional
			users.add(u);
		}
		return users;
	}

	public boolean updateUser(User user) throws SQLException {
		String sql = "UPDATE users SET username=?, password=?, role=? WHERE id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getRole());
		stmt.setInt(4, user.getId());
		return stmt.executeUpdate() > 0;
	}

	public boolean deleteUser(int id) throws SQLException {
		String sql = "UPDATE users SET status='inactive' WHERE id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		return stmt.executeUpdate() > 0;
	}
}
