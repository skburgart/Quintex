<%-- 
    Document   : new-topic
    Created on : Aug 4, 2011, 10:31:03 PM
    Author     : steve
--%>

<%@page import="com.quintex.value.BoardVO"%>
<%@page import="com.quintex.objects.BoardDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>   
    <%
        BoardDBO bdbo = new BoardDBO();

        int boardid = Integer.parseInt(request.getParameter("boardid"));

        BoardVO thisBoard = bdbo.get(boardid);
    %>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
        <script src="/Quintex/js/form-validation.js"></script>
        <script src="/Quintex/js/ajax/post-topic.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <form action="javascript:newTopic()">
                <h2 class="center"><%= thisBoard.getTitle()%></h2>
                <h3>Create New Topic</h3>
                <p>Topic Title</p>
                <p><input name="topic-title" id="new-topic-input" type="text" /></p>
                <p>Message</p>
                <p><textarea name="topic-message" id="message-textarea" cols="50" rows="10" ></textarea></p>
                <p><input class="submit" type="submit" value="Post Topic" />
            </form>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
