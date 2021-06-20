<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
    <a class="btn navbar-brand" style="color: #007bff;" href="controller?command=to_main">
        <fmt:message key="header.brand"/>
    </a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <form action="controller" method="post" class="my-auto">
                    <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_catalog">
                        <fmt:message key="header.catalog"/>
                    </button>
                </form>
            </li>
        </ul>
        <div class="nav-item dropdown mt-3" style="vertical-align:middle">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="header.language"/>
            </a>
            <form action="controller" method="post">
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <button class="dropdown-item" type="submit" name="changeLocale" value="en_US">
                        <fmt:message key="header.language_en"/></button>
                    <button class="dropdown-item" type="submit" name="changeLocale" value="ru_RU">
                        <fmt:message key="header.language_ru"/></button>
                    <button class="dropdown-item" type="submit" name="changeLocale" value="ja_JP">
                        <fmt:message key="header.language_jp"/></button>
                    <input type="hidden" name="command" value="change_locale">
                </div>
            </form>
        </div>
        <c:choose>
            <c:when test="${role.equals('guest')}">
                <div class="d-flex flex-row justify-content-end align-items-center pr-0">
                    <form action="controller" method="post" class="my-auto">
                        <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_sign_in">
                            <fmt:message key="header.sign_in"/>
                        </button>
                    </form>
                    <form action="controller" method="post" class="my-auto">
                        <button class="btn btn-primary mx-1" type="submit" name="command" value="to_sign_up">
                            <fmt:message key="header.sign_up"/>
                        </button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_cart">
                    <fmt:message key="header.cart"/>
                    <span class="badge badge-danger badge-pill mr-2"><c:out value="${currentUser.getCart().size()}"/></span>
                </button>
                <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_profile">
                    <fmt:message key="header.profile"/>
                </button>
                <c:if test="${role.equals('admin')}">
                    <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_dashboard">
                        <fmt:message key="header.dashboard"/>
                    </button>
                </c:if>
            </c:otherwise>
        </c:choose>

    </div>
    </div>
</nav>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
