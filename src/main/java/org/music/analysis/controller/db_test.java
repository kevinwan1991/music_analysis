package org.music.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.music.analysis.module.ConnDB;
import org.music.analysis.module.ConnDB_Local;

public class db_test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement stmt = null;
		Connection conn = null;
		PrintWriter out = response.getWriter();
		
        try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql = "SELECT DISTINCT songName,viewCount "
      				+ "FROM Songs_new "
      				+ "ORDER BY viewCount desc "
      				+ "Limit 10";
        	ResultSet rs = stmt.executeQuery(sql);
        
        	response.setContentType("text/html");
        
	      	while(rs.next()){
        		//Retrieve by column name	            
        		String aName  = rs.getString("songName");
        		String aPop  = rs.getString("viewCount");
        		
        		//Display values
        		out.println("<h1>"+"artistName: " + aName + "<h1>");
        		out.println("<h1>"+"artistPop: " + aPop + "<h1>");
        	}	
	      	
        }catch (SQLException e) {
			out.println("<h1>"+"sql failed"+"</h1>");
			e.printStackTrace();
		}finally{
	         try{
	            if(stmt!=null)
	               stmt.close();
	         }catch(SQLException se2){
	         }
	         try{
	            if(conn!=null)
	            conn.close();
	         }catch(SQLException se){
	            se.printStackTrace();
	         }
	      }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
