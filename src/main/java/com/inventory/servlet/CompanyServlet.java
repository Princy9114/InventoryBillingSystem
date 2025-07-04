package com.inventory.servlet;

import com.inventory.dao.CompanyDAO;
import com.inventory.model.Company;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class CompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");
            CompanyDAO dao = new CompanyDAO(conn);
            dao.addCompany(new Company(0, name, address, contact));
            conn.close(); // Close the connection after adding the company

            // Redirect to the same servlet to fetch the updated company list
            response.sendRedirect("CompanyServlet"); // Redirect to the doGet method
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error adding company: " + e.getMessage());
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
