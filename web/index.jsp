<!--
    Author     : Steven Burgart
    Created on : Jul 13, 2011
-->

<!DOCTYPE html>
<html>
    <head>
        <title>Dynamic</title>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
        <link rel='stylesheet' type='text/css' href='css/main.css'>
        <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Bowlby+One+SC'>
        <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Muli'>
        <script src="js/jquery-1.6.2.min.js"></script>
        <script src="js/login-box.js"></script>
    </head>
    <body>
        <div id='header'>
            <h1>Dynamic Website</h1>
            <ul>
                <li>Home</li>
                <li>About</li>
                <li>
                    <a href="javascript:showLogin()">Login</a>
                    <span id="login">
                        <form>
                            <p>Username</p>
                            <p><input type="text" class="text"/></p>
                            <p>Password</p>
                            <p><input type="text" class="text"/></p>
                            <p style="float:left">
                                <a href="#">Register</a><br />
                                <a href="#">Forgot Password</a>
                            </p>
                            <input style="float: right" type="submit" id="submit" value="Login" />
                        </form>
                    </span>
                </li>
            </ul>
        </div>
        <div id='content'>
            <h2>Welcome!</h2>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
                Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna felis sollicitudin mauris. Integer in mauris eu nibh euismod gravida. Duis ac tellus et risus vulputate vehicula. Donec lobortis risus a elit. Etiam tempor. Ut ullamcorper, ligula eu tempor congue, eros est euismod turpis, id tincidunt sapien risus a quam. Maecenas fermentum consequat mi. Donec fermentum. Pellentesque malesuada nulla a mi. Duis sapien sem, aliquet nec, commodo eget, consequat quis, neque. Aliquam faucibus, elit ut dictum aliquet, felis nisl adipiscing sapien, sed malesuada diam lacus eget erat. Cras mollis scelerisque nunc. Nullam arcu. Aliquam consequat. Curabitur augue lorem, dapibus quis, laoreet et, pretium ac, nisi. Aenean magna nisl, mollis quis, molestie eu, feugiat in, orci. In hac habitasse platea dictumst.
            </p>
        </div>
        <div id='footer'>
            by <a href='http://ww2.cs.fsu.edu/~burgart'>Steven Burgart</a>
        </div>
    </body>
</html>
