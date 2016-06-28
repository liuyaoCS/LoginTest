<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body>
   <form action="/UserSystem/LoginServlet" method="post">
   	username:<input type="text" name="username"></br>
   	password:<input type="text" name="password"></br>
   	<input type="checkbox" name="autologin" value="ok">auto login</br>
   	<input type="submit" value="submit">
   </form>
   <%--
   		String message=(String)request.getAttribute("loginmessage");
   		out.write(message);
   		
    --%>
  </body>
</html>
