<%--
    Document   : header
    Created on : Jul 25, 2011, 10:52:55 PM
    Author     : steve
--%>

<div id='header'>
    <h1>Dynamic Website</h1>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li>
            <a href="javascript:showLogin()">Login</a>
            <span id="login">
                <form action="javascript:login()">
                    <div id="login-msg"></div>
                    <p>Username</p>
                    <p><input type="text" name="login-username" class="login"/></p>
                    <p>Password</p>
                    <p><input type="password" name="login-password" class="login"/></p>
                    <p style="float:left">
                        <a href="register.jsp">Register</a><br />
                        <a href="#">Forgot Password</a>
                    </p>
                    <input style="float: right" type="submit" id="submit" value="Login" />
                </form>
            </span>
        </li>
    </ul>
</div>