package com.cgi.easyshare.utilities;

import java.io.IOException;
import java.io.StringReader;
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

public class ParseResponse {
	
	public String parseXmlStream(String response,String service,String message){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<String>responseList=null;
		String data=null;
		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			InputSource is = new InputSource(new StringReader(response));
			 Document dom= db.parse(is);
			 data=parseDocument(dom,service,message);
			 

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return data;
	}
	
	private String parseDocument(Document dom,String service,String message){
		//get the root element
		Element docEle = dom.getDocumentElement();
		String data=null;
		//get a nodelist of elements
		NodeList serviceNode = docEle.getElementsByTagName("service");
		Element serviceElement=null;
		if(serviceNode!=null){
			serviceElement=(Element)serviceNode.item(0);
		}
		service=getSubElementValue(serviceElement,"service");
		
		NodeList messageNode = docEle.getElementsByTagName("message");
		Element messageElement=null;
		if(messageNode!=null){
			messageElement=(Element)messageNode.item(0);
		}
		message=messageElement.getFirstChild().getNodeValue();
		System.out.println(service+"   "+message);
		NodeList dataNode=docEle.getElementsByTagName("data");
		if("SUCCESS".equals(message)){
			Element dataElement=null;
			if(dataNode!=null){
				dataElement=(Element)dataNode.item(0);
			}
			if("GetAllSessions".equals(service) || "GetMySessions".equals(service))
				data=loadSessions(dataElement);
			else if("GetUsers".equals(service) || "GetUser".equals(service))
				data=loadUsers(dataElement);
			else 
				data=loadResult(dataElement);
			
		}
		
		return data;
	}
	public String loadSessions(Element dataElement){
		List<String>responseList=new ArrayList<String>();
		NodeList sessionList=dataElement.getElementsByTagName("com.cgi.open.easyshare.model.Session");
		if(sessionList != null && sessionList.getLength() > 0) {
			for(int i = 0 ; i < sessionList.getLength();i++) {

				Element el = (Element)sessionList.item(i);

				//get the Employee object
				responseList.add(getSubElementValue(el,"sessionName"));			
			}
		}
		return responseList.toString();
		
	}
	
	public String loadUsers(Element dataElement){
		List<String>responseList=new ArrayList<String>();
		NodeList usersList=dataElement.getElementsByTagName("com.cgi.open.easyshare.model.User");
		if(usersList != null && usersList.getLength() > 0) {
			for(int i = 0 ; i < usersList.getLength();i++) {
				Element el = (Element)usersList.item(i);
				responseList.add(getSubElementValue(el,"email"));			
			}
		}
		return responseList.toString();
		
	}
	
	public String loadResult(Element dataElement){
		String data=null;
		data=dataElement.getFirstChild().getNodeValue();
		return data;
	}
	
	private String getSubElementValue(Element el,String subElement){
		String subEleValue = getTextValue(el,subElement);
		return subEleValue;
		
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



