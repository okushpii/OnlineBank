<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.04.2020
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" action="/login" modelAttribute="authorizationRequest">
    <form:input type="text" path = "username"/>
    <br><br>
    <form:input type="password" path= "password"/>
    <br><br>
    <input type="submit"/>
    <p>${error}</p>
</form:form>
</body>
</html>
