package org.music.analysis.module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongHandler {
	public Song fetchSongInfo(String songName){
		Statement stmt = null;
		Connection conn = null;
		
		Song song = new Song();
		song.setName(songName);
		String id;
		String genres;
		
		try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql = "SELECT DISTINCT songName,youtubeId "
      				+ "FROM Songs "
      				+ "WHERE songName='" + songName + "' "
      				+ "LIMIT 1";
      		
        	ResultSet rs = stmt.executeQuery(sql);
        
	      	if(rs.next()){	            
	      		id = rs.getString("youtubeId");
	      		song.setId(id);
	      		
	      		sql = "SELECT DISTINCT name "
	      				+ "FROM genres "
	      				+ "WHERE songId='" + id + "'";
	      		
	      		rs = stmt.executeQuery(sql);
	      		if(rs.next()){
	      			genres=rs.getString("name");
	      			song.setGenres(genres);
	      		}		
	      	}
	      	
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
		 return song;
		
	}
	
	public ArrayList<String[]> fetchTopSong(){
		ArrayList<String[]> result=new ArrayList<String[]>();
		
		Statement stmt = null;
		Connection conn = null;
		
		int num=40;
		String[] sName = new String[num];
		String[] sView = new String[num];
        
		try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql = "SELECT DISTINCT songName,viewCount "
      				+ "FROM Songs "
      				+ "ORDER BY viewCount desc "
      				+ "Limit " + num;
        	ResultSet rs = stmt.executeQuery(sql);
        
        	int i=0;
	      	while(rs.next()){
        		//Retrieve by column name	            
        		sName[i]  = rs.getString("songName");
        		sView[i++]  = rs.getString("viewCount");
        		
        	}	
	      	result.add(sName);
	      	result.add(sView);
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
