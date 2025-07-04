package com.inventory.servlet;

import com.inventory.dao.StockDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class StockServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem", "root", "Princy@123");

            StockDAO dao = new StockDAO(conn);
            List<Map<String, Object>> stockList = dao.getAllStocks();
            request.setAttribute("stockList", stockList);
            conn.close();
            request.getRequestDispatcher("stock.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading stock data: " + e.getMessage());
        }
    }
}