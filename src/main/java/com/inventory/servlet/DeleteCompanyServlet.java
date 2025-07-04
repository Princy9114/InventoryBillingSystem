package com.inventory.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.dao.CompanyDAO;
import com.inventory.utils.DBConnection;

/**
 * Servlet implementation class DeleteCompanyServlet
 */
@WebServlet("/DeleteCompanyServlet")
public class DeleteCompanyServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try (Connection conn = DBConnection.getConnection()) {
            CompanyDAO dao = new CompanyDAO(conn);
            dao.softDeleteCompany(id);
            response.sendRedirect("CompanyServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
