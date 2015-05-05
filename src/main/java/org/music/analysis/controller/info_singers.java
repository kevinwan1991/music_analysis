package org.music.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.music.analysis.module.*;
import org.music.analysis.module.Singers;
import org.music.analysis.module.Song;
import org.music.analysis.module.Songs;


/**
 * Servlet implementation class info_singers
 */
public class info_singers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public info_singers() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ArrayList<String[]> singerSet=new SingerHandler().fetchTopSinger();
        String[] singerName = singerSet.get(0);
        String[] singerPop = singerSet.get(1); 
        
        
		Singers singers = new Singers();
//		String[] singerName= {"LI Zhi", "XU Wei", "CAI Jianya", "ZHENG Jun","SUN Yanzi", "CUI Jian", "ZHANG Liangying", "ZHANG Chu", "WANG Feng", "HUANG Jiaju"};
//		ArrayList<Singer> tmp_singer = new ArrayList<Singer>();
		for(int i=0; i<singerName.length; i++){
			singers.getSingerList().add(new Singer());
			singers.getSingerList().get(i).setName(singerName[i]);
			singers.getSingerList().get(i).setPopularity(Integer.parseInt(singerPop[i]));
		}
//		singers.setSingerList(tmp_singer);
//		String str = "hello";
		
		
		
		ArrayList<String[]> songSet = new SongHandler().fetchTopSong();
		String[] sName = songSet.get(0);
		String[] sView = songSet.get(1);
        
        
		Songs songs = new Songs();
//		String[] songName= {"Raining", "Homeland", "Falling", "Pure Love","Encounter", "Nothing to lose", "Single", "Dear Sister", "Flower With Fire", "Truly Loving You"};
		String[] albumName= {"F", "nayinian", "dialogsbetweenangelsdemons", "purelove", "Themoment", "Rockinmyway", "listentojanezlive", "Zhangchu", "flowerwithfire", "beyondiv"};
//		ArrayList<Singer> tmp_singer = new ArrayList<Singer>();
		for(int i=0; i<sName.length; i++){
			songs.getSongList().add(new Song());
			songs.getSongList().get(i).setName(sName[i]);
			songs.getSongList().get(i).setAlbumBelong("");
			songs.getSongList().get(i).setViews(Integer.parseInt(sView[i]));
		}
		
		JSONObject json_obj = new JSONObject();
		try {
			for(int i=0; i<15; i++){
				json_obj.put("word", "XU Wei");
				json_obj.put("weight",70);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		singers.setSingerList(tmp_singer);
//		String str = "hello";
		request.setAttribute("singers", singers);
		request.setAttribute("songs", songs);
		request.setAttribute("json_singers", json_obj);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
