<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/jsp/fragments/head.jsp" %>
<%@include file="/WEB-INF/jsp/fragments/header.jsp" %>
<form:form method="post" action="/login" modelAttribute="authorizationRequest">
<%@include file="/WEB-INF/jsp/fragments/loginForm.jsp"%>
</form:form>
<body>
</body>
</html>
