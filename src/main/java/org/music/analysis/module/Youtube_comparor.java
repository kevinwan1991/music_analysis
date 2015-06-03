package org.music.analysis.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.echonest.api.v4.EchoNestException;

public class Youtube_comparor {
	public void commonTask(){
		Statement stmt = null;
		Connection conn = null;
		//Spotify_comparor sc = new Spotify_comparor();
		SingerHandler sh = new SingerHandler();
		
		String singerName;
		String singerID;
		String singerPop;
		//List<com.echonest.api.v4.Song> songList;
		List<Song> songList;
		
        try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql = "SELECT DISTINCT artistId,artistName,artistPopularityAll "
      				+ "FROM artists "
      				//+ "group by artistId "
      				+ "ORDER BY artistPopularityAll desc ";
      				
        	ResultSet rs = stmt.executeQuery(sql);
        
        	int i=0;
	      	while(rs.next()){
	      		System.out.println(rs.getString("artistName"));
	      		if(i%500 == 0){
		      		singerID = rs.getString("artistId");
		      		singerName = rs.getString("artistName");
		      		singerPop = rs.getString("artistPopularityAll");
		      		
		      		try{
		      			if((songList = sh.fetchSongsFromYouTube(singerID))!=null){
			      			int k=1;
			      			try{
			      				for (Song song : songList) {
				      				System.out.println("Spotify searched results: ");
				      				System.out.println("Spotify searched title  : "+song.getName());
				      				System.out.println("Spotify searched hotness: "+song.getViews());
				      				
				      				sql = "INSERT INTO youtube VALUES (?,?,?,?,?,?)";
				      				PreparedStatement ps = conn.prepareStatement(sql);
				      				ps.setString(1,singerID);
				      				ps.setString(2,singerName);
				      				ps.setString(3,singerPop);
				      				ps.setString(4,song.getName());
				      				ps.setString(5,song.getViews()+"");
				      				ps.setString(6,""+k++);
			      				
				      				ps.executeUpdate();
				      	        	System.out.println("----------------------------------------------------------------------------------");
				      	        }
			      			}catch (SQLException e) {
			      				System.out.println("sql failed");
			      				e.printStackTrace();
			      			} 
			      		}
		      		}catch(Exception ex){
		      			ex.printStackTrace();
		      		}
		      		TimeUnit.SECONDS.sleep(1);
	      		}
	      		i++;
        	}	
	      	System.out.println("total number of line is: "+i);
        }catch (SQLException e) {
				System.out.println("sql failed");
				e.printStackTrace();
		}catch (InterruptedException e) {
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
	
	public static void main(String args[]){
		Youtube_comparor yc = new Youtube_comparor();
		yc.commonTask();
	}
}