package com.inventory.servlet;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import com.inventory.utils.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        int id = Integer.parseInt(request.getParameter("id"));

	        Connection conn = DBConnection.getConnection();
	        ProductDAO dao = new ProductDAO(conn);
	        Product product = dao.getProductById(id);

	        request.setAttribute("product", product);
	        request.getRequestDispatcher("edit_product.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Error: " + e.getMessage());
	    }
	}

}
