<%--
  Created by IntelliJ IDEA.
  User: azatfanisovic
  Date: 02.03.18
  Time: 22:21
  To change this template use File | Settings | File Models.Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home page</title>
</head>
<center><h2>Home Page</h2></center>

Welcome <c:out value="${nickName}" />
<c:choose>
    <c:when test="${empty publications}">
        You have no publications yet.
    </c:when>
    <c:otherwise>
        <p> There you can see all your publications</p>
        <table>
            <c:forEach var="publication" items="${publications}">
                <tr>
                    <td><c:out value="${publication.title}"></c:out></td>
                    <td><c:out value="${publication.description}"></c:out></td>
                    <td><c:out value="${publication.link}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<a href="addpub.jsp">Add new publication</a>
<a href="publication">See all the publications</a>
<div style="text-align: right"><a href="/logout">Logout</a></div>
</body>
</html>
