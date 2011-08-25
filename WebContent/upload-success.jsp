<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>Upload success</title>
</head>

<body>
<table>
<tr>

<td colspan="2"><h1>File Upload Example</h1></td>
</tr>

<tr>
<td class="tdLabel"><label for="doUpload_upload" class="label">Content Type:</label></td>
<td><s:property value="uploadContentType" /></td>
</tr>

<tr>
<td class="tdLabel"><label for="doUpload_upload" class="label">File Name:</label></td>
<td ><s:property value="uploadFileName" /></td>
</tr>


<tr>
<td class="tdLabel"><label for="doUpload_upload" class="label">File:</label></td>
<td><s:property value="upload" /></td>
</tr>

<tr>
<td class="tdLabel"><label for="doUpload_upload" class="label">File Caption:</label></td>
<td><s:property value="caption" /></td>
</tr>


</table>

</body>
</html>