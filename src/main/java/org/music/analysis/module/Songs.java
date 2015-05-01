package org.music.analysis.module;

import java.util.ArrayList;

public class Songs {
	private ArrayList<Song> songs = new ArrayList<Song>();
	
	public void setSongList(ArrayList<Song> sList){
		this.songs = sList;
	}
	public ArrayList<Song> getSongList(){
		return this.songs;
	}
}
