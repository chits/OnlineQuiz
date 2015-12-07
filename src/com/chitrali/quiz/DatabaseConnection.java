package com.chitrali.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Chitrali Rai
 * This is the database connection class
 */
public class DatabaseConnection {
	
	static String dbURL="jdbc:oracle:thin:@prophet.njit.edu:1521:course";
	static String dbUser="cr252";
	static String dbPassword="CKHMs7icB";
	/*
	 * Loads Oracle driver and creates connection with database
	 * for read write operations.
	 */
	public static Connection createConnection()
	{
		Connection con=null;
		try{
			try {
				   Class.forName("oracle.jdbc.driver.OracleDriver");
				}
				catch(ClassNotFoundException ex) {
				   System.out.println("Error: unable to load driver class!");
				   System.exit(1);
				}			
		     con = DriverManager.getConnection(dbURL,dbUser,dbPassword);
		   }
		  catch(SQLException sqe){ 
			  System.out.println("Error: While Creating connection to database");sqe.printStackTrace();
		  }
		return con;
	}

}


