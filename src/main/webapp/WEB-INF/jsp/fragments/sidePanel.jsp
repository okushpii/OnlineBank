<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-sm-3" style="alignment: left">
    <div class="list-group">
        <a href="/user/${user.code}" class="list-group-item active"><span><spring:message
                key="accounts.operation"/></span></a>
        <a href="/transfer" class="list-group-item"><span><spring:message key="transfer"/></span></a>
        <a href="/exchangeStepOne" class="list-group-item"><span><spring:message key="currency.exchange"/> </span></a>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
