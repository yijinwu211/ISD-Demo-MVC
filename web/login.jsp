<%-- 
    Document   : login
    Created on : Aug 11, 2018, 9:34:31 PM
    Author     : George
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/demo.css"> 
        <script type="text/javascript" src="js/script.js"></script>
        <title>JSP Page</title>
    </head>
    <body onload="startTime()">
        <%
            String exist = (String) session.getAttribute("existErr");
            String idErr = (String) session.getAttribute("IDErr");
            String passError = (String) session.getAttribute("passErr");
        %>
        <div><span class="time" id="time" ></span></div>
        <h1>Enter your details to login: <span class="error"><c:if test="${existErr!=null}"><span class="error"><c:out value="${existErr}"/></span></c:if></span></h1>
        
        <form action="LoginServlet" method="post">
            <table>                
                <tr><td>Student ID:</td><td><input type="text" name="ID" value="<%=(idErr != null ? idErr : "")%>"></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password" value="<%=(passError != null ? passError : "")%>"></td></tr>                
                <tr><td><input type="hidden" value="submitted" name="submitted"></td>
                    <td><input class="button" type="submit" value="Login"> 
                        &nbsp; 
                        <button class="button" type="button" onclick="location.href = 'index.jsp'" > Home Page </button>
                    </td>
                </tr>
            </table>
        </form>
        <%
            if (request.getParameter("submitted") != null) {
                exist = idErr = passError = null;
            }           
        %>
    </body>
</html>
