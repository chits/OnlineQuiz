package com.chitrali.quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	String username;
	String subject;
	String date;
	int score;
	
	public Profile(String _username){
		username = _username;
	}
	
	public String getUsername(){
		return username;
	}
	public String getSubject(){
		return subject;
	}
	public String getDate(){
		return date;
	}
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
	public List<Profile> getProfileInfo()
	{
		Connection con=DatabaseConnection.createConnection();		
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
