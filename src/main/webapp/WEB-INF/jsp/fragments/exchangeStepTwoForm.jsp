<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="col">
    <div class="form-group">
        <label class="control-label"></label>
        <div class="controls">
            <select class="form-control" name="accountFromCode">
                <c:forEach var="account" items="${accounts}">
                    <option value="${account.code}">${account.cardNumber}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label"></label>
        <div class="controls">
            <select class="form-control" name="accountToCode">
                <c:forEach var="account" items="${accounts}">
                    <option value="${account.code}">${account.cardNumber}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label"></label>

        <div class="controls">
            <input type="number" placeholder="Sum"
                   name="sum" class="form-control" required>
        </div>
    </div>

    <br>
    <div class="row">
        <div class="col-sm-3">
            <div class="form-group">
                <a href="/"><button class="btn btn-success">Threes step</button></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
