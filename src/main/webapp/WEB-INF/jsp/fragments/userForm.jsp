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
                    <label><spring:message key="user.id"/> </label>
                </div>
                <div class="col-md-6">
                    <p>${user.code}</p>
                </div>
            </div>
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
            <c:forEach items="${user.accounts}" var="accounts">
                <div class="row">
                    <div class="col-md-6">
                        <label><spring:message key="card.id"/></label>
                    </div>
                    <div class="col-md-6">
                        <p>${accounts.cardNumber}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label>Currency</label>
                    </div>
                    <div class="col-md-6">
                        <p>${accounts.currency.name}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label><spring:message key="money"/></label>
                    </div>
                    <div class="col-md-6">
                        <p>${accounts.money}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
