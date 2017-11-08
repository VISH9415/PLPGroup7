package com.cg.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

  private static Connection conn;
  public static Connection createConnection() {
	  ResourceBundle resOracle = ResourceBundle.getBundle("oracle");
	  String url = resOracle.getString("url");
	  String username = resOracle.getString("username");
	  String password = resOracle.getString("password");
	  try {
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		//connStudent = DriverManager.getConnection(url,username,password);
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "Capgemini123");
		
		  //System.out.println(connStudent);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  //connStudent = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Capgemini123");
	 //
	   return conn;
  }
  
  public static void closeConnection() throws SQLException{
	  if(conn!= null){
		  conn.close();
	  }
  }
  
}
