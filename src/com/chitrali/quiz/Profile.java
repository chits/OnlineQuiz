package com.chitrali.quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is the model for Profile page for all users.
 * This is used to model the table shown in profile.jsp
 * @author Chitrali Rai
 *
 */
public class Profile {
	
	/**
	 * Username
	 */
	String username;
	/**
	 * Subject of quiz
	 */
	String subject;
	/**
	 * Date when quiz is taken
	 */
	String date;
	/**
	 * Score
	 */
	int score;
	
	/**
	 * Constructor
	 * @param _username
	 */
	public Profile(String _username){
		username = _username;
	}
	/**
	 * Getter
	 * @return username
	 */
	public String getUsername(){
		return username;
	}
	/**
	 * Getter
	 * @return subject
	 */
	public String getSubject(){
		return subject;
	}
	/**
	 * @return date
	 */
	public String getDate(){
		return date;
	}
	/**
	 * @return Score
	 */
	public int getScore(){
		return score;
	}
	public void setSubject(String _subject){
		subject = _subject;
	}
	public void setDate(String _date){
		date = _date;
	}
	public void setScore(int _score){
		score = _score;
	}
	
	/*
	 * Get details for all quizzes taken by the user
	 * from the Profile table. This will help to populate
	 * profile.jsp page
	 */
	/**
	 * Get details for all quizzes taken by the user
	 * from the Profile table. This helps to populate
	 * profile.jsp page
	 * @return List of all quizzes taken by user
	 */
	public List<Profile> getProfileInfo()
	{
		Connection con=DBHandler.createConnection();		
		ResultSet set=null;
		List<Profile> profileList = new ArrayList<Profile>();
		try
		{
			 Statement st=con.createStatement();
			 String sql = "Select * from  profile where username='"+username+"' ";
			 System.out.println(sql);
			 set=st.executeQuery(sql);
			 while(set.next())
			 {
				 Profile profile = new Profile(set.getString(1));
				 profile.setSubject(set.getString(2));
				 profile.setDate(set.getString(3));
				 profile.setScore(set.getInt(4));
				 profileList.add(profile);
			 }
		}catch(SQLException sqe)
		{
			System.out.println("Error : While Fetching records from database");
		}
		try
		{
		 con.close();	
		}catch(SQLException se)
		{
			System.out.println("Error : While Closing Connection");
		}
		return profileList;
	}

}
