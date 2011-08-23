<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:form action="DesignateUser.action">
	<s:textfield name="email" label="User Email:"/>
	<s:select label="User Type" name="userType" 
   headerValue="--- Please Select ---"
  headerKey="1" list="{'ADMIN','FACILITATOR'}" /><br>
  <s:submit/>
</s:form>
</body>
</html>