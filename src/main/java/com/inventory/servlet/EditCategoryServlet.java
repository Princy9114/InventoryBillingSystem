package com.inventory.servlet;

import com.inventory.dao.CategoryDAO;
import com.inventory.model.Category;
import com.inventory.utils.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/EditCategoryServlet")
public class EditCategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DBConnection.getConnection()) {
            CategoryDAO dao = new CategoryDAO(conn);
            Category category = dao.getCategoryById(id);
            request.setAttribute("category", category);
            request.getRequestDispatcher("edit_category.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading category for editing: " + e.getMessage());
        }
    }
}
