<%--
    Document   : index.jsp
    Author     : steve
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.quintex.value.BoardVO"%>
<%@page import="com.quintex.objects.BoardDBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>   
    <%
        BoardDBO bdbo = new BoardDBO();
        ArrayList<BoardVO> boards = bdbo.getBoards();
    %>
    <head>
        <title>Quintex</title>
        <jsp:include page="/common-headers.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id='content'>
            <h2 class="center">Board List</h2>
            <table id="boards">
                <tr>
                    <th>Board</th><th>Topics</th><th>Msgs</th><th>Created</th>
                </tr>
                <%
                    for (BoardVO board : boards) {
                        out.println("<tr>");
                        out.println("<td class='board'><a href='board.jsp?boardid=" + board.getBoardid() + "'>" + board.getTitle() + "</a><br />");
                        out.println(board.getDescription() + "</td>");
                        out.println("<td>" + board.getMessages() + "</td>");
                        out.println("<td>" + board.getTopics() + "</td>");
                        out.println("<td>" + board.getTimestamp() + "</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
