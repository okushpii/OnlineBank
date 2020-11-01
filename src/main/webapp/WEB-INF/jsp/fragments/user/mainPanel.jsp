<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col">
    <div class="profile-head-md">
        <h5>
            ${user.name}
        </h5>
        <h6>
            <spring:message key="profile"/>
        </h6>
        <div class="profile-img">
            <img width="300px" height="180px" src= <c:url value="/img/user-icon.png"/>>
            <br><br>
            <div class="file btn btn-lg btn-primary">
                <spring:message key="change.photo"/>
            </div>
        </div>
    </div>
</div>

<div class="col">
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
               aria-controls="home"
               aria-selected="true"><spring:message key="about"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
               aria-controls="profile" aria-selected="false"><spring:message key="accounts"/> </a>
        </li>
    </ul>
    <br>
    <div>
        <input type="submit" class="btn btn-secondary" name="btnAddMore" value=
                <spring:message key="edit.profile"/>/>
    </div>
    <br>
    <div class="tab-content profile-tab" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="name"/></label>
                </div>
                <div class="col-md-6">
                    <p>${user.name}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="email"/></label>
                </div>
                <div class="col-md-6">
                    <p>${user.email}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label><spring:message key="phone"/></label>
                </div>
                <div class="col-md-6">
                    <p>123 456 7890</p>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            <div id="accordion">
                <c:forEach items="${user.accounts}" var="accounts">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0">
                                <button class="btn btn-link" data-toggle="collapse" data-target="#${accounts.code}"
                                        aria-expanded="true" aria-controls="${accounts.code}">
                                        ${accounts.cardNumber} Balance ${accounts.money} ${accounts.currency.name}
                                </button>
                            </h5>
                        </div>

                        <div id="${accounts.code}" class="collapse" aria-labelledby="headingOne"
                             data-parent="#accordion">
                            <div class="card-body">
                                <c:forEach items="${accounts.operations}" var="operations">
                                <c:if test="${operations.type eq 'TRANSFER_OUTCOME'}">
                                <p style="color: red">Transfer: ${operations.sum} to
                                    card: ${operations.cardNumberTo}
                                    <br>
                                    </c:if>
                                    <c:if test="${operations.type eq 'TRANSFER_INCOME'}">
                                <p style="color:green">Refill: ${operations.sum} from
                                    card: ${operations.cardNumberTo}
                                    <br>
                                    </c:if>
                                    <c:if test="${operations.type eq 'EXCHANGE_OUTCOME'}">
                                <p style="color: red">Currency exchange: ${operations.sum} from
                                        ${operations.currencyFromName} to
                                        ${operations.currencyToName} to card: ${operations.cardNumberTo}
                                    <br>
                                    </c:if>
                                    <c:if test="${operations.type eq 'EXCHANGE_INCOME'}">
                                <p style="color:green">Currency exchange: ${operations.sum} from
                                        ${operations.currencyToName} to
                                        ${operations.currencyFromName} from card: ${operations.cardNumberTo}
                                    <br>
                                    </c:if>
                                    </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

</body>
</html>
