<%--
  Created by IntelliJ IDEA.
  User: azatfanisovic
  Date: 03.03.18
  Time: 11:14
  To change this template use File | Settings | File Models.Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script>
        function validate()
        {
            var username = document.form.username.value;
            var password = document.form.password.value;

            if (username==null || username=="")
            {
                alert("Username cannot be blank");
                return false;
            }
            else if(password==null || password=="")
            {
                alert("Password cannot be blank");
                return false;
            }
        }
    </script>
</head>
<body>
<div style="text-align:center"><h1>Login</h1> </div>
<br>
<form name="form" action="login" method="post" onsubmit="return validate()">
    <!-- Do not use table to format fields. As a good practice use CSS -->
    <table align="center">
        <tr>
            <td>Username</td>
            <td><input type="text" name="nickName" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr> <!-- refer to the video to understand request.getAttribute() -->
            <td><span style="color:red"><%=(request.getAttribute("errMessage") == null) ? ""
                    : request.getAttribute("errMessage")%></span></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Login"></input><input
                    type="reset" value="Reset"></input></td>
        </tr>
        <tr>
            <td>If you are not registered yet <a href="Register.jsp"> Register here</a> </td>
        </tr>
    </table>
</form>

</body>
</html>
