package org.music.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.music.analysis.module.*;


public class info_songs extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public info_songs() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//String songName = (String) request.getAttribute("songName");
		String songName="Baby";
		out.println("<h1>"+"songName: " + songName + "<h1>");
		Song song = new SongHandler().fetchSongInfo(songName);
		
		
		out.println("<h1>"+"songName: " + songName + "<h1>");
		out.println("<h1>"+"genres: " + song.getGenres() + "<h1>");
		
		//request.setAttribute("song", song);
		//request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}



