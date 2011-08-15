<%--
    Document   : edit-profile.jsp
    Author     : steve
--%>

<%@page import="com.quintex.value.UserVO"%>
<%@page import="com.quintex.objects.UserDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2 class="center">Change Password</h2>            
            <table id="profile-table">
                <tr><td class="left">Current Password</td><td><input type="password" /></td></tr>
                <tr><td class="left">New Password</td><td><input type="password" /></td></tr>
                <tr><td class="left">Confirm New Password</td><td><input type="password" /></td></tr>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
