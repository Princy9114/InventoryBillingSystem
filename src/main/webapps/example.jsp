<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<%
    // Database connection details
    String driver = "com.mysql.cj.jdbc.Driver"; // Example for MySQL
    String connectionUrl = "jdbc:mysql://localhost:3306/inventorysystem";
    String userId = "root";
    String password = "Princy@123";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        Class.forName(driver).newInstance(); // Load the driver
        connection = DriverManager.getConnection(connectionUrl, userId, password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM users"); // Replace with your table and columns
		
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>password</th><th>role</th><th>status</th></tr>");

        while (resultSet.next()) {
            out.println("<tr>");
            out.println("<td>" + resultSet.getInt("id") + "</td>");
            out.println("<td>" + resultSet.getString("username") + "</td>");
            out.println("<td>" + resultSet.getString("password") + "</td>");
            out.println("<td>" + resultSet.getString("role") + "</td>");
            out.println("<td>" + resultSet.getString("status") + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
	
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
        if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
        if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
%>