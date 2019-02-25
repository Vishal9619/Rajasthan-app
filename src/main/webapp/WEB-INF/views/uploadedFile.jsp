<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uploaded file</title>
</head>
<body>
  <h1>Succesfully uploaded files</h1>
  <hr>

  <h2>${sz} files uploaded successfully!</h2>
   <ol>
  	<c:forEach items="${files}" var="file">
  	 * ${file} <br>
  	</c:forEach>
  	</ol>  
  </body>
</html>