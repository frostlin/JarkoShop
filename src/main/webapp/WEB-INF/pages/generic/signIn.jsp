<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <title><fmt:message key="header.sign_in"/> </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<jsp:include page="../modules/header.jsp"/>

<div class="container justify-content-center" style="width: 380px; margin-left: auto; margin-right: auto">
    <form action="controller" method="post">
        <br/>
        <br/>
        <div class="form-group">
            <input type="text" name="login" class="form-control" id="inputLogin" aria-describedby="loginHelp"
                   placeholder="<fmt:message key="signup.loginPlaceholder"/>" required pattern="^(?=.*[A-Za-z0-9]$)[A-Za-z][\w.-]{0,19}$">
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" id="inputPassword"
                   placeholder="<fmt:message key="signup.passwordPlaceholder"/>" required pattern="^[\w]{3,20}$">
        </div>
        <label style="color: red">${errorSignInMessage}</label>
        <br/>
        <button type="submit" name="command" value="sign_in" class="btn btn-primary">
            <fmt:message key="signup.signin"/>
        </button>
    </form>
</div>
</body>
</html>
