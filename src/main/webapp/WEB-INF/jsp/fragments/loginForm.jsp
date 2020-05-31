<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>

<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div id="first">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1>Login</h1>
                        </div>
                    </div>

                    <form:form method="post" action="/auth" modelAttribute="authRequest">
                        <div class="form-group">
                            <label>Username</label>
                            <form:input type="text" name="username" class="form-control"
                                        placeholder="Enter username" path="username"/>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <form:input type="password" name="password" class="form-control"
                                        placeholder="Enter Password" path="password"/>
                        </div>
                        <div class="form-group">
                            <p class="text-center">By signing up you accept our <a href="#">Terms Of Use</a></p>
                        </div>
                        <div class="col-md-12 text-center ">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
                        </div>
                        <div class="form-group">
                            <p class="text-center">Don't have account? <a href="#" id="signup">Sign up here</a></p>
                        </div>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
