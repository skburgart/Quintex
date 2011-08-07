<%-- 
    Document   : users
    Created on : Aug 4, 2011, 8:15:18 PM
    Author     : steve
--%>

<%@page import="com.quintex.value.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.quintex.objects.UserDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        UserDBO udbo = new UserDBO();
        
        ArrayList<UserVO> users = udbo.getAll();
    %>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2>User List</h2>
            <table id="user-list">
                <tr><th>Username</th><th>Topics</th><th>Posts</th></tr>
                <% for (UserVO user : users) { 
                out.println("<tr>");
                out.println("<td><a href='profile.jsp?userid=" + user.getUserid() + "'>" + user.getUsername() + "</a>");
                out.println("<td>" + user.getTopics() + "</td>");
                out.println("<td>" + user.getMessages() + "</td>");
                out.println("</tr>");
                }%>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
