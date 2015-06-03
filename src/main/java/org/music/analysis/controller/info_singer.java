package org.music.analysis.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.music.analysis.module.SingerHandler;
import org.music.analysis.module.Song;
import org.music.analysis.module.SongHandler;

import com.echonest.api.v4.EchoNestException;

public class info_singer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public info_singer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sName = "";
		String sID;
		sName = request.getParameter("singerName");
		
		SingerHandler shr = new SingerHandler();
		try {
			if((sID=SingerHandler.checkSpotify(sName))!=null){
				ArrayList<Song> songList = shr.fetchSongs(sName);
				request.setAttribute("songList", songList);
				request.getRequestDispatcher("singer_trial.jsp").forward(request, response);
			}else{
				Song song = new SongHandler().fetchSongInfo("Love the way you lie");
				System.out.println(song.getUrl());
				request.setAttribute("obj_song", song);
				
				request.getRequestDispatcher("song.jsp").forward(request, response);
			}
		} catch (EchoNestException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
