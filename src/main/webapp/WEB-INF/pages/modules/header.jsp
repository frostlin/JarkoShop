<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="../../../assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <form action="controller" method="post" class="my-auto">
        <a class="btn navbar-brand" style="color: #007bff;" href="controller?command=to_main">
            <fmt:message key="header.brand"/>
        </a>
    </form>
    <div class="d-flex justify-content-start dropdown">
        <a class="nav-link dropdown-toggle language-choice" href="#" id="navbarDropdown" role="button"
           data-toggle="dropdown"
           aria-haspopup="true" aria-expanded="false">
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
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder=<fmt:message key="header.search"/> aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                    <fmt:message key="header.search"/></button>
            </form>
        </div>

    </div>

</nav>
<script type="text/javascript" src="../../../assets/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="../../../assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
