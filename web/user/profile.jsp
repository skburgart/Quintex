<%-- 
    Document   : profile
    Created on : Aug 4, 2011, 8:20:06 PM
    Author     : steve
--%>

<%@page import="com.quintex.value.UserVO"%>
<%@page import="com.quintex.objects.UserDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    <%
        UserDBO udbo = new UserDBO();

        int userid = -1;
        String txtUserid = request.getParameter("userid");
        if (txtUserid == null) {
            userid = (Integer) session.getAttribute("userid");
        }

        if (userid == -1) {
            try {
                userid = Integer.parseInt(txtUserid);
            } catch (NumberFormatException nfe) {
                out.println("User id not a number");
                return;
            }
        }

        UserVO user = udbo.get(userid);

        if (user == null) {
            out.println("User not found");
            return;
        }
    %>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2>Profile For <%=user.getUsername()%></h2>
            <table>
                <tr><td>Userid</td><td><%=user.getUserid() %></td></tr>
                <tr><td>Email</td><td><%=user.getEmail() %></td></tr>
                <tr><td>Registered</td><td><%=user.getRegistered() %></td></tr>
                <tr><td>Signature</td><td><%=user.getSignature() %></td></tr>
                <tr><td>Topics Created</td><td><%=user.getTopics() %></td></tr>
                <tr><td>Messages Posted</td><td><%=user.getMessages() %></td></tr>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
