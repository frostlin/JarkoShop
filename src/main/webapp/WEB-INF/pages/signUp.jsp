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
    <link href="${pageContext.request.contextPath}/assets/style/signin.css" rel="stylesheet" type="text/css">

</head>
<jsp:include page="modules/header.jsp"/>

<body class="text-center">
<div class="container justify-content-center" style="width: 380px; margin-left: auto; margin-right: auto">
    <form class="form-signin" action="controller" method="post">
        <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="header.sign_up"/></h1>

        <input type="email" name = "email" id="inputEmail" class="form-control" placeholder="<fmt:message key="signup.emailPlaceholder"/>" required autofocus pattern="^[\w\.]{3,13}@\w{3,10}\.\w{2,5}$">
        <input type="text" name = "login" id="inputLogin" class="form-control" placeholder="<fmt:message key="signup.loginPlaceholder"/>" required autofocus pattern="^(?=.*[A-Za-z0-9]$)[A-Za-z][\w.-]{0,19}$">
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="signup.passwordPlaceholder"/>" required pattern="^[\w]{3,20}$">
        <input type="password" name="confirmedPassword" id="inputConfirmPassword" class="form-control" placeholder="<fmt:message key="signup.passwordRepeatPlaceholder"/>" required pattern="^[\w]{3,20}$">

        <c:if test="${errorSignUpMessageKey != null}">
            <label style="color: red"><fmt:message key="${errorSignUpMessageKey}"/></label>
        </c:if>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="sign_up">
            <fmt:message key="header.sign_up"/>
        </button>
    </form>
</div>
</body>
</html>
