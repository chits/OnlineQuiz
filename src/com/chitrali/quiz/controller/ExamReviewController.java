package com.chitrali.quiz.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.chitrali.quiz.ExamModule;
import com.chitrali.quiz.QuizModule;

/**
 * 
 * @author Chitrali Rai
 * Servlet implementation of showing the review answers page for a quiz
 *
 */
@WebServlet("/exam/review")
public class ExamReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ExamModule exam=(ExamModule)request.getSession().getAttribute("currentExam");
		
		request.setAttribute("totalQuestion",exam.getTotalNumberOfQuestions());
		
		ArrayList<QuizModule> reviewQuestionList=new ArrayList<QuizModule>();
		
		Document dom=exam.getDom();
		
		for(int i=0;i<exam.getTotalNumberOfQuestions();i++){
			int number=i;
			String options[]=new String[4];
		    String question=null;
		    int correct=0;
		   
			NodeList qList=dom.getElementsByTagName("question");
		    NodeList childList=qList.item(i).getChildNodes();
		    
		    int counter=0;
		    
		    for (int j = 0; j < childList.getLength(); j++) {
	            Node childNode = childList.item(j);
	            if ("answer".equals(childNode.getNodeName()))
	            {
	                options[counter]=childList.item(j).getTextContent();
	                counter++;
	            }
	            else if("quizquestion".equals(childNode.getNodeName()))
	            {
	            	question=childList.item(j).getTextContent();
	            }
	            else if("correct".equals(childNode.getNodeName()))
	            {
	            	correct=Integer.parseInt(childList.item(j).getTextContent());
	            }
	            
	        }
		  
			QuizModule q=new QuizModule();
			q.setQuestionNumber(number);
			q.setQuestion(question);
			q.setCorrectOptionIndex(correct);
			q.setQuestionOptions(options);
		    q.setUserSelected(exam.getUserSelectionForQuestion(i));
			reviewQuestionList.add(number,q);
		}
		request.setAttribute("reviewQuestions",reviewQuestionList);		
		request.getRequestDispatcher("/WEB-INF/jsps/examReview.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
