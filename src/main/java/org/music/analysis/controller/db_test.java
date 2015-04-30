package org.music.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class db_test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// JDBC driver name and database URL
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
		final String DB_URL="jdbc:mysql://musicanalysisdata.cuxecej3mq5k.us-west-2.rds.amazonaws.com/Database_Project_DB";
		
		//  Database credentials
		final String USER = "KevinWill";
		final String PASS = "password";
		
		Statement stmt = null;
		Connection conn = null;
		PrintWriter out = response.getWriter();
		out.println("<h1>"+"java-db test"+"</h1>");
		// Register JDBC driver
	        try {
			Class.forName("com.mysql.jdbc.Driver");
	        	// Open a connection
		        conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
	       		// Execute SQL query
	        	stmt = conn.createStatement();
	        	String sql;
	      		sql = "SELECT * FROM Albums Limit 10";
	        	ResultSet rs = stmt.executeQuery(sql);
	        
	        	response.setContentType("text/html");
	        
	        	//PrintWriter out = response.getWriter();
	        	String title = "Database Album";
//	        	String docType =
//	          		"<!doctype html public \"-//w3c//dtd html 4.0 " +
//	           		"transitional//en\">\n";
//	           		out.println(docType +
//	           		"<html>\n" +
//	           		"<head><title>" + title + "</title></head>\n" +
//	           		"<body bgcolor=\"#f0f0f0\">\n" +
//	           		"<h1 align=\"center\">" + title + "</h1>\n");
		      	while(rs.next()){
	        		//Retrieve by column name	            
	            		String aName  = rs.getString("albumName");
	        		String aCountry = rs.getString("albumCountry");
	            		String aLanguage = rs.getString("albumLanguage");
	           		String barcode = rs.getString("albumBarCode");
	            		String sId = rs.getString("songId");
	            		int aId = rs.getInt("albumId");

	            		//Display values
	            		out.println("<h1>"+"albumName: " + aName + "<h1>");
	            		out.println("<h1>"+"albumCountry: " + aCountry + "<br>");
	            		out.println("<h1>"+"albumLanguage: " + aLanguage + "<br>");
	            		out.println("<h1>"+"albumId: " + aId + "<br>");
	            		out.println("<h1>"+"songId: " + sId + "<br>");
	        	}	
		
        	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<h1>"+"sql failed"+"</h1>");
			e.printStackTrace();
		}finally{
	         //finally block used to close resources
	         try{
	            if(stmt!=null)
	               stmt.close();
	         }catch(SQLException se2){
	         }// nothing we can do
	         try{
	            if(conn!=null)
	            conn.close();
	         }catch(SQLException se){
	            se.printStackTrace();
	         }//end finally try
	      } //end try
        
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
