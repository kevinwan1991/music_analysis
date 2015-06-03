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


public class Spotify_comparor {
	public List<Song> youtubeFetchBySinger(String sName){
		SingerHandler shr = new SingerHandler();
		ArrayList<Song> songList = new ArrayList<Song>();
		
		try {
			songList = shr.fetchSongs(sName);	
			songList.remove(0);
		}catch (EchoNestException e) {
			e.printStackTrace();
		}
		return songList;
	}
	
	/** sName is the artist name**/
	public List<com.echonest.api.v4.Song> SpotifyFetchByName(String sName) throws EchoNestException{
		String sID = SpotifyFetchIDBySinger(sName);
		return SpotifyFetchByID(sID);
	}
	
	public List<com.echonest.api.v4.Song> SpotifyFetchByID(String sID) throws EchoNestException{
		com.echonest.api.v4.Params p = new com.echonest.api.v4.Params();
	 	int results = 10;
	 	com.echonest.api.v4.EchoNestAPI en = new com.echonest.api.v4.EchoNestAPI("ICY4WXWWLLSRR8ZIX");
        p.add("artist_id", sID);
        p.add("results", results);
        p.add("sort", "song_hotttnesss-desc");
        
        List<com.echonest.api.v4.Song> songList = en.createStaticPlaylist(p).getSongs();
        if(songList == null || songList.size()==0)
        	return null;
        //for (com.echonest.api.v4.Song song : songList) {
        	//System.out.println("Spotify searched results: "+song.getTitle());
        	//System.out.println(artist.getID());
        	//System.out.println(artist.getName());
        	//System.out.println("----------------------------------------------------------------------------------");
        //}
        return songList;
	}
	
	public String SpotifyFetchIDBySinger(String sName) throws EchoNestException{
		com.echonest.api.v4.Params p = new com.echonest.api.v4.Params();
	 	int results = 1;
	 	com.echonest.api.v4.EchoNestAPI en = new com.echonest.api.v4.EchoNestAPI("ICY4WXWWLLSRR8ZIX");
        p.add("name", sName);
        p.add("results", results);
        p.add("sort", "hotttnesss-desc");
        
        List<com.echonest.api.v4.Artist> artists = en.searchArtists(p);
        if(artists == null || artists.size()==0)
        	return null;
        for (com.echonest.api.v4.Artist artist : artists) {
        	//System.out.println("Spotify seach artist by name: "+sName);
        	//System.out.println(artist.getID());
        	//System.out.println(artist.getName());
        	//System.out.println("----------------------------------------------------------------------------------");
            return artist.getID();
        }
        return null;
	}
	
	public List<Double> existRate(){
		Statement stmt = null;
		Connection conn = null;
		Spotify_comparor sc = new Spotify_comparor();
		
		String singerName;
		//List<com.echonest.api.v4.Song> songList;
		List<Double> rstList = new ArrayList<Double>();
		
        try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql = "SELECT DISTINCT artistId,artistName,artistPopularityAll "
      				+ "FROM artists "
      				+ "group by artistId "
      				+ "ORDER BY artistPopularityAll desc ";
      				//+ "LIMIT 200";
      				
        	ResultSet rs = stmt.executeQuery(sql);
        
        	int i=1;
        	int counter=1;
        	double hitNum=0;
	      	while(rs.next()){
	      		System.out.println(rs.getString("artistName"));
	      		if(i%200 == 0){
	      			System.out.println("-------------------------------------------------------------------------");
		      		singerName = rs.getString("artistName");
		      		try {
						if(sc.artistExist(singerName)==1){
							hitNum++;
							System.out.println("Hitted!");
						}						
					} catch (EchoNestException e) {
						e.printStackTrace();
					}
		      		counter++;
		      		TimeUnit.SECONDS.sleep(3);
	      		}
	      		i++;
	      		if(counter%501==0){	
	      			rstList.add(hitNum/(double)500);
	      			System.out.println("Hitted num : "+hitNum);
	      			System.out.println("Hitted rate: "+hitNum/(double)500);
	      			hitNum = 0;
	      			counter = 1;
	      		}
        	}	
	      	System.out.println("total number of line is: "+(i-1));
	      	
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
        return rstList;
	}
	
	public int artistExist(String artistName) throws EchoNestException{
		com.echonest.api.v4.Params p = new com.echonest.api.v4.Params();
	 	int results = 1;
	 	com.echonest.api.v4.EchoNestAPI en = new com.echonest.api.v4.EchoNestAPI("ICY4WXWWLLSRR8ZIX");
        p.add("name", artistName);
        p.add("results", results);
        p.add("sort", "hotttnesss-desc");
        
        List<com.echonest.api.v4.Artist> artists = en.searchArtists(p);
        if(artists == null || artists.size()==0)
        	return 0;
        return 1;
	}
	
	public void commonTask(){
		Statement stmt = null;
		Connection conn = null;
		Spotify_comparor sc = new Spotify_comparor();
		
		String singerName;
		String singerID;
		String singerPop;
		List<com.echonest.api.v4.Song> songList;
		
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
		      			if((songList = sc.SpotifyFetchByName(singerName))!=null){
			      			int k=1;
			      			for (com.echonest.api.v4.Song song : songList) {
			      				System.out.println("Spotify searched results: ");
			      				System.out.println("Spotify searched title  : "+song.getTitle());
			      				System.out.println("Spotify searched hotness: "+song.getSongHotttnesss());
			      				try{
			      					sql = "INSERT INTO spotify VALUES (?,?,?,?,?,?)";
				      				PreparedStatement ps = conn.prepareStatement(sql);
				      				ps.setString(1,singerID);
				      				ps.setString(2,singerName);
				      				ps.setString(3,singerPop);
				      				ps.setString(4,song.getTitle());
				      				ps.setString(5,song.getSongHotttnesss()+"");
				      				ps.setString(6,""+k++);
			      				
				      				ps.executeUpdate();
				      	        	System.out.println("----------------------------------------------------------------------------------");
			      				} catch (SQLException e) {
			      					System.out.println("sql failed");
			      					e.printStackTrace();
			      				}
			      			}
		      			}
		      		}catch (EchoNestException e) {
		    			e.printStackTrace();
		    		} 
		      		
		      		TimeUnit.SECONDS.sleep(5);
	      		}
	      		i++;
        	}	
	      	System.out.println("total number of line is: "+i);
        } catch (SQLException e) {
			System.out.println("sql failed");
			e.printStackTrace();
		} catch (InterruptedException e) {
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
		Spotify_comparor sc = new Spotify_comparor();
		//sc.commonTask();
		List<Double> rst = sc.existRate();
		System.out.println(rst.toString());
	}
}
