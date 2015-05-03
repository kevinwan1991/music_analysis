package org.music.analysis.module;

public class Song {
	private String songName = "";
	private String albumBelong = "";
	private int views = 0;
	private String language = "";
	private String url = "";
	private String genre = "";
	
//	Song(){
//		this.songName = "";
//		this.albumBelong = null;
//		this.views = 0;
//		this.language = "";
//		this.url = "";
//	}
	
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
	public void setUrl(String url){
		this.url = url;
	}
	public void setGenre(String genre){
		this.genre = genre;
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
	public String getUrl(){
		return this.url;
	}
	public String getGenre(){
		return this.genre;
	}
}
