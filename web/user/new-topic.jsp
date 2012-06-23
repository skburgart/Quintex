<%-- 
    Document   : new-topic
    Created on : Aug 4, 2011, 10:31:03 PM
    Author     : steve
--%>

<%@page import="com.quintex.value.BoardVO"%>
<%@page import="com.quintex.database.BoardDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>   
    <%
        BoardDBO bdbo = new BoardDBO();
        int boardid = 0;

        String txtBoardid = request.getParameter("boardid");
        if (txtBoardid == null) {
            out.println("No board id");
            return;
        }

        try {
            boardid = Integer.parseInt(txtBoardid);
        } catch (NumberFormatException nfe) {
            out.println("Board id not a number");
            return;
        }

        BoardVO thisBoard = bdbo.get(boardid);

        if (thisBoard == null) {
            out.println("Board not found");
            return;
        }
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
                <input type="hidden" name="board-id" value="<%=boardid%>" />
                <h2 class="center"><%= thisBoard.getTitle()%></h2>
                <h3>Create New Topic</h3>
                <div class="msg-error" id="post-msg"></div>
                <p>Topic Title</p>
                <p><input name="topic-title" class="topic-input" id="topic-title" type="text" /></p>
                <p>Message</p>
                <p><textarea wrap="soft" name="topic-message" class="topic-input" id="message-box" cols="50" rows="9" ></textarea></p>
                <p><input class="submit topic-input" type="submit" value="Post Topic" />
            </form>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
