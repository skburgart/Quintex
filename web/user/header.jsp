<%--
    Document   : header
    Created on : Jul 31, 2011, 7:33:31 PM
    Author     : steve
--%>

<div id='header'>
    <h1>Quintex</h1>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <% if (((String) session.getAttribute("flags")).contains("a")) {%>
        <li><a href="../admin/index.jsp">Admin</a></li>
        <%}%>
        <li><a href="logout">Logout</a></li>
    </ul>
</div>