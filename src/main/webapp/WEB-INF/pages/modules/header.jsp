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
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
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
                    <div class="btn-group">
                        <button class="btn btn-primary mx-1" type="submit" name="currentCategory" value="0">
                            <fmt:message key="header.catalog"/>
                        </button>
                        <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu">
                            <c:forEach var="category" items="${categories}">
                                <button class="dropdown-item" type="submit" name="currentCategory" value="${category.getId()}">
                                    <fmt:message key="category.${category.getName()}"/></button>
                            </c:forEach>
                        </div>
                        <input type="hidden" name="command" value="to_catalog">
                    </div>

                </form>
            </li>
        </ul>
        <div class="btn-group">
            <button class="btn btn-link dropdown-toggle" type="button" id="currencyDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class='bx bxs-dollar-circle'></i>
            </button>
            <form action="controller" method="post">
                <div class="dropdown-menu" aria-labelledby="currencyDropdown">
                    <button class="dropdown-item" type="submit" name="changeCurrency" value="usd">
                        USD</button>
                    <button class="dropdown-item" type="submit" name="changeCurrency" value="byn">
                        BYN</button>
                    <button class="dropdown-item" type="submit" name="changeCurrency" value="rub">
                        RUB</button>
                    <input type="hidden" name="command" value="change_currency">
                </div>
            </form>
        </div>
        <div class="btn-group">
            <button class="btn btn-link dropdown-toggle" type="button" id="localeDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class='bx bx-world'></i>
            </button>
            <form action="controller" method="post">
                <div class="dropdown-menu" aria-labelledby="localeDropdown">
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
            <form action="controller" method="post" class="my-auto">
                <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_cart">
                    <i class='bx bxs-cart' ></i> <fmt:message key="header.cart"/>
                    <span class="badge badge-danger"><c:out value="${currentUser.getCart().size()}"/></span>
                </button>
                <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_profile">
                    <i class='bx bxs-id-card' > </i> <fmt:message key="header.profile"/>
                </button>
                <c:if test="${role.equals('admin')}">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="header.controlPanel"/>
                        </button>
                        <form action="controller" method="post">
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownAdmin">
                                <button class="dropdown-item" type="submit" name="command" value="to_admin_orders">
                                    <fmt:message key="admin.orders"/></button>
                                <button class="dropdown-item" type="submit" name="command" value="to_admin_users">
                                    <fmt:message key="admin.users"/></button>
                                <button class="dropdown-item" type="submit" name="command" value="to_admin_products">
                                    <fmt:message key="admin.products"/></button>
                                <button class="dropdown-item" type="submit" name="command" value="to_admin_discounts">
                                    <fmt:message key="admin.promocodesAndDiscounts"/></button>
                            </div>
                        </form>
                    </div>
                </c:if>
            </form>
            </c:otherwise>
        </c:choose>

    </div>
    </div>
</nav>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
