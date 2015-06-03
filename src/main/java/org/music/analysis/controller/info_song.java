package org.music.analysis.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.music.analysis.module.Song;
import org.music.analysis.module.SongHandler;

import com.echonest.api.v4.EchoNestException;


public class info_song extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public info_song() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sName = "";
		String sID = "";
		
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("UTF-8");
		
		sName = new String(request.getParameter("songName").getBytes("ISO8859-1"),"gb2312");
		System.out.println(sName);
		
		if(request.getParameter("tid")!=null){
			String tid = request.getParameter("tid");
			String songName = request.getParameter("songName");
			String singer =  request.getParameter("singerName");
			
			request.setAttribute("tid", tid);
			request.setAttribute("songName", songName);
			request.setAttribute("singerName", singer);
			request.getRequestDispatcher("song_detail.jsp").forward(request, response);
			return;
		}
		
		SongHandler shr = new SongHandler();
		
		try {
			if((sID = shr.checkSpotify(sName))!=null){
				System.out.println("found");
				ArrayList<Song> songs = shr.fetchSongs(sName);
				
				request.setAttribute("songList", songs);
				request.getRequestDispatcher("Song_trial.jsp").forward(request, response);
			}else{
				System.out.println("not found");
				Song song = new SongHandler().fetchSongInfo(sName);
				request.setAttribute("obj_song", song);
				request.getRequestDispatcher("song.jsp").forward(request, response);
			}
		} catch (EchoNestException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
