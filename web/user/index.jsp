<%--
    Document   : index.jsp
    Author     : steve
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Dynamic</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2>Logged-in Section</h2>
            <p>Welcome <%= session.getAttribute("username")%>!</p>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
