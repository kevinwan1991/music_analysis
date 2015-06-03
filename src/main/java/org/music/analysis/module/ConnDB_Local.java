package org.music.analysis.module;

import java.sql.*;

public class ConnDB_Local {
	public Connection ct = null;
	
	public Connection getConnection(){
		String url="jdbc:mysql://musica.cuxecej3mq5k.us-west-2.rds.amazonaws.com:3306/database_project_db2"+"?useUnicode=true&characterEncoding=UTF-8";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ct = DriverManager.getConnection(url,"KevinWill","password");	
		}catch( Exception ex){
			ex.printStackTrace();
		}
		return ct;
	}
}

