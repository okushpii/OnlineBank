<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="auth" uri="/WEB-INF/auth.tld" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: gold">

    <a class="navbar-brand" href="/">
        <img class="d-inline-block align-top" style="width:40px;" src= <c:url value="/img/logotype.svg"/>>
    </a>

    <a class="navbar-brand" href="/"><spring:message key="homePage"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/login"><spring:message key="login"/> <span class="sr-only">(current)</span></a>
            </li>
            <auth:authorize>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${user.name}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/user/${user.code}">${user.name}</a>
                        <a class="dropdown-item" href="/user/${user.code}">${user.email}</a>
                        <div class="dropdown-divider"></div>
                        <form:form method="post" action="/logout">
                            <button class="dropdown-item"><spring:message key="logout"/></button>
                        </form:form>
                    </div>
                </li>
            </auth:authorize>

            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <spring:message key="language"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="?lang=en"><spring:message key="english"/></a>
                    <a class="dropdown-item" href="?lang=ru"><spring:message key="russian"/></a>
                    <div class="dropdown-divider"></div>
                </div>
            </li>
        </ul>
    </div>
</nav>
