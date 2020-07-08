<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form method="get" action="/exchangeStepTwo">
    <div class="col">
        <div class="form-group">

            <div class="alert alert-info" role="alert">
                <p><spring:message key="select.currencies"/></p>
            </div>

            <label class="control-label"><spring:message key="currency.from"/></label>
            <div class="controls">
                <select class="form-control" name="currencyFromCode">
                    <c:forEach var="currencies" items="${currencies}">
                        <option value=${currencies.code}>${currencies.name}</option>
                    </c:forEach>
                </select>
            </div>

            <label class="control-label"><spring:message key="currency.to"/></label>
            <div class="controls">
                <select class="form-control" name="currencyToCode">
                    <c:forEach var="currencies" items="${currencies}">
                        <option value=${currencies.code}>${currencies.name}</option>
                    </c:forEach>
                </select>
            </div>

            <br>
            <div class="row">
                <div class="col-sm-3">
                    <div class="form-group">
                        <button class="btn btn-success"><spring:message key="second.step"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>
</body>
</html>
