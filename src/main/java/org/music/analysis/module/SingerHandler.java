package org.music.analysis.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.echonest.api.v4.EchoNestException;

public class SingerHandler {
	public ArrayList<Song> fetchSongs(String artistName) throws EchoNestException{
	 	ArrayList<Song> rstList = new ArrayList<Song>();
	 	String artistID;
	 	String spotifyArtistID;
	 	
	 	Song song = new Song();
	 	ArrayList<String> realArtist = fetchArtistID(artistName);
	 	artistName = realArtist.get(1);
	 	artistID = realArtist.get(0);
	 	spotifyArtistID = checkSpotify(artistName);//input: real artist!
	 	
		//Warning: put the artistID and artistName in song's ID and name to transport to front!
	 	song.setId(spotifyArtistID); 	
		song.setName(artistName);
		
		rstList.add(song);
		rstList.addAll(fetchSongsFromYouTube(artistID));
		
		return rstList;
	}
	
	public ArrayList<String> fetchArtistID(String artistName){
		Statement stmt = null;
		Connection conn = null;
		
		ArrayList<String> pair = new ArrayList<String>();
		
		try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	String artistID;
        	
        	/*sql =  "SELECT artistId,artistName "       
      				+ "FROM artists "
      				+ "WHERE artistName LIKE'%" + artistName + "%' ";*/
        	sql =  "SELECT artistId,artistName "       
      				+ "FROM artists "
      				+ "WHERE artistName ='" + artistName + "' ";
        	
        	System.out.println(sql);
        	System.out.println("----------------------------------------------------------------------------------");
        	ResultSet rs = stmt.executeQuery(sql);
        	
        	if(rs.next()){
        		artistID = rs.getString("artistId");
        		artistName = rs.getString("artistName");
        		pair.add(artistID);
        		pair.add(artistName);
        		return pair;
        	}
        	
        	sql =  "SELECT artistId,artistName "       
      				+ "FROM artists "
      				+ "WHERE artistName REGEXP'" + RegxMaker.make(artistName) + "' ";
        	
        	System.out.println(sql);
        	System.out.println("----------------------------------------------------------------------------------");
        	rs = stmt.executeQuery(sql);
        	
        	if(rs.next()){
        		artistID = rs.getString("artistId");
        		artistName = rs.getString("artistName");
        		pair.add(artistID);
        		pair.add(artistName);
        		return pair;
        	}
		}catch (SQLException e) {
			 System.out.println("sql failed!");
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
        return pair;
	}
	
	public ArrayList<Song> fetchSongsFromYouTube(String artistID){
		Statement stmt = null;
		Connection conn = null;
		
		ArrayList<Song> songList = new ArrayList<Song>();
		String youtubeID;
		String youtubeName;
		String songName;
		
		try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql =  "SELECT DISTINCT songName,youtubeName,youtubeId "       
      				+ "FROM songs "
      				+ "WHERE artistId='" + artistID + "' "
				    + "ORDER BY viewCount desc "
      				+ "LIMIT 10";
      		
      		System.out.println(sql);
        	System.out.println("----------------------------------------------------------------------------------");

            ResultSet rs = stmt.executeQuery(sql);
        
	      	while(rs.next()){
	      		Song song = new Song();
				
				youtubeID = rs.getString("youtubeId");
	      		youtubeName = rs.getString("youtubeName");
	      		songName = rs.getString("songName");
	      		
	      		song.setName(songName);
	      		song.setId(youtubeID);
	      		song.setYoutubeName(youtubeName);
	      		
	      		songList.add(song);
	      		//System.out.println(youtubeName);
	      	}
	      	
        }catch (SQLException e) {
			System.out.println("sql failed!");
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
	
	public static String checkSpotify(String sName) throws EchoNestException{
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
        	System.out.println("Spotify seach artist by name: "+sName);
        	//System.out.println(artist.getID());
        	//System.out.println(artist.getName());
        	System.out.println("----------------------------------------------------------------------------------");
            return artist.getID();
        }
        return null;
	}
	
	public static boolean checkSpotify_old(String singer){
		boolean ifExist = false;
		
		InputStream inputStream  = null;
		HttpURLConnection httpURLConnection = null;
		try {
			String sName = URLEncoder.encode(singer, "UTF-8");
			URL url = new URL("http://developer.echonest.com/api/v4/artist/search?api_key=ICY4WXWWLLSRR8ZIX&name="+sName);
			if(url != null){
				System.out.println(url);
				httpURLConnection = (HttpURLConnection)url.openConnection();
				httpURLConnection.setConnectTimeout(3000);
				httpURLConnection.setDoInput(true);
				httpURLConnection.setRequestMethod("GET");
				int responseCode = 400;
				try{
					responseCode = httpURLConnection.getResponseCode();
				}catch(Exception e){
					System.out.println("cannot get response code!");
					e.printStackTrace();
				}
				
				if(responseCode == 200){
					System.out.println("got it");
					inputStream = httpURLConnection.getInputStream();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("URL not right");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("io error");
		}
		
		JSONObject json = null;
		
		
		try {
			BufferedReader tmpReader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder total = new StringBuilder();
			String line;
			while ((line = tmpReader.readLine()) != null) 
			    total.append(line);
			try {
				json = new JSONObject(total.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		
		try {
			if(json.getJSONObject("response").getJSONArray("artists").length()>0)
				ifExist=true;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return ifExist;
	}
	
	public ArrayList<String> ajaxSearch(String content){
		Statement stmt = null;
		Connection conn = null;
		
		ArrayList<String> rst = new ArrayList<String>();
		try {
        	
        	conn=new ConnDB_Local().getConnection(); 
        	stmt = conn.createStatement();
        	String sql;
        	
      		sql = "SELECT DISTINCT artistName "
      				+ "FROM artists "
      				+ "WHERE artistName like '" + content + "%' "
	      			+ "ORDER BY artistPopularityAll desc "
				    + "LIMIT 5";
      		
        	ResultSet rs = stmt.executeQuery(sql);
        
	      	while(rs.next()){	            
	      		rst.add(rs.getString("artistName"));
	      	}
	      	
        }catch (SQLException e) {
        	System.out.println("sql failed!");
        	e.printStackTrace();
        }finally{
        	try{
        		if(stmt!=null)
        			stmt.close();
        	}catch(SQLException se2){}
	        try{
	            if(conn!=null)
	            conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }
        }
		return rst;
	}	

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
      				+ "FROM artists "
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
