<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>

<div class="container">
    <div class="row">
        <div class="col">
            <div class="profile-head-md">
                <h5>
                    ${user.name}
                </h5>
                <h6>
                    User profile
                </h6>
                <div class="profile-img">
                    <img width="300px" height="180px" src= <c:url value="/img/user-icon.png"/>>
                    <br><br>
                    <div class="file btn btn-lg btn-primary">
                        Change Photo
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                       aria-controls="home"
                       aria-selected="true">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                       aria-controls="profile" aria-selected="false">Accounts</a>
                </li>
            </ul>
            <br>
            <div>
                <input type="submit" class="btn btn-secondary" name="btnAddMore" value="Edit Profile"/>
            </div>
            <br>
            <div class="tab-content profile-tab" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div class="row">
                        <div class="col-md-6">
                            <label>User Id</label>
                        </div>
                        <div class="col-md-6">
                            <p>${user.code}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Name</label>
                        </div>
                        <div class="col-md-6">
                            <p>${user.name}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Email</label>
                        </div>
                        <div class="col-md-6">
                            <p>${user.email}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Phone</label>
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
                            <label>Account id:</label>
                        </div>
                        <div class="col-md-6">
                            <p>${accounts.cardNumber}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Money</label>
                        </div>
                        <div class="col-md-6">
                            <p>${accounts.money}</p>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
