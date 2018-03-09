<%--
  Created by IntelliJ IDEA.
  User: azatfanisovic
  Date: 06.03.18
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add publication</title>
    <h5>Add new publication</h5>
    <form name="form" action="home" method="post">
        <table align="center">
            <tr>
                <td>Title</td>
                <td><input type="text" name="title"/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description"/></td>
            </tr>
            <tr>
                <td>Link</td>
                <td><input type="text" name="link"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="addPublication"></input><input
                        type="reset" value="Reset"></input></td>
            </tr>
        </table>
    </form>
</head>
<body>

</body>
</html>
