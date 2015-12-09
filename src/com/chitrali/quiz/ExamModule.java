package com.chitrali.quiz;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.chitrali.quiz.ParseXmlToDom;


/**
 * This class acts as a model ExamModule list for all subjects
 * @author Chitrali Rai
 *
 */
public class ExamModule {
	/**
	 * Document object 
	 */
	Document dom;
	/**
	 * Current question counter
	 */
	public int currentQuestion=0;
	/**
	 * Total no of questions
	 */
	public int totalNumberOfQuestions=0;
	/**
	 * Duration of quiz for the timer
	 */
	public int quizDuration=0;
	/**
	 * List of Integers to store question selection
	 */
	public List<Integer> quizSelectionsList=new ArrayList<Integer>();
	/**
	 * Map for tracking the user selection about choices in a question
	 */
	public Map<Integer,Integer> selections=new LinkedHashMap<Integer,Integer>();
	/**
	 * Array of questions
	 */
	public ArrayList<QuizModule> questionList;
	
	/**
	 * Getter
	 * @return question selection list
	 */
	public List<Integer> getQuizSelectionsList() {
		return quizSelectionsList;
	}
	
	/**
	 * Getter
	 * @return question list
	 */
	public ArrayList<QuizModule> getQuestionList(){
		return this.questionList;
	}
	
	/**
	 * Getter
	 * @return current question
	 */
	public int getCurrentQuestion(){
		return currentQuestion;
	}
	
	/**
	 * @return Map of selections
	 */
	public Map<Integer,Integer> getSelections(){
		return this.selections;
	}
	
	/**
	 * @return Parsed document
	 */
	public Document getDom(){
		return dom;
	}
	
	/**
	 * @return total no of questions
	 */
	public int getTotalNumberOfQuestions(){
		return totalNumberOfQuestions;
	}
   
	public void setTotalNumberOfQuestions(int i){
		this.totalNumberOfQuestions=i;
	}
	
	
	/**
	 * Constructor
	 * @param test
	 * @param totalNumberOfQuestions
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public ExamModule(String test,int totalNumberOfQuestions) throws SAXException,ParserConfigurationException,IOException, URISyntaxException{
		dom=ParseXmlToDom.parseXmlStructure(test);
		for(int i=0;i<totalNumberOfQuestions;i++){
			selections.put(i,-1);
		}
		questionList=new ArrayList<QuizModule>(totalNumberOfQuestions);
	}
	
	
	/**
	 * Setting question based on the DOM read from the
	 * input XML's
	 * @param i
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
		
		QuizModule q=new QuizModule();
		q.setQuestionNumber(number);
		q.setQuestion(question);
		q.setCorrectOptionIndex(correct);
		q.setQuestionOptions(options);	
		
		questionList.add(number,q);
	}
	
	
	/**
	 * 
	 * Calculating result based on user input
	 * against the correct answers in the xml
	 * @param exam
	 * @param taken
	 * @return result score
	 */
	public int calculateResult(ExamModule exam,int taken){
		int totalCorrect=0;
		Map<Integer,Integer> userSelectionsMap=exam.selections;		
		List<Integer> userSelectionsList=new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry :userSelectionsMap.entrySet()) {
			userSelectionsList.add(entry.getValue());
		}
		quizSelectionsList=userSelectionsList;
		
		List<QuizModule> questionList=exam.questionList;
		
		List<Integer> correctAnswersList=new ArrayList<Integer>();
		for(QuizModule question: questionList){
			correctAnswersList.add(question.getCorrectOptionIndex());
		}
		
		for(int i=0;i<taken-1;i++){
			
			if((userSelectionsList.get(i)-1)==correctAnswersList.get(i)){
				totalCorrect++;
			}
		}
		
		
		return totalCorrect;
	}
	/**
	 * @param i
	 * @return user selection
	 */
	public int getUserSelectionForQuestion(int i){
		Map<Integer,Integer> map=getSelections();
		return (Integer) map.get(i);
	}		
	
}
