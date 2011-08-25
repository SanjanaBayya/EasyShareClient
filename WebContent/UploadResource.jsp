<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Upload</title>
</head>

<body>

<s:actionerror />
<s:fielderror />
<s:form action="UploadResource.action" method="POST" enctype="multipart/form-data">
<tr>
<td colspan="2"><h1>Upload material</h1></td>
</tr>

<s:file name="upload" label="File"/>
<s:submit label="Upload"/>
</s:form>
</body>
</html>