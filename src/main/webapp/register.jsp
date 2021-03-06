<%--
    Document   : register
    Created on : Jul 25, 2011, 10:51:37 PM
    Author     : Steven Burgart
    Email      : skburgart@gmail.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quintex</title>
        <jsp:include page="common-headers.jsp"/>
        <script src="js/form-validation.js"></script>
        <script src="js/ajax/register.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content-center'>
            <div class="msg-error" id="register-msg"></div>
            <h2>Registration</h2>
            <form action="javascript:register()">
                <table id="form-table">
                    <tr>
                        <td class="left">Username</td>
                        <td><input type="text" name="register-username" class="register-input" /></td>
                    </tr>
                    <tr>
                        <td class="left">E-mail</td>
                        <td><input type="text" name="register-email" class="register-input" /></td>
                    </tr>
                    <tr>
                        <td class="left">Password</td>
                        <td><input type="password" name="register-password1" class="register-input" /></td>
                    </tr>
                    <tr>
                        <td class="left">Confirm Password</td>
                        <td><input type="password" name="register-password2" class="register-input" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Register" class="submit register-input"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
