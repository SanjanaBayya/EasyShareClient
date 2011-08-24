package com.cgi.easyshare.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.cgi.easyshare.response.ParsedResponse;
import com.cgi.easyshare.utilities.ParseResponse;

public class AddMessage {

	private ParsedResponse parsedResp;
	private String sessionId; 
	private String subject; 
	private String text;
	private String date;
	private String postTime;
	private String postedBy;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getPostBy() {
		return postedBy;
	}

	public void setPostBy(String postedBy) {
		this.postedBy = postedBy;
	}
	
	public ParsedResponse getParsedResp() {
		return parsedResp;
	}

	public void setParsedResp(ParsedResponse parsedResp) {
		this.parsedResp = parsedResp;
	}

	public String execute(){
        StringBuilder completeResponse = new StringBuilder();
        
		try {
				populatePostDetails();
				URL url = new URL("http://localhost:8081/EasyShare/addMessage?sessionId=1&subject="+subject+"&text="+text+"&date="+date+"&postTime="+postTime);
				URLConnection conn = url.openConnection();
				ParseResponse pr=new ParseResponse();
				parsedResp=new ParsedResponse();
				conn.setDoOutput(true);
				conn.setRequestProperty("Cookie", "es=surabhi");
				conn.connect();			
				 BufferedReader in = 
		                new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
		            
		            String res;
		            while ( (res = in.readLine()) != null ) {
		                System.out.println( res );
		                completeResponse.append(res);
		            }
		            in.close();	
		            pr.parseXmlStream(completeResponse.toString(),parsedResp);
		            if("FAILURE".equals(parsedResp.getCode()))
		            	return "ERROR";
		            return "ADDED";    
		}catch(MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("MalformedURLException");
			e.printStackTrace();
			parsedResp.setMessage("ERROR");
			return "ERROR";
		}			
		catch(IOException e) {
				// TODO Auto-generated catch block
			System.out.println("IOException");
				e.printStackTrace();
				parsedResp.setMessage("ERROR");
				return "ERROR";
		}
		 
	}
	
	private void populatePostDetails(){
		StringBuffer timeBuff=new StringBuffer();
		Calendar cal=Calendar.getInstance();
		Date today=cal.getTime();
		DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		setDate(df.format(today));
		df=new SimpleDateFormat("HH:mm");	
		System.out.println(df.format(today));
		setPostTime(df.format(today));		
	}
	
}