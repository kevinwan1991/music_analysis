package org.music.analysis.module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.echonest.api.v4.EchoNestException;

public class SongHandler {
	 public ArrayList<Song> fetchSongs(String sName) throws EchoNestException{
		 	ArrayList<Song> rstList = new ArrayList<Song>();
		 	
		 	Song song = new Song();
		 	String spotifySongID = checkSpotify(sName);
			song.setId(spotifySongID);
			song.setName(sName);
			
			rstList.add(song);
			rstList.addAll(fetchSongsFromYouTube(sName));
			
			return rstList;
	 }
	 
	 public ArrayList<Song> fetchSongsFromYouTube(String sName){
		 	Statement stmt = null;
			Connection conn = null;
			
			ArrayList<Song> songList = new ArrayList<Song>();
			String id;
			String youtubeName;
			
			try {
	        	
	        	conn=new ConnDB_Local().getConnection(); 
	        	stmt = conn.createStatement();
	        	String sql;
	        	
	      		sql =  "SELECT DISTINCT songName,youtubeName,youtubeId "       
	      				+ "FROM songs "
	      				+ "WHERE songName='" + sName + "' "
					    + "ORDER BY viewCount desc "
	      				+ "LIMIT 10";
	      		
	      		System.out.println(sql);
	        	ResultSet rs = stmt.executeQuery(sql);
	        
		      	while(rs.next()){
		      		Song song = new Song();
					
					id = rs.getString("youtubeId");
		      		youtubeName = rs.getString("youtubeName");
		      		
		      		song.setName(sName);
		      		song.setId(id);
		      		song.setYoutubeName(youtubeName);
		      		
		      		songList.add(song);
		      		System.out.println(youtubeName);
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
			 return songList;
	 }
	 
	 public com.echonest.api.v4.Playlist fetchSimilarSongs(String sID){
		    com.echonest.api.v4.Params p = new com.echonest.api.v4.Params();
		 	int results = 10;
		 	com.echonest.api.v4.EchoNestAPI en = new com.echonest.api.v4.EchoNestAPI("ICY4WXWWLLSRR8ZIX");
	        p.add("song_id", sID);
	        p.add("results", results);
	        p.add("type", "artist-radio");
	        
	        com.echonest.api.v4.Playlist playlist = null;
	        try {
				 playlist = en.createStaticPlaylist(p);
			} catch (EchoNestException e) {
				e.printStackTrace();
			}
	        
	        return playlist;
	 }
	
	 public String checkSpotify(String sName, String sArtist) throws EchoNestException{
		 	com.echonest.api.v4.Params p = new com.echonest.api.v4.Params();
		 	int results = 1;
		 	com.echonest.api.v4.EchoNestAPI en = new com.echonest.api.v4.EchoNestAPI("ICY4WXWWLLSRR8ZIX");
	        p.add("title", sName);
	        p.add("results", results);
	        p.add("artist", sArtist);
	        p.add("sort", "artist_hotttnesss-desc");
	        
	        List<com.echonest.api.v4.Song> songs = en.searchSongs(p);
	        if(songs == null || songs.size()==0)
	        	return null;
	        for (com.echonest.api.v4.Song song : songs) {
	        	System.out.println(song.getID());
	        	System.out.println(song.getTitle());
	            System.out.println(song.getArtistHotttnesss());
	            System.out.println(song.getArtistName());
	            System.out.println(song.getDuration());
	            return song.getID();
	        }
	        return null;
	 }
	 
	 public String checkSpotify(String sName) throws EchoNestException{
		 	com.echonest.api.v4.Params p = new com.echonest.api.v4.Params();
		 	int results = 1;
		 	com.echonest.api.v4.EchoNestAPI en = new com.echonest.api.v4.EchoNestAPI("ICY4WXWWLLSRR8ZIX");
	        p.add("title", sName);
	        p.add("results", results);
	        p.add("song_max_hotttnesss","0.9");
	        
	        List<com.echonest.api.v4.Song> songs = en.searchSongs(p);
	        if(songs == null || songs.size()==0)
	        	return null;
	        for (com.echonest.api.v4.Song song : songs) {
	        	System.out.println(song.getID());
	        	System.out.println(song.getTitle());
	            System.out.println(song.getArtistHotttnesss());
	            System.out.println(song.getArtistName());
	            System.out.println(song.getDuration());
	            return song.getID();
	        }
	        return null;
	 }
	
	 public ArrayList<String> ajaxSearch(String content){
                Statement stmt = null;
                Connection conn = null;

                ArrayList<String> rst = new ArrayList<String>();
                try {

                conn=new ConnDB_Local().getConnection();
                stmt = conn.createStatement();
                String sql;

                sql = "SELECT DISTINCT songName "
                                + "FROM songs "
                                + "WHERE songName like '" + content + "%' "
				+ "ORDER BY viewCount desc "
                                + "LIMIT 5";

                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                        rst.add(rs.getString("songName"));
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
                 return rst;

        }

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
        	
      		sql =  "SELECT DISTINCT songName,youtubeId "       
      				+ "FROM songs "
      				+ "WHERE songName='" + songName + "' "
				    + "ORDER BY viewCount desc "
      				+ "LIMIT 1";
      		
      		System.out.println(sql);
        	ResultSet rs = stmt.executeQuery(sql);
        
	      	if(rs.next()){	        
	      		System.out.println("i am here");
	      		id = rs.getString("youtubeId");
	      		System.out.println(id);
	      		song.setId(id);
	      		
	      		sql = "SELECT DISTINCT name "
	      				+ "FROM genres "
	      				+ "WHERE songId='" + id + "'";
	      		
	      		rs = stmt.executeQuery(sql);
	      		if(rs.next()){
	      			
	      			genres=rs.getString("name");
	      			System.out.println(genres);
	      			song.setGenres(GenreProcessor.act(genres));
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
      				+ "FROM songs "
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
