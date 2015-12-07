package com.chitrali.quiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chitrali.quiz.DatabaseConnection;
import com.chitrali.quiz.Profile;

/**
 * 
 * @author Chitrali Rai
 * Servlet implementation of login authentication flow functionality
 *
 */
@WebServlet("/checkLogin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");				
		Connection con=DatabaseConnection.createConnection();		
		ResultSet set=null;
		int i=0;
		try
		{
		 Statement st=con.createStatement();
		 String sql = "Select * from  users where username='"+username+"' and password='"+password+"' ";
		 		System.out.println(sql);
		 set=st.executeQuery(sql);
		 while(set.next())
		 {
			 i=1;
		 }
		
		 if(i!=0)
		 {   HttpSession session=request.getSession();
		     session.setAttribute("user",username);
		     Profile profile = new Profile(request.getSession().getAttribute("user").toString());
		     request.getSession().setAttribute("profileList", profile.getProfileInfo());
			 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsps/profile.jsp");
			 rd.forward(request, response);
		 }
		 else
		 {  
			 PrintWriter out = response.getWriter();  
			 response.setContentType("text/html");  
			 out.println("<script type=\"text/javascript\">");  
			 out.println("alert('Invalid user credentials');");
			 out.println("location='login';");
			 out.println("</script>");		
			 out.close();
		 }
		}catch(SQLException sqe){System.out.println("Error : While Fetching records from database");}
		try
		{
		 con.close();	
		}catch(SQLException se){System.out.println("Error : While Closing Connection");}
	}
}
