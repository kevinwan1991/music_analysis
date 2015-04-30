package org.music.analysis.module;

import java.sql.*;

public class ConnDB {
	public Connection ct = null;
	
	public Connection getConnection(){
		String url="jdbc:mysql://musicanalysisdata.cuxecej3mq5k.us-west-2.rds.amazonaws.com/Database_Project_DB";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ct = DriverManager.getConnection(url,"KevinWill","password");	
		}catch( Exception ex){
			ex.printStackTrace();
		}
		return ct;
	}
}

