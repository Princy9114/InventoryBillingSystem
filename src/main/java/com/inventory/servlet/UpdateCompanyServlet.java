package com.inventory.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.dao.CompanyDAO;
import com.inventory.model.Company;
import com.inventory.utils.DBConnection;

@WebServlet("/UpdateCompanyServlet")
public class UpdateCompanyServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (Connection conn = DBConnection.getConnection()) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");

			Company company = new Company(id, name, address, contact);
			CompanyDAO dao = new CompanyDAO(conn);
			dao.updateCompany(company);

			response.sendRedirect("CompanyServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");

            CompanyDAO dao = new CompanyDAO(conn);
            List<Map<String, Object>> companyList = dao.getAllCompanies();
            request.setAttribute("companyList", companyList);
            conn.close();
            request.getRequestDispatcher("company.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading company data: " + e.getMessage());
        }
    }
}
