package org.music.analysis;

import java.util.ArrayList;

public class Singers {
	private ArrayList<Singer> singers = new ArrayList<Singer>();
	
	public void setSingerList(ArrayList<Singer> sList){
		this.singers = sList;
	}
	public ArrayList<Singer> getSingerList(){
		return this.singers;
	}
}
