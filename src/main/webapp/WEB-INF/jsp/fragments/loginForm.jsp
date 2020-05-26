<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>
<form:form method="post" action="/auth" modelAttribute="authRequest">
    <form:input type="text" path = "username"/>
    <br><br>
    <form:input type="password" path= "password"/>
    <br><br>
    <input type="submit"/>
    <p>${error}</p>
</form:form>
</body>
</html>
