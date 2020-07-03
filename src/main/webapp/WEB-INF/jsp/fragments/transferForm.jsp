<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form method="post" action="/account/transfer" modelAttribute="accounts">
    <div class="col">
        <div class="alert alert-info" role="alert">
            <p><spring:message key="select.your.card.number"/></p>
        </div>

        <div class="form-group">
            <label class="control-label"><spring:message key="card.number.of.sender"/></label>
            <div class="controls">
                <select class="form-control" name="accountCode">
                    <c:forEach var="account" items="${accounts}">
                        <option value="${account.code}">${account.cardNumber}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="alert alert-info" role="alert">
            <p><spring:message key="enter.sum.of.transfer"/></p>
        </div>
        <div class="form-group">
            <label class="control-label"><spring:message key="sum.of.transfer"/></label>

            <div class="controls">
                <input type="number" placeholder=
                    <spring:message key="placeholder.sum.of.transfer"/>
                        name="sum" class="form-control" required>
            </div>
        </div>
        <div class="alert alert-info" role="alert">
            <p><spring:message key="enter.card.number.of.receiver"/></p>
        </div>
        <div class="form-group">
            <label class="control-label"><spring:message key="card.number.of.receiver"/></label>

            <div class="controls">
                <input type="number" placeholder=
                    <spring:message key="placeholder.card.number.of.receiver"/>
                        name="cardNumber" class="form-control" required>
            </div>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
        <div class="row">
            <div class="col-sm-3">
                <div class="form-group">
                    <button class="btn btn-success"><spring:message key="transfer"/></button>
                </div>
            </div>
        </div>
    </div>
</form:form>
</body>
</html>
