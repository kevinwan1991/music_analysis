package org.music.analysis.module;

public class Song {

	private String id = "";
	private String genre = "";
	private String songName = "";
	private String albumBelong = "";
	private int views = 0;
	private String language = "";
	private String url = "";
//	
//	public Song(){
//		this.id = "";
//		this.genre = "";
//		this.songName = "";
//		this.albumBelong = "";
//		this.views = 0;
//		this.language = "";
//		this.url = "";
//	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenres() {
		return genre;
	}

	public void setGenres(String genre) {
		this.genre = genre;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
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
