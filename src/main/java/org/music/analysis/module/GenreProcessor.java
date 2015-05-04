package org.music.analysis.module;

public class GenreProcessor {
	public static String act(String genre){
		if(genre.equals("Unknown genre"))
			genre = "";
		
		if(genre.indexOf("music")>=0)
			genre=genre.substring(0,genre.indexOf("music")-1);
		if(genre.indexOf("genre")>=0)
			genre=genre.substring(0,genre.indexOf("genre")-1);
		if(genre.indexOf("-")>=0)
			genre=genre.substring(0,genre.indexOf("-"))+genre.substring(genre.indexOf("-")+1, genre.length()-1);
		
		return genre.toLowerCase();
	}
}
