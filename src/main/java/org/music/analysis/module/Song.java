package org.music.analysis.module;

public class Song {
	private String songName;
	private String albumBelong;
	private int views;
	private String language;
	
	Song(){
		this.songName = "";
		this.albumBelong = null;
		this.views = 0;
		this.language = "";
	}
	
	public void setName(String sName){
		this.songName = sName;
	}
	public void setAlbumBelong(String album){
		this.albumBelong = album;
	}
	public void setLanguage(String language){
		this.language = language;
	}
	public void setViews(int views){
		this.views = views;
	}
	
	public String getName(){
		return this.songName;
	}
	public String getAlbumBelong(){
		return this.albumBelong;
	}
	public String getLanguage(){
		return this.language;
	}
	public int getViews(){
		return this.views;
	}
}
