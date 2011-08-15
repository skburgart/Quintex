<%--
    Document   : edit-profile.jsp
    Author     : steve
--%>

<%@page import="com.quintex.value.UserVO"%>
<%@page import="com.quintex.objects.UserDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        UserDBO udbo = new UserDBO();

        UserVO user = udbo.get((Integer)session.getAttribute("userid"));
    %>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2 class="center">Edit Profile</h2>
            <table id="profile-table">
                <tr><td class="left">Email</td><td><input type="text" value="<%=user.getEmail()%>" /></td></tr>
                <tr><td class="left">Signature</td><td><textarea id="email" rows="2" cols="40"><%=user.getSignature()%></textarea></td></tr>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
