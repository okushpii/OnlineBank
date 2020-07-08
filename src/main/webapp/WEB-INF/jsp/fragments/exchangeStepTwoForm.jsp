<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form method="get" action="/exchangeStepThree">
    <div class="col">
        <div class="form-group">

            <div class="alert alert-info" role="alert">
                <p><spring:message key="select.accounts"/></p>
            </div>

            <label class="control-label"><spring:message key="account.from"/> ${currencyFrom.name}</label>
            <div class="controls">
                <select class="form-control" name="accountCode">
                    <c:forEach var="account" items="${accountsFrom}">
                        <option value="${account.code}">${account.cardNumber}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"><spring:message key="account.to"/> ${currencyTo.name}</label>
            <div class="controls">
                <select class="form-control" name="cardNumber">
                    <c:forEach var="account" items="${accountsTo}">
                        <option value="${account.cardNumber}">${account.cardNumber}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="alert alert-info" role="alert">
            <p><spring:message key="enter.the.sum.of.exchange"/></p>
        </div>

        <div class="form-group">
            <label class="control-label"><spring:message key="sum.of.exchange"/></label>

            <div class="controls">
                <input type="number" placeholder=
                    <spring:message key="placeholder.sum.of.exchange"/>
                        name="sum" class="form-control" required>
            </div>
        </div>

        <br>
        <div class="row">
            <div class="col-sm-3">
                <div class="form-group">
                    <button class="btn btn-success"><spring:message key="threes.step"/></button>
                </div>
            </div>
        </div>
    </div>
</form:form>

</body>
</html>
