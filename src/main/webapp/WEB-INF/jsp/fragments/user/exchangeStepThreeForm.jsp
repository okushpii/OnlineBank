<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col">
    <form:form method="post" action="/account/exchange">
        <div class="alert alert-info" role="alert">
            <p><spring:message key="exchange.from"/></p>
        </div>
        <input type="hidden" name="accountFromCode" value=${exchange.accountFrom.code}>
        <input type="hidden" name="accountToCode" value=${exchange.accountTo.code}>
        <input type="hidden" name="sum" value=${exchange.sum}>
        <div class="form-group">
            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="currency"/></label>
                </div>
                <div class="col-md-6">
                    <p>${exchange.accountFrom.currency.name}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="card.number"/></label>
                </div>
                <div class="col-md-6">
                    <p>${exchange.accountFrom.cardNumber}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="balance"/></label>
                </div>
                <div class="col-md-6">
                    <p>${exchange.balanceFrom} - ${exchange.sum} = ${exchange.balanceAfterFrom}</p>
                </div>
            </div>

            <div class="alert alert-info" role="alert">
                <p><spring:message key="exchange.to"/></p>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="currency"/></label>
                </div>
                <div class="col-md-6">
                    <p>${exchange.accountTo.currency.name}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="card.number"/></label>
                </div>
                <div class="col-md-6">
                    <p>${exchange.accountTo.cardNumber}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="balance"/></label>
                </div>
                <div class="col-md-6">
                    <p>${exchange.balanceTo} + ${exchange.sumAfter} = ${exchange.balanceAfterTo}</p>
                </div>
            </div>

            <br>
            <div class="row">
                <div class="col-sm-3">
                    <div class="form-group">
                        <button class="btn btn-success"><spring:message key="confirm.exchange"/></button>
                    </div>
                </div>
            </div>

        </div>
    </form:form>
</div>
</body>
</html>
