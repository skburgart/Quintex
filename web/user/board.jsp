<%-- 
    Document   : board
    Created on : Aug 4, 2011, 8:15:55 PM
    Author     : steve
--%>

<%@page import="com.quintex.value.TopicVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.quintex.value.BoardVO"%>
<%@page import="com.quintex.objects.BoardDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        BoardDBO bdbo = new BoardDBO();

        int boardid = Integer.parseInt(request.getParameter("boardid"));

        BoardVO thisBoard = bdbo.get(boardid);
        ArrayList<TopicVO> topics = bdbo.getTopics(boardid);
    %>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2 class="center"><%= thisBoard.getTitle()%></h2>
            <table id="boards">
                <tr>
                    <th>Topic Title</th><th>Created By</th><th>Msgs</th><th>Last Post</th>
                </tr>
                <%
                    for (TopicVO topic : topics) {
                        out.println("<tr>");
                        out.println("<td class='board'><a href='topic.jsp?topicid=" + topic.getTopicid() + "'>" + topic.getTitle() + "</a></td>");
                        out.println("<td>" + topic.getCreator() + "</td>");
                        out.println("<td>" + topic.getMessages() + "</td>");
                        out.println("<td>" + topic.getLatest() + "</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
