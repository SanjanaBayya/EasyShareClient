package com.cgi.easyshare.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
public class ParseMain {
	private static String service;
	private static String message;
	private static String data;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	/*public static void main(String[] args) {
		ParseResponse pr=new ParseResponse();
		ParsedResponse parsedResp=new ParsedResponse();
		 StringBuilder completeResponse = new StringBuilder();
	        
			try {
					//URL url = new URL("http://localhost:8081/EasyShare/getAllSessions");
					//URL url = new URL("http://localhost:8081/EasyShare/getMySessions?email=sanjana@cgi.com");
					//URL url = new URL("http://localhost:8081/EasyShare/getUsers?sessionId=1&userType=FACILITATOR");
					//URL url = new URL("http://localhost:8081/EasyShare/createSession?sessionName=DBMS&description=basics");
					//URL url = new URL("http://localhost:8081/EasyShare/addAppointment?sessionId=1&date=07/28/2011&fromTime=1500&toTime=1700&location=ecity");
					//URL url = new URL("http://localhost:8081/EasyShare/addMessage?sessionId=1&subject=hii&text=epadi&date=07/28/2011&postedBy=sanjana");
					//URL url = new URL("http://localhost:8081/EasyShare/addFacilitator?sessionId=1&email=shamim.aziz");
					//URL url = new URL("http://localhost:8081/EasyShare/addAttendee?sessionId=1&email=sanjana.bayya");
					//URL url = new URL("http://localhost:8081/EasyShare/assignAdmin?sessionId=1&email=surabhi@cgi.com");
				//---*hold on for now*
				//URL url = new URL("http://localhost:8081/EasyShare/addResource?sessionId=1&resourceName=installables&url=/install/exe");
				
				//URL url = new URL("http://localhost:8081/EasyShare/removeResource?sessionId=1&resourceId=2");
				//URL url = new URL("http://localhost:8081/EasyShare/removeAttendee?sessionId=1&email=safiya.fathima");
				//URL url = new URL("http://localhost:8081/EasyShare/removeMessage?sessionId=1&messageId=2");
				//URL url = new URL("http://localhost:8081/EasyShare/removeFacilitator?sessionId=1&email=jaffer@cgi.com");
				URL url = new URL("http://localhost:8081/EasyShare/removeAppointment?sessionId=1&appointmentId=2");
					URLConnection conn = url.openConnection();
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
			            System.out.println(parsedResp.getData());
			                
			}catch(MalformedURLException e) {
				// TODO Auto-generated catch block
				System.out.println("MalformedURLException");
				e.printStackTrace();
				
			}			
			catch(IOException e) {
					// TODO Auto-generated catch block
				System.out.println("IOException");
					e.printStackTrace();
					
			}

	}*/
	
	public static void main(String[] args) {
		
			StringBuffer timeBuff=new StringBuffer();
			Calendar cal=Calendar.getInstance();
			Date today=cal.getTime();
			DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
			System.out.println(df.format(today));
			df=new SimpleDateFormat("HH:mm a");			
			System.out.println(df.format(today));
			
		
	}
	
}
