package org.music.analysis.module;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class info_song
 */
public class info_song extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public info_song() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sName = "";
		sName = request.getParameter("songName");
		String url = "";
		url = "https://youtu.be/aoSF7qQjPpc";
		Song song = new Song();
		song.setAlbumBelong("Cishicike");
		song.setLanguage("Chinese");
		song.setName(sName);
		song.setGenre("country");
		song.setUrl(url);
		
		request.setAttribute("obj_song", song);
		
		request.getRequestDispatcher("song.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
