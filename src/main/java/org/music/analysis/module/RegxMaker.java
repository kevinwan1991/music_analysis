package org.music.analysis.module;

public class RegxMaker {
	public static String make(String vagueName){
		String[] parts = vagueName.split(" ");
		StringBuilder sb = new StringBuilder();
		
		sb.append("(.)*");
		for(int i=0; i<parts.length; i++){
			sb.append("("+parts[i]+")+(.)*");
			//System.out.println(sb.toString());
		}
		
		return sb.toString();
	}
	
	public static void main(String args[]){
		String rst = RegxMaker.make("rolling stone");
		System.out.println(rst);
	}
}
