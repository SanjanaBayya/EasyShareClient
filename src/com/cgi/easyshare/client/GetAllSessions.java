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

import com.cgi.easyshare.response.ParsedResponse;
import com.cgi.easyshare.utilities.ParseResponse;

public class GetAllSessions {
	private ParsedResponse parsedResp;
	
	public ParsedResponse getParsedResp() {
		return parsedResp;
	}

	public void setParsedResp(ParsedResponse parsedResp) {
		this.parsedResp = parsedResp;
	}
	public String execute(){
        StringBuilder completeResponse = new StringBuilder();
        
		try {
				URL url = new URL("http://localhost:8081/EasyShare/getAllSessions");
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
	
}