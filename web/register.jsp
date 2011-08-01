<%--
    Document   : register
    Created on : Jul 25, 2011, 10:51:37 PM
    Author     : steve
--%>

<!DOCTYPE html>
<!--TODO: register jsp-->
<html>
    <head>
        <title>Dynamic</title>
        <jsp:include page="common-headers.jsp"/>
        <script src="js/ajax/register.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content-center'>
            <h2>Registration</h2>
            <table id="register-table">
                <tr>
                    <td class="left">Username</td>
                    <td><input type="text" /></td>
                </tr>
                <tr>
                    <td class="left">Password</td>
                    <td><input type="text" /></td>
                </tr>
                <tr>
                    <td class="left">Confirm Password</td>
                    <td><input type="text" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" id="submit"/></td>
                </tr>
            </table>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
