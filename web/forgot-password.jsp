<%--
    Document   : forgot-password
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quintex</title>
        <jsp:include page="common-headers.jsp"/>
        <script src="js/form-validation.js"></script>
        <script src="js/ajax/reset-password.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content-center'>
            <div class="msg-error" id="reset-msg"></div>
            <h2>Forgot Password</h2>
            <form action="javascript:resetPassword()">
                <table id="form-table">
                    <tr>
                        <td class="left">Username</td>
                        <td><input type="text" name="reset-username" class="reset-input" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Reset Password" class="submit reset-input"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
