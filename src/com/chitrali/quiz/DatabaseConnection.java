package com.chitrali.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is a class for making the connection with the database
 * @author Chitrali Rai
 *
 */
public class DatabaseConnection {
	
	/**
	 * URL of the database host and database on it
	 */
	static String dbURL="jdbc:mysql://localhost/quiz";
	/**
	 * User name of database connection
	 */
	static String dbUser="root";
	/**
	 * Database password
	 */
	static String dbPassword="chits25";
	
	/**
	 * Loads Mysql driver and creates connection with database
	 * for read write operations.
	 * @return the connection object
	 */
	public static Connection createConnection()
	{
		Connection con=null;
		try{
			try {
				   Class.forName("com.mysql.jdbc.Driver");
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


