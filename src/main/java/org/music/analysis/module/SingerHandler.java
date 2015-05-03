package org.music.analysis.module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SingerHandler {
	public ArrayList<String[]> fetchTopSinger(){
		ArrayList<String[]> result=new ArrayList<String[]>();
		
		Statement stmt = null;
		Connection conn = null;
		
		int num = 40;
		String[] singerName=new String[num]; 
		String[] singerPop =new String[num]; 
		
        try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql = "SELECT DISTINCT artistName,artistPopularityAll "
      				+ "FROM Artists "
      				+ "ORDER BY artistPopularityAll desc "
      				+ "Limit "+num;
        	ResultSet rs = stmt.executeQuery(sql);
        
        
        	int i=0;
	      	while(rs.next()){
        		//Retrieve by column name	
	      		singerName[i]=rs.getString("artistName");
	      		singerPop[i++]=rs.getString("artistPopularityAll");
        	}	
	      	result.add(singerName);
	      	result.add(singerPop);
	      	
        }catch (SQLException e) {
			System.out.println("<h1>"+"sql failed"+"</h1>");
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
        return result;
	}
}
