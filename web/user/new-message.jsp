<%-- 
    Document   : new-message
    Created on : Aug 4, 2011, 10:31:11 PM
    Author     : Steven Burgart
    Email      : skburgart@gmail.com
--%>

<%@page import="com.quintex.database.TopicDBO"%>
<%@page import="com.quintex.value.TopicVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.quintex.value.BoardVO"%>
<%@page import="com.quintex.database.BoardDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        BoardDBO bdbo = new BoardDBO();
        TopicDBO tdbo = new TopicDBO();

        int topicid;
        String txtTopicid = request.getParameter("topicid");
        if (txtTopicid == null) {
            out.println("No topic id");
            return;
        }

        try {
            topicid = Integer.parseInt(txtTopicid);
        } catch (NumberFormatException nfe) {
            out.println("Topic id not a number");
            return;
        }
        
        TopicVO thisTopic = tdbo.get(topicid);

        if (thisTopic == null) {
            out.println("Topic not found");
            return;
        }
        
        int boardid = thisTopic.getBoardid();
        BoardVO thisBoard = bdbo.get(boardid);

    %>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
        <script src="/Quintex/js/form-validation.js"></script>
        <script src="/Quintex/js/ajax/post-message.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <form action="javascript:newMessage()">
                <input type="hidden" name="topic-id" value="<%=topicid%>" />
                <h2 class="center"><%= thisBoard.getTitle()%></h2>
                <h3 class="center"><%= thisTopic.getTitle()%></h3>
                <h3>Post a Message</h3>
                <div class="msg-error" id="post-msg"></div>
                <p><textarea wrap="soft" name="post-message" class="message-input" id="message-box" cols="50" rows="9" ></textarea></p>
                <p><input class="submit message-input" type="submit" value="Post Message" />
            </form>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
