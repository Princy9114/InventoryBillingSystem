package com.inventory.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.dao.CompanyDAO;
import com.inventory.model.Company;
import com.inventory.utils.DBConnection;

@WebServlet("/EditCompanyServlet")
public class EditCompanyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        int id = Integer.parseInt(request.getParameter("id"));

	        Connection conn = DBConnection.getConnection();
	        CompanyDAO dao = new CompanyDAO(conn);
	        Company company = dao.getCompanyById(id);

	        request.setAttribute("company", company);
	        request.getRequestDispatcher("edit_company.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Error: " + e.getMessage());
	    }
	}
}
