package com.chitrali.quiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chitrali.quiz.DatabaseConnection;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/checkRegister")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if(username == "" || password == "" || email == "")
		{
			PrintWriter out = response.getWriter();  
	        response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
	        out.println("alert('Fields cannot be empty');");
	        out.println("location='register';");
	        out.println("</script>");		
			out.close();
		}
		
		Connection con=DatabaseConnection.createConnection();
		
		try
		{
			Statement st=con.createStatement();
			String sql = "INSERT INTO users values ('"+username+"','"+email+"','"+password+"')";
		 	st.executeUpdate(sql);
		 	request.setAttribute("newUser",username);
	        PrintWriter out = response.getWriter();  
	        response.setContentType("text/html");  
	        out.println("<script type=\"text/javascript\">");  
	        out.println("alert('Congratulations! Account created.');");  
	        out.println("location='login';");
	        out.println("</script>");		
			out.close();
			con.close();
		}catch(SQLException sqe){
			PrintWriter out = response.getWriter();  
	        response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
	        out.println("alert('User name already exists');");
	        out.println("location='register';");
	        out.println("</script>");		
			out.close();
		}
	}
	
	
	

}
