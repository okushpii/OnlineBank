
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="auth" uri="/WEB-INF/auth.tld" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <a class="navbar-brand" href="#">
        <img src="" alt="logo" style="width:40px;">
    </a>
</nav>
<nav>

    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/">Home page</a>
        </li>
<auth:authorize>
        <li class="nav-item">
            <a class="nav-link" href="/user/${user.code}">${user.name}</a>
        </li>
</auth:authorize>
        <li class="nav-item">
            <a class="nav-link" href="/login">Sign in</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link 2</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link 3</a>
        </li>
    </ul>
</nav>
