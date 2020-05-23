<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User page</h1>
<p>${user.name}</p>
<p>${user.email}</p
<c:forEach items="${user.accounts}" var="accounts">
    <p>${accounts.money}</p>
</c:forEach>
</body>
</html>
