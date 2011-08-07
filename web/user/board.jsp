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
        int boardid;
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
            <table id="board-list">
                <tr ><th class="options" colspan="4"><a href="index.jsp"/>Board List</a> - <a href="new-topic.jsp?boardid=<%= thisBoard.getBoardid()%>">Create Topic</a></tr>
                <tr><th>Topic Title</th><th>Created By</th><th>Msgs</th><th>Last Post</th></tr>
                <%
                    for (TopicVO topic : topics) {
                        out.println("<tr>");
                        out.println("<td class='left'><a href='topic.jsp?topicid=" + topic.getTopicid() + "'>" + topic.getTitle() + "</a></td>");
                        out.println("<td><a href='profile.jsp?" + topic.getUserid() + "'>" + topic.getCreator() + "</a></td>");
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
