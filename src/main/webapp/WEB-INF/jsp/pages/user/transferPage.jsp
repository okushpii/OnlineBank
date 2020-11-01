<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/jsp/fragments/common/head.jsp" %>
<%@include file="/WEB-INF/jsp/fragments/common/header.jsp" %>
<body>
<br><br>
<div class="container" style="alignment: left">
    <div class="row">
        <%@include file="/WEB-INF/jsp/fragments/user/sidePanel.jsp" %>
        <%@include file="/WEB-INF/jsp/fragments/user/transferForm.jsp" %>
    </div>
</div>
</body>
<%@include file="/WEB-INF/jsp/fragments/common/scripts.jsp" %>
</html>
