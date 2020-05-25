<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>First Servlet</title>
</head>
<body>
<form action="emailServlet" method="post">
  Input email recipient:
  <input type="email" name="email"><br>
  Input subject:
  <input type="text" name="subject"><br>
  Input message:
  <textarea name="message"></textarea>
  <input type="submit" name="exec" value="Execute"/>
</form>
</body>
</html>
