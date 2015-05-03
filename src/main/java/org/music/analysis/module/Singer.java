package org.music.analysis.module;

public class Singer {
	private String sName;
	private String[] albums;
	private int popularity;
	private String country;
	
	public Singer(){
		this.sName = "";
		this.albums = null;
		this.popularity = 0;
		this.country = "";
	}
	
	public void setName(String sName){
		this.sName = sName;
	}
	public void setAlbums(String[] albums){
		this.albums = albums;
	}
	public void setCountry(String country){
		this.country = country;
	}
	public void setPopularity(int popularity){
		this.popularity = popularity;
	}
	
	public String getName(){
		return this.sName;
	}
	public String[] getAlbums(){
		return this.albums;
	}
	public String getCountry(){
		return this.country;
	}
	public int getPopularity(){
		return this.popularity;
	}
}
