package com.chitrali.quiz;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.chitrali.quiz.CreateDOM;


/**
 * This class acts as a model Exam list for all subjects
 * @author Chitrali Rai
 *
 */
public class Exam {
	/**
	 * Document object 
	 */
	Document dom;
	public int currentQuestion=0;
	public int totalNumberOfQuestions=0;
	public int quizDuration=0;
	public List<Integer> quizSelectionsList=new ArrayList<Integer>();
	public Map<Integer,Integer> selections=new LinkedHashMap<Integer,Integer>();
	public ArrayList<QuizQuestion> questionList;
	/*
	 * get and set for all variables
	 */
	public List<Integer> getQuizSelectionsList() {
		return quizSelectionsList;
	}
	
	public ArrayList<QuizQuestion> getQuestionList(){
		return this.questionList;
	}
	
	public int getCurrentQuestion(){
		return currentQuestion;
	}
	
	public Map<Integer,Integer> getSelections(){
		return this.selections;
	}
	
	public Document getDom(){
		return dom;
	}
	
	public int getTotalNumberOfQuestions(){
		return totalNumberOfQuestions;
	}
   
	public void setTotalNumberOfQuestions(int i){
		this.totalNumberOfQuestions=i;
	}
	
	// Constructor
	public Exam(String test,int totalNumberOfQuestions) throws SAXException,ParserConfigurationException,IOException, URISyntaxException{
		dom=CreateDOM.getDOM(test);
		for(int i=0;i<totalNumberOfQuestions;i++){
			selections.put(i,-1);
		}
		questionList=new ArrayList<QuizQuestion>(totalNumberOfQuestions);
	}
	
	/*
	 * Setting question based on the DOM read from the
	 * input XML's
	 */
	public void setQuestion(int i)
	{ 
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
		
		QuizQuestion q=new QuizQuestion();
		q.setQuestionNumber(number);
		q.setQuestion(question);
		q.setCorrectOptionIndex(correct);
		q.setQuestionOptions(options);	
		
		questionList.add(number,q);
	}
	
	/*
	 * Calculating result based on user input
	 * against the correct answers in the xml
	 */
	public int calculateResult(Exam exam,int taken){
		int totalCorrect=0;
		Map<Integer,Integer> userSelectionsMap=exam.selections;		
		List<Integer> userSelectionsList=new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry :userSelectionsMap.entrySet()) {
			userSelectionsList.add(entry.getValue());
		}
		quizSelectionsList=userSelectionsList;
		
		List<QuizQuestion> questionList=exam.questionList;
		
		List<Integer> correctAnswersList=new ArrayList<Integer>();
		for(QuizQuestion question: questionList){
			correctAnswersList.add(question.getCorrectOptionIndex());
		}
		
		for(int i=0;i<taken-1;i++){
			
			if((userSelectionsList.get(i)-1)==correctAnswersList.get(i)){
				totalCorrect++;
			}
		}
		
		
		return totalCorrect;
	}
	
	//Get user selection
	public int getUserSelectionForQuestion(int i){
		Map<Integer,Integer> map=getSelections();
		return (Integer) map.get(i);
	}		
	
}
