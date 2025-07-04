package com.inventory.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inventory.model.Company;
import com.inventory.model.Product;

public class CompanyDAO {
	private Connection conn;

	public CompanyDAO(Connection conn) {
		this.conn = conn;
	}

	public void addCompany(Company company) throws SQLException {
		String query = "INSERT INTO companies (name, address, contact, status) VALUES (?, ?, ?, DEFAULT)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getAddress());
			stmt.setString(3, company.getContact());
			stmt.executeUpdate();
		}
	}

	public List<Map<String, Object>> getAllCompanies() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		String query = "SELECT id, name, address, contact FROM companies WHERE status = 1";
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", rs.getInt("id"));
				map.put("name", rs.getString("name"));
				map.put("address", rs.getString("address"));
				map.put("contact", rs.getString("contact"));
				list.add(map);
			}
		}
		return list;
	}
	
	public Company getCompanyById(int id) throws SQLException {
        String sql = "SELECT * FROM companies WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setAddress(rs.getString("address"));
                company.setContact(rs.getString("contact"));
                
                return company;
            }
        }
        return null;
    }
	
	public void updateCompany(Company company) throws SQLException {
		String sql = "UPDATE companies SET name = ?, address = ?, contact = ? WHERE id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getAddress());
			stmt.setString(3, company.getContact());
			stmt.setInt(4, company.getId());
			stmt.executeUpdate();
		}
	}
	
	public void softDeleteCompany(int id) throws SQLException{
		 String sql = "UPDATE companies SET status = 0 WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        stmt.executeUpdate();
	    }
	}
}






