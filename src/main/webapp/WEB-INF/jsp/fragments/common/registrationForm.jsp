<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="col-md-12 text-center">
                    <h3><spring:message key="registration"/></h3>
                </div>

                <div class="card-body">
                    <form:form method="post" action="/registration" modelAttribute="registerData">
                        <div class="form-group">
                            <label class="cols-sm-2 control-label"><spring:message key="your.full.name"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                                    <spring:message key="placeholder.your.full.name" var="fullNamePlaceholder"/>
                                    <form:input type="text" class="form-control" path="name"
                                                placeholder="${fullNamePlaceholder}"/>
                                </div>
                            </div>
                            <div style="color: red"><form:errors path="name" cssStyle="font-size: 15px"
                                                                 cssClass="error" element="em"/></div>
                        </div>
                        <div class="form-group">
                            <label class="cols-sm-2 control-label"><spring:message key="your.email"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope fa"
                                                                   aria-hidden="true"></i></span>
                                    <spring:message key="placeholder.your.email" var="emailPlaceholder"/>
                                    <form:input type="text" class="form-control" path="email"
                                                placeholder="${emailPlaceholder}"/>
                                </div>
                            </div>
                            <div style="color: red"><form:errors path="email" cssStyle="font-size: 15px"
                                                                 cssClass="error" element="em"/></div>
                        </div>
                        <div class="form-group">
                            <label class="cols-sm-2 control-label"><spring:message key="username"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-users fa"
                                                                       aria-hidden="true"></i></span>
                                    <spring:message key="placeholder.your.username" var="usernamePlaceholder"/>
                                    <form:input type="text" class="form-control" path="username"
                                                placeholder="${usernamePlaceholder}"/>
                                </div>
                            </div>
                            <div style="color: red"><form:errors path="username" cssStyle="font-size: 15px"
                                                                 cssClass="error" element="em"/></div>
                        </div>
                        <div class="form-group">
                            <label class="cols-sm-2 control-label"><spring:message key="password"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <spring:message key="placeholder.your.password" var="passwordPlaceholder"/>
                                    <form:input type="password" class="form-control" path="password"
                                                placeholder="${passwordPlaceholder}"/>
                                </div>
                            </div>
                            <div style="color: red"><form:errors path="password" cssStyle="font-size: 15px"
                                                                 cssClass="error" element="em"/></div>
                        </div>
                        <div class="form-group">
                            <label class="cols-sm-2 control-label"><spring:message key="confirm.password"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <spring:message key="placeholder.confirm.your.password" var="confirmaPasswordPlaceholder"/>
                                    <form:input type="password" class="form-control" path="passwordConfirmation"
                                                placeholder="${confirmaPasswordPlaceholder}"/>
                                </div>
                            </div>
                            <div style="color: red"><form:errors path="passwordConfirmation" cssStyle="font-size: 15px"
                                                                 cssClass="error" element="em"/></div>
                        </div>
                        <div class="form-group ">
                            <button type="submit" class="btn btn-primary btn-lg btn-block login-button"><spring:message
                                    key="register"/>
                            </button>
                        </div>
                        <div class="form-group">
                            <p class="text-center"><spring:message key="you.have.account"/><a href="/login"><spring:message
                                    key="log.in.here"/></a></p>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
