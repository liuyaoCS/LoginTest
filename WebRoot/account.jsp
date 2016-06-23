<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body>
   <form action="/UserSystem/AccountServlet" method="post">
   	to user:<input type="text" name="userto"></br>
   	money:<input type="text" name="money"></br>
   	<input type="submit" value="transfer">
   </form>
   <%--
   		String message=(String)request.getAttribute("loginmessage");
   		out.write(message);
   		
    --%>
  </body>
</html>
