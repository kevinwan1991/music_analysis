package org.music.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.music.analysis.module.*;

public class JDBCExample extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
 
	PrintWriter out = response.getWriter();
	out.println("<h4>"+"-------- MySQL JDBC Connection Testing ------------"+"</h4>");
 
//	try {
//
//		Class.forName("com.mysql.jdbc.Driver");
//	} catch (ClassNotFoundException e) {
//		out.println("<h4>"+"Where is your MySQL JDBC Driver?"+"</h4>");
//		e.printStackTrace();
//		return;
//	}
// 
	//out.println("<h4>"+"MySQL JDBC Driver Registered!"+"</h4>");
	Connection connection = null;
	connection=new ConnDB().getConnection(); 
//	try {
//		connection = DriverManager
//		.getConnection("jdbc:mysql://musicanalysisdata.cuxecej3mq5k.us-west-2.rds.amazonaws.com/Database_Project_DB","KevinWill","password");
//	} catch (SQLException e) {
//		out.println("<h4>"+"Connection Failed! Check output console"+"</h4>");
//		e.printStackTrace();
//		return;
//	}
// 
	if (connection != null) {
		out.println("<h4>"+"You made it, take control your database now!"+"</h4>");
	} else {
		out.println("<h4>"+"Failed to make connection!"+"</h4>");
	}
  }
}
