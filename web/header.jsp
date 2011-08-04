<%--
    Document   : header
    Created on : Jul 25, 2011, 10:52:55 PM
    Author     : steve
--%>

<div id='header'>
    <h1>Quintex</h1>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li>
            <a href="javascript:showLogin()">Login</a>
            <div id="login">
                <form action="javascript:login()">
                    <div class="msg-error" id="login-msg"></div>
                    <p>Username</p>
                    <p><input type="text" name="login-username" class="login login-input"/></p>
                    <p>Password</p>
                    <p><input type="password" name="login-password" class="login login-input"/></p>
                    <p style="float:left">
                        <a href="register.jsp">Register</a><br />
                        <a href="forgot-password.jsp">Forgot Password</a>
                    </p>
                    <input style="float: right" type="submit" class="submit login-input" value="Login" />
                </form>
            </div>
        </li>
    </ul>
</div>