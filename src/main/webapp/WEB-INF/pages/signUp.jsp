<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <title><fmt:message key="header.sign_up"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="modules/header.jsp"/>

<div class="container justify-content-center" style="width: 380px; margin-left: auto; margin-right: auto">
    <form action="controller" method="post">
        <br/>
        <br/>

        <div class="form-group">
            <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                   placeholder="<fmt:message key="signup.emailPlaceholder"/>" required pattern="^[\w\.]{3,13}@\w{3,10}\.\w{2,5}$">
        </div>
        <div class="form-group">
            <input type="text" name="login" class="form-control" id="inputLogin" aria-describedby="loginHelp"
                   placeholder="<fmt:message key="signup.loginPlaceholder"/>">
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" id="inputPassword"
                   placeholder="<fmt:message key="signup.passwordPlaceholder"/>" required pattern="^[\w]{3,20}$">
        </div>
        <div class="form-group">
            <input type="password" name="confirmedPassword" class="form-control" id="inputConfirmPassword"
                   placeholder="<fmt:message key="signup.passwordRepeatPlaceholder"/>" required pattern="^[\w]{3,20}$">
        </div>
        <c:if test="${errorSignUpMessageKey != null}">
            <label style="color: red"><fmt:message key="${errorSignUpMessageKey}"/></label>
        </c:if>
        <button type="submit" name="command" value="sign_up" class="btn btn-primary">
            <fmt:message key="header.sign_up"/>
        </button>
    </form>
</div>

</body>
</html>
