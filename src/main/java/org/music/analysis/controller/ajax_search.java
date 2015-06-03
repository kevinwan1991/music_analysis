package org.music.analysis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.music.analysis.module.*;

public class ajax_search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<String> strList = new ArrayList<String>();
		// StringBuffer sb = new StringBuffer();
		String content = req.getParameter("content").trim().toLowerCase();
		
		strList = new SongHandler().ajaxSearch(content);
		//strList.add("wang");
		//strList.add("wan");
		//strList.add("wa");
//		if (content != null && !"".equals(content)) {
//			for (String strTemp : str) {
//				if (strTemp.toLowerCase().startsWith(content)) {
//					strList.add(strTemp);
//					// sb.append(strTemp);
//					// sb.append("<br/>");
//				}
//			}
//		}
		// if (strList.size() > 10) {
		// strList = strList.subList(0, 10);
		// }
		PrintWriter out = resp.getWriter();
	        out.println(strList.get(0));	
		out.println(strList.get(1));
		//out.println(strList.get(1));
		
		//req.setAttribute("strList", strList);
		//req.getRequestDispatcher("ajax_search.jsp").forward(req, resp);
		// PrintWriter print = resp.getWriter();
		// print.write(sb.toString());
	}
}
