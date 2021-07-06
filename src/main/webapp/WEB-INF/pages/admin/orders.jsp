<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>catalog</title>
</head>
<jsp:include page="../modules/header.jsp"/>
<body>
<div class="container">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th><fmt:message key="orderTable.id"/> </th>
                <th><fmt:message key="orderTable.status"/></th>
                <th><fmt:message key="orderTable.user"/></th>
                <th><fmt:message key="orderTable.address"/></th>
                <th><fmt:message key="orderTable.sumToPay"/></th>
                <th><fmt:message key="orderTable.dateOrdered"/></th>
                <th><fmt:message key="orderTable.shippingDate"/></th>
                <th><fmt:message key="orderTable.comment"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${currentItemsRange}" var="order" varStatus="loop">
                <!--<form action="controller" class="order${order.getId()}">-->
                <tr>
                    <td><c:out value="${order.getId()}"/> </td>
                    <td><c:out value="${order.getStatus()}"/> </td>
                    <td><c:out value="${order.getUser().getFio()}"/> </td>
                    <td><c:out value="${order.getAddress().toString()}"/> </td>
                    <td><c:out value="${order.getSumToPay()}"/> </td>
                    <td><c:out value="${order.getDateOrdered()}"/> </td>
                    <td><c:out value="${order.getDateShipping()}"/> </td>
                    <td><c:out value="${order.getComment()}"/> </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <ul class="pagination">
        <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
            <li><form action="controller" method="post" class="my-auto">
                <c:choose>
                    <c:when test="${pageNumber == currentOrdersPage}">
                        <button class="btn btn-primary mx-1" type="submit" disabled>
                                ${pageNumber}
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-primary mx-1" type="submit" name="nextItemPage" value="${pageNumber}">
                                ${pageNumber}
                        </button>
                        <input type="hidden" name="command" value="to_admin_orders">
                    </c:otherwise>
                </c:choose>
            </form></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>