package com.inventory.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.dao.PurchaseDAO;
import com.inventory.utils.DBConnection;

@WebServlet("/DeletePurchaseServlet")
public class DeletePurchaseServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try (Connection conn = DBConnection.getConnection()) {
            PurchaseDAO dao = new PurchaseDAO(conn);
            dao.softDeletePurchase(id);
            response.sendRedirect("PurchaseServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
