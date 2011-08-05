<%-- 
    Document   : view-topic
    Created on : Aug 4, 2011, 8:15:34 PM
    Author     : steve
--%>

<%@page import="com.quintex.value.BoardVO"%>
<%@page import="com.quintex.objects.BoardDBO"%>
<%@page import="com.quintex.value.MessageVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.quintex.value.TopicVO"%>
<%@page import="com.quintex.objects.TopicDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <%        
            BoardDBO bdbo = new BoardDBO();
            TopicDBO tdbo = new TopicDBO();

            int topicid = Integer.parseInt(request.getParameter("topicid"));
            TopicVO thisTopic = tdbo.get(topicid);
            
            int boardid = thisTopic.getBoardid();
            BoardVO thisBoard = bdbo.get(boardid);

            ArrayList<MessageVO> messages = tdbo.getMessages(topicid);
        %>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2 class="center"><%= thisBoard.getTitle()%></h2>
            <h3 class="center"><%= thisTopic.getTitle()%></h3>
            <table id="topic">
                <tr ><th style="background: #C4C4C4;text-align: center;"><a href="new-message.jsp?topicid=<%= thisTopic.getTopicid()%>">Post Message</a></tr>
                <%
                    for (MessageVO message : messages) {
                        out.println("<tr>");
                        out.println("<th>By <a href='profile.jsp?userid=" + message.getUserid() + "'>" + message.getUsername() + "</a> at " + message.getTimestamp() + " </th></tr>");
                        out.println("<tr><td>" + message.getBody() + "</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
