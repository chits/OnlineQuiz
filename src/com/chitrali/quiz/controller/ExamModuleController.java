package com.chitrali.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chitrali.quiz.DBHandler;
import com.chitrali.quiz.ExamModule;
import com.chitrali.quiz.Profile;
import com.chitrali.quiz.QuizModule;

/**
 * 
 * @author Chitrali Rai
 * Servlet implementation for exam flow
 *
 */
@WebServlet("/exam")
public class ExamModuleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean finish=false;
		
		HttpSession session=request.getSession();		
		try
		{
			if(session.getAttribute("currentExam")==null)
		  {  session=request.getSession(); 	
		     String selectedExam=(String)request.getSession().getAttribute("exam"); 
		   
		     Object ob= session.getAttribute("totalNumberOfQuizQuestions");
		     
		     String sob=(String)ob;
			 ExamModule newExam=new ExamModule(selectedExam,Integer.parseInt(sob));
			 session.setAttribute("currentExam",newExam);
			 String sq=(String)request.getSession().getAttribute("totalNumberOfQuizQuestions");
			
			 newExam.setTotalNumberOfQuestions(Integer.parseInt(sq));
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
				Date date = new Date();
				String started=dateFormat.format(date);
			  session.setAttribute("started",started);
		  }
		
		}catch(Exception e){e.printStackTrace();}
		
        ExamModule exam=(ExamModule)request.getSession().getAttribute("currentExam");		
        
        if(exam.currentQuestion==0){	
			exam.setQuestion(exam.currentQuestion);
		    QuizModule q=exam.questionList.get(exam.currentQuestion);	
			session.setAttribute("quest",q);
        }
			
			String action=request.getParameter("action");			
			
			int minute=-1;
			int second=-1;
			if(request.getParameter("minute")!=null)
			{			
			minute=Integer.parseInt(request.getParameter("minute"));
			second=Integer.parseInt(request.getParameter("second"));
			request.getSession().setAttribute("min",request.getParameter("minute") );
			request.getSession().setAttribute("sec",request.getParameter("second") );
			}
			
			String radio=request.getParameter("answer");
			int selectedRadio=-1;
			exam.selections.put(exam.currentQuestion, selectedRadio);
			if("1".equals(radio))
			{
				selectedRadio=1;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);				
			}
			else if("2".equals(radio))
			{
				selectedRadio=2;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);				
			}
			else if("3".equals(radio))
			{
				selectedRadio=3;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);				
			}
			else if("4".equals(radio))
			{
				selectedRadio=4;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(selectedRadio);				
			}
			
						
			if("Next".equals(action)){
				exam.currentQuestion++;
				exam.setQuestion(exam.currentQuestion);
			    QuizModule q=exam.questionList.get(exam.currentQuestion);	
			  	session.setAttribute("quest",q);
			}
			else if("Finish Exam".equals(action)||( minute==0 && second==0))
			{   finish=true;			    
				int result=exam.calculateResult(exam,exam.questionList.size());
				Connection con=DBHandler.createConnection();
				try
				{
					 Statement st=con.createStatement();
					 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
					 Date date = new Date();
					 String endTime=dateFormat.format(date);
					 String sql = "INSERT INTO profile values ('"+session.getAttribute("user")+"','"+session.getAttribute("exam")+"','"+endTime+"','"+result+"')";
					 st.executeUpdate(sql);
				}
				catch(SQLException sqe)
				{
					System.out.println("Error : While Inserting record in database");
				}
				try
				{
				 con.close();	
				}
				catch(SQLException se)
				{
					System.out.println("Error : While Closing Connection");
				}
				request.setAttribute("result",result);		
				Profile profile = new Profile(session.getAttribute("user").toString());
				session.setAttribute("profileList",profile.getProfileInfo());
				request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request,response);
				
			}
						
		if(finish!=true){
		request.getRequestDispatcher("/WEB-INF/jsps/exam.jsp").forward(request,response);
		}
		
	}

}
