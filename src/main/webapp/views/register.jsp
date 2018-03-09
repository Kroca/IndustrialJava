<%--
  Created by IntelliJ IDEA.
  User: azatfanisovic
  Date: 02.03.18
  Time: 22:08
  To change this template use File | Settings | File Models.Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <script>
        function validate()
        {
            var name = document.form.name.value;
            var email = document.form.email.value;
            var nickName = document.form.nickName.value;
            var password = document.form.password.value;
            var conpassword= document.form.conpassword.value;

            if (name==null || name=="")
            {
                alert("Full Name can't be blank");
                return false;
            }
            else if (email==null || email=="")
            {
                alert("Email can't be blank");
                return false;
            }
            else if (nickName==null || nickName=="")
            {
                alert("Username can't be blank");
                return false;
            }
            else if(password.length<6)
            {
                alert("Password must be at least 6 characters long.");
                return false;
            }
            else if (password!=conpassword)
            {
                alert("Confirm Password should match with the Password");
                return false;
            }
        }
    </script>
</head>
<body>
<center><h2>Register to get access to puplication helper</h2></center>
<form name="form" action="/register" method="post" onsubmit="return validate()">
    <table align="center">
        <tr>
            <td>Full Name</td>
            <td><input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td>Username</td>
            <td><input type="text" name="nickName" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="conpassword" /></td>
        </tr>
        <tr>
            <td><%=(request.getAttribute("errMessage") == null) ? ""
                    : request.getAttribute("errMessage")%></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Register"></input><input
                    type="reset" value="Reset"></input></td>
        </tr>
        <tr>
            <td> If you already registered <a href="login"> Login here</a> </td>
        </tr>
    </table>
</form>

</body>
</html>
