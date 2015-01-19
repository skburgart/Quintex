<%--
    Document   : header
    Created on : Jul 31, 2011, 7:33:31 PM
    Author     : Steven Burgart
    Email      : skburgart@gmail.com
--%>

<div id='header'>
    <h1>Quintex</h1>
    <ul>
        <li><a href="profile.jsp"><%= session.getAttribute("username")%></a></li>
        <li><a href="index.jsp">Boards</a></li>
        <li><a href="users.jsp">User List</a></li>
        <% if (((String) session.getAttribute("flags")).contains("a")) {%>
        <li><a href="../admin/index.jsp">Admin</a></li>
        <%}%>
        <li><a href="logout">Logout</a></li>
    </ul>
</div>