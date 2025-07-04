package com.inventory.servlet;

import com.inventory.dao.CategoryDAO;
import com.inventory.utils.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/DeleteCategoryServlet")
public class DeleteCategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DBConnection.getConnection()) {
            CategoryDAO dao = new CategoryDAO(conn);
            dao.softDeleteCategory(id);
            response.sendRedirect("CategoryServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting category: " + e.getMessage());
        }
    }
}
