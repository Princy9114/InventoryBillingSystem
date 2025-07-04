package com.inventory.servlet;

import com.inventory.dao.CategoryDAO;
import com.inventory.model.Category;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");
            CategoryDAO dao = new CategoryDAO(conn);
            dao.addCategory(new Category(0, name, description, 1));
            conn.close(); // Close the connection after adding the company

            // Redirect to the same servlet to fetch the updated company list
            response.sendRedirect("CategoryServlet"); // Redirect to the doGet method
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

            CategoryDAO dao = new CategoryDAO(conn);
            List<Map<String, Object>> categoryList = dao.getAllCategories();
            request.setAttribute("categoryList", categoryList);
            conn.close();
            request.getRequestDispatcher("category.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading category data: " + e.getMessage());
        }
    }
}
