package com.inventory.servlet;

import com.inventory.dao.ProductDAO;
import com.inventory.utils.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DBConnection.getConnection()) {
            ProductDAO dao = new ProductDAO(conn);
            dao.softDeleteProduct(id);
            response.sendRedirect("ProductServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
