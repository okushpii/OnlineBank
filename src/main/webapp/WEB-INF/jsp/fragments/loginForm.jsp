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
                            <h1><spring:message key="login"/></h1>
                        </div>
                    </div>

                    <form:form method="post" action="/auth" modelAttribute="authRequest">
                        <div class="form-group">
                            <label><spring:message key="username"/></label>
                            <input type="text" name="username" class="form-control"
                                        placeholder = <spring:message key="enter.user.name"/> path="username"/>
                        </div>
                        <div class="form-group">
                            <label><spring:message key="password"/></label>
                            <input type="password" name="password" class="form-control"
                                        placeholder = <spring:message key="enter.password"/> path="password"/>
                        </div>
                        <div class="form-group">
                            <p class="text-center"><spring:message key="accept.of"/> <a href="#"><spring:message key="terms.of.use"/></a></p>
                        </div>
                        <div class="col-md-12 text-center ">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm"><spring:message key="login"/></button>
                        </div>
                        <div class="form-group">
                            <p class="text-center"><spring:message key="have.not.account"/> <a href="/registration"><spring:message key="sing.up"/></a></p>
                        </div>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                            <spring:message key="user.error.message"/>
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
