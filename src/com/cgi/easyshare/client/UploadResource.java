package com.cgi.easyshare.client;

import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;

import com.cgi.easyshare.response.ParsedResponse;
import com.cgi.easyshare.utilities.ParseResponse;
import com.opensymphony.xwork2.ActionSupport;

public class UploadResource extends ActionSupport {

	private File upload;// The actual file
	private String uploadContentType; // The content type of the file
	private String uploadFileName; // The uploaded file name
	
	private String service;
	private String message;
	private String code;
	private String data;
	private ParsedResponse parsedResp;
	

	public String execute() throws Exception {
		StringBuilder completeResponse = new StringBuilder();
		try {

			String destination = "c:/upload/"+uploadFileName;

			File resourcePool = new File(destination);

			FileUtils.copyFile(upload, resourcePool);
			URL url = new URL("http://localhost:8081/EasyShare/addResource?sessionId=1&resourceName="+uploadFileName+"&resourceUrl="+destination);
			URLConnection conn = url.openConnection();
			ParseResponse pr=new ParseResponse();
			parsedResp=new ParsedResponse();
			conn.setDoOutput(true);
			conn.setRequestProperty("Cookie", "es=jaffer");
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
			} catch (Exception e) {
				addActionError(e.getMessage());
				return INPUT;
			}
			


	}


	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


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


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public ParsedResponse getParsedResp() {
		return parsedResp;
	}


	public void setParsedResp(ParsedResponse parsedResp) {
		this.parsedResp = parsedResp;
	}

}
