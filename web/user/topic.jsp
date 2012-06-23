<%-- 
    Document   : view-topic
    Created on : Aug 4, 2011, 8:15:34 PM
    Author     : steve
--%>

<%@page import="com.quintex.database.MessageDBO"%>
<%@page import="com.quintex.value.BoardVO"%>
<%@page import="com.quintex.database.BoardDBO"%>
<%@page import="com.quintex.value.MessageVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.quintex.value.TopicVO"%>
<%@page import="com.quintex.database.TopicDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        BoardDBO bdbo = new BoardDBO();
        TopicDBO tdbo = new TopicDBO();
        MessageDBO mdbo = new MessageDBO();

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

        ArrayList<MessageVO> messages = mdbo.getFromTopicid(topicid);
    %>
    <head>    
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2 class="center"><%= thisBoard.getTitle()%></h2>
            <h3 class="center"><%= thisTopic.getTitle()%></h3>
            <table id="topic">
                <tr ><th class="options"><a href="board.jsp?boardid=<%=boardid%>">Topic List</a> - <a href="new-message.jsp?topicid=<%= thisTopic.getTopicid()%>">Post Message</a></tr>
                <%
                    for (MessageVO message : messages) {
                        out.println("<tr>");
                        out.println("<th><a href='profile.jsp?userid=" + message.getUserid() + "'>" + message.getUsername() + "</a> at " + message.getTimestamp() + " </th></tr>");
                        out.println("<tr><td>" + message.getBody() + "</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
