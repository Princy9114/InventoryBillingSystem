package com.inventory.servlet;

import com.inventory.dao.CategoryDAO;
import com.inventory.model.Category;
import com.inventory.utils.DBConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/UpdateCategoryServlet")
public class UpdateCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Category updatedCategory = new Category(id, name, description, 1);
            CategoryDAO dao = new CategoryDAO(conn);
            dao.updateCategory(updatedCategory);

            response.sendRedirect("CategoryServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error updating category: " + e.getMessage());
        }
    }
}
