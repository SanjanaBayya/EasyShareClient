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
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class AssignAdmin {
	private String service;
	private String message;
	private String code;
	private String data;
	
	private String sessionId;
	private String email;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String execute(){
        StringBuilder completeResponse = new StringBuilder();
        
		try {
				URL url = new URL("http://localhost:8080/EasyShare/assignAdmin?sessionId="+sessionId+"&email="+email);
			
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);
				 BufferedWriter out = 
				        new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );			
				 out.flush();
				 out.close();			
				 BufferedReader in = 
		                new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
		            
		            String res;
		            while ( (res = in.readLine()) != null ) {
		                System.out.println( res );
		                completeResponse.append(res);
		            }
		            in.close();	
		            parseXmlStream(completeResponse.toString());
		            return "ADDED";    
		}catch(MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("MalformedURLException");
			e.printStackTrace();
			message="ERROR";
			return "ERROR";
		}			
		catch(IOException e) {
				// TODO Auto-generated catch block
			System.out.println("IOException");
				e.printStackTrace();
				message="ERROR";
				return "ERROR";
		}
		 
	}
	
	

	private void parseXmlStream(String response){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<String>responseList=null;
		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			InputSource is = new InputSource(new StringReader(response));
			 Document dom= db.parse(is);
			 parseDocument(dom);
			

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}


private void parseDocument(Document dom){
	//get the root element
	Element docEle = dom.getDocumentElement();
	List<String>responseList=new ArrayList<String>();
	//get a nodelist of elements
	NodeList serviceNode = docEle.getElementsByTagName("service");
	Element serviceElement=null;
	if(serviceNode!=null){
		serviceElement=(Element)serviceNode.item(0);
	}
	service=getElementAttribute(serviceElement,"service");
	
	NodeList messageNode = docEle.getElementsByTagName("message");
	Element messageElement=null;
	if(messageNode!=null){
		messageElement=(Element)messageNode.item(0);
	}
	message=messageElement.getNodeValue();
	System.out.println(service+"   "+message);
	
	NodeList codeNode = docEle.getElementsByTagName("code");
	Element codeElement=null;
	if(codeNode!=null){
		codeElement=(Element)codeNode.item(0);
	}
	code=codeElement.getNodeValue();
	/*
	NodeList dataNode=docEle.getElementsByTagName("data");
	Element dataElement=null;
	if(dataNode!=null){
		dataElement=(Element)dataNode.item(0);
	}
	NodeList sessionList=dataElement.getElementsByTagName("com.cgi.open.easyshare.model.Session");
	if(sessionList != null && sessionList.getLength() > 0) {
		for(int i = 0 ; i < sessionList.getLength();i++) {

			//get the employee element
			Element el = (Element)sessionList.item(i);

			//get the Employee object
			responseList.add(getElementAttribute(el,"sessionName"));			
		}
	}
	*/
	
}

private String getElementAttribute(Element el,String attribute){
	String bookName = getTextValue(el,attribute);
	return bookName;
	
}

private String getTextValue(Element ele, String tagName) {
	String textVal = null;
	NodeList nl = ele.getElementsByTagName(tagName);
	if(nl != null && nl.getLength() > 0) {
		Element el = (Element)nl.item(0);
		textVal = el.getFirstChild().getNodeValue();
	}

	return textVal;
}

}
