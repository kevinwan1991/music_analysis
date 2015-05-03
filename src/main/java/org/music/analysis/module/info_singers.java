package org.music.analysis.module;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class info_singers
 */
public class info_singers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public info_singers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Singers singers = new Singers();
		String[] singerName= {"LI Zhi", "XU Wei", "CAI Jianya", "ZHENG Jun","SUN Yanzi", "CUI Jian", "ZHANG Liangying", "ZHANG Chu", "WANG Feng", "HUANG Jiaju"};
//		ArrayList<Singer> tmp_singer = new ArrayList<Singer>();
		for(int i=0; i<10; i++){
			singers.getSingerList().add(new Singer());
			singers.getSingerList().get(i).setName(singerName[i]);
			singers.getSingerList().get(i).setPopularity(i*10);
		}
//		singers.setSingerList(tmp_singer);
//		String str = "hello";
		
		
		Songs songs = new Songs();
		String[] songName= {"Raining", "Homeland", "Falling", "Pure Love","Encounter", "Nothing to lose", "Single", "Dear Sister", "Flower With Fire", "Truly Loving You"};
		String[] albumName= {"F", "nayinian", "dialogsbetweenangelsdemons", "purelove", "Themoment", "Rockinmyway", "listentojanezlive", "Zhangchu", "flowerwithfire", "beyondiv"};
//		ArrayList<Singer> tmp_singer = new ArrayList<Singer>();
		for(int i=0; i<10; i++){
			songs.getSongList().add(new Song());
			songs.getSongList().get(i).setName(songName[i]);
			songs.getSongList().get(i).setAlbumBelong(albumName[i]);
			songs.getSongList().get(i).setViews(i*10);
		}
		
//		JSONObject json_obj = new JSONObject();
//		try {
//			for(int i=0; i<15; i++){
//				json_obj.put("word", "XU Wei");
//				json_obj.put("weight",70);
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		singers.setSingerList(tmp_singer);
//		String str = "hello";
		request.setAttribute("singers", singers);
		request.setAttribute("songs", songs);
//		request.setAttribute("json_singers", json_obj);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
