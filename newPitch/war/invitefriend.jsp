
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send an e-mail</title>
</head>
<body>
    <form action="Servlets/InviteFriendServlet" method="post">
        <table border="0" width="35%" align="center">
            <caption><h2>Invite a friend to try out Pitch!</h2></caption>
            <tr>
                <td width="100%">Email address </td>
                <td><input type="text" name="email" size="50"/></td>
            </tr>
            
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Send Invite"/></td>
            </tr>
        </table>
         
    </form>
</body>
</html>