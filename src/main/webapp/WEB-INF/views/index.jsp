<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="/resources/css/style.css">
<script type="text/javascript" src="/resources/js/app.js"></script>

<title>Upload Portal</title>
</head>
<body>
  <h1>Upload multiple files</h1>
  <hr>

  <div class="form">
    

    <form action="/upload" method="post" enctype="multipart/form-data">
      Select File to Upload:<input type="file" name="fileName" multiple="multiple">
      <br>
      <input type="submit" value="Upload">
    </form>

  </div>

</body>
</html>