<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<body>
  <%
  String respons = (String)request.getSession().getAttribute("DB_resp");
    if (respons == null) {
        
    
   %>
   <b>WTF it didn't work</b>
   
   <% }
   else
   {
  
   %>
   
   <%=respons %>   
   <%
   }
   %>
  </body>
 </html>
   