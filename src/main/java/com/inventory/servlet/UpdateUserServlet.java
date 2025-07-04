package com.inventory.servlet;

import com.inventory.dao.UserDAO;
import com.inventory.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");

            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            UserDAO dao = new UserDAO(conn);
            dao.updateUser(user);

            conn.close();
            response.sendRedirect("UserServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");

			UserDAO dao = new UserDAO(conn);
			List<User> userList = dao.getAllUsers();
			request.setAttribute("userList", userList);

			conn.close();
			request.getRequestDispatcher("users.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		}
	}

}
