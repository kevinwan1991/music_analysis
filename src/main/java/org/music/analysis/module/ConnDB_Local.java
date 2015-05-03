package org.music.analysis.module;

import java.sql.*;

public class ConnDB_Local {
	public Connection ct = null;
	
	public Connection getConnection(){
		String url="jdbc:mysql://localhost:3306/database_project_db2";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ct = DriverManager.getConnection(url,"root","qazwsx");	
		}catch( Exception ex){
			ex.printStackTrace();
		}
		return ct;
	}
}

