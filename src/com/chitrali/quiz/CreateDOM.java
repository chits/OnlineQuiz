package com.chitrali.quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
/**
 * 
 * @author Chitrali Rai
 * This class is to get the DOM elements from the input xml
 * 
 */

public class CreateDOM {
	
	/**
	 * Parses the input xml quiz file to get all DOM elements
	 * to be displayed for individual subjects.
	 * @param test subject
	 * @return parsed Document 
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	public static Document getDOM(String subject) throws SAXException,ParserConfigurationException,IOException, URISyntaxException
	{
		Document dom=null;
		File quizFile=null;
		    
		quizFile=new File("/Users/cr252/desktop/Quizzes/"+subject+"-quiz-1.xml");
	         
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		try{
			dom=db.parse(quizFile);
		}catch(FileNotFoundException fileNotFound){
			System.out.println("Error : Quiz File Not Found "+fileNotFound);
		}
		dom.getDocumentElement().normalize();
		return dom;
	}

}
