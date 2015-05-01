package org.music.analysis.module;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class info_songs
 */
public class info_songs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public info_songs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Songs songs = new Songs();
		String[] sName= {"LI Zhi", "XU Wei", "CAI Jianya", "ZHENG Jun","SUN Yanzi", "CUI Jian", "ZHANG Liangying", "ZHANG Chu", "WANG Feng", "HUANG Jiaju"};
//		ArrayList<Singer> tmp_singer = new ArrayList<Singer>();
		for(int i=0; i<10; i++){
			songs.getSongList().add(new Song());
			songs.getSongList().get(i).setName(sName[i]);
			songs.getSongList().get(i).setViews(i*10);
		}
//		singers.setSingerList(tmp_singer);
//		String str = "hello";
		request.setAttribute("songs", songs);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}



