<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%--@elvariable id="car" type="paperSubmission.model.template.Car"--%>
<spring:form method="post" modelAttribute="car" action="/home">
    <spring:input path="id"/>
    <spring:input path="name"/>
    <spring:input path="amount"/>
    <input type="submit"/>
</spring:form>

</body>
</html>
