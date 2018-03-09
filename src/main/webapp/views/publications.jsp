<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: azatfanisovic
  Date: 03.03.18
  Time: 17:26
  To change this template use File | Settings | File Models.Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add publication</title>
    <c:choose>
        <c:when test="${empty publications}">
            There are no publications found.
        </c:when>
        <c:otherwise>
            <p> There you can see all present publications</p>
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
</head>
<body>

</body>
</html>
