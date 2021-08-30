<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="customTags" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <title>User profile</title>
</head>
<jsp:include page="../modules/header.jsp"/>

<body>
<div class="container">
    <div class="row">
        <div class="col">
            <h4><fmt:message key="profile.username"/>${currentUser.getLogin()}</h4>
            <form action="controller" method="post">
                <button class="btn btn-outline-primary" type="submit" name="command" value="to_change_password">
                    <fmt:message key="profile.changePass"/>
                </button>
                <button class="btn btn-outline-primary" type="submit" name="command" value="logout">
                    <fmt:message key="profile.logout"/>
                </button>
                <button class="btn btn-outline-primary" type="submit" name="command" value="to_order_archive">
                    <fmt:message key="profile.archive"/>
                </button>
            </form>
        </div>
    </div>
    <fmt:message key="profile.activeOrders"/>

    <table class="table mt-2">
        <thead>
        <tr>
            <th><fmt:message key="orderTable.id"/></th>
            <th><fmt:message key="orderTable.status"/></th>
            <th><fmt:message key="orderTable.products"/></th>
            <th><fmt:message key="orderTable.address"/></th>
            <th><fmt:message key="orderTable.sumToPay"/></th>
            <th><fmt:message key="orderTable.dateOrdered"/></th>
            <th><fmt:message key="orderTable.shippingDate"/></th>
            <th><fmt:message key="orderTable.comment"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${currentItemsRange}" var="order" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>
                        ${order.getStatus()}
                </td>
                <td>
                    <div class="list-group">
                        <c:forEach var="cartItem" items="${order.getProducts()}" varStatus="status">
                            <a href="controller?command=to_product&productId=${cartItem.getProduct().getId()}"
                               class="list-group-item list-group-item-action" aria-current="true">
                                    ${cartItem.getProduct().getBrand()}  ${cartItem.getProduct().getModel()}
                            </a>
                        </c:forEach>
                    </div>
                </td>
                <td><c:out value="${order.getAddress().toString()}"/> </td>
                <td>
                    <c:choose>
                        <c:when test="${currentLocale.equals('ru_RU')}">
                            <fmt:formatNumber value="${order.getSumToPay() * conv}" currencyCode="BYN" currencySymbol="BYN" type="currency"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${order.getSumToPay() * conv}" type="currency"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><c:out value="${order.getDateOrdered()}"/> </td>
                <td><c:out value="${order.getDateShipping()}"/> </td>
                <td><c:out value="${order.getComment()}"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <custom:pagination totalPageCount="${totalPageCount}" currentPage="${currentProfileOrdersPage}" command="to_profile"/>

</div>

</body>
</html>
