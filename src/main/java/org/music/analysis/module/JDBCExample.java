package org.music.analysis.module;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JDBCExample extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
 
	PrintWriter out = response.getWriter();
	out.println("<h1>"+"-------- MySQL JDBC Connection Testing ------------"+"</h1>");
 
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		out.println("<h1>"+"Where is your MySQL JDBC Driver?"+"</h1>");
		e.printStackTrace();
		return;
	}
 
	out.println("<h1>"+"MySQL JDBC Driver Registered!"+"</h1>");
	Connection connection = null;
 
	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://musicanalysisdata.cuxecej3mq5k.us-west-2.rds.amazonaws.com/Database_Project_DB","KevinWill","password");//jdbc:mysql://localhost:3306/mysql", "root", "password");
//"jdbc:mysql://musicanalysisdata.cuxecej3mq5k.us-west-2.rds.amazonaws.com/Database_Project_DB","KevinWill","password"); 
	} catch (SQLException e) {
		out.println("<h1>"+"Connection Failed! Check output console"+"</h1>");
		e.printStackTrace();
		return;
	}
 
	if (connection != null) {
		out.println("<h1>"+"You made it, take control your database now!"+"</h1>");
	} else {
		out.println("<h1>"+"Failed to make connection!"+"</h1>");
	}
  }
}
