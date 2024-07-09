<%-- 
    Document   : login.jsp
    Created on : Jul 8, 2024, 10:35:16 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>

    </head>
    <body>
        <div style="text-align: center">
            <h1>Login</h1>
            <form action="login" method="post">
                <label for="account">User name:</label>
                <input name="account" size="30" />
                <br><br>
                <label for="password">Password:</label>
                <input type="password" name="password" size="30" />
                <br>${message}
                <br><br>           
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#loginForm").validate({
                rules: {
                    account: {
                        required: true
                    },

                    password: "required"
                },

                messages: {
                    account: {
                        required: "Please enter email"
                    },

                    password: "Please enter password"
                }
            });

        });
    </script>
</html>
