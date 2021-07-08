<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="customTags"%>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>catalog</title>
</head>
<jsp:include page="../modules/header.jsp"/>
<body>
    <div class="container mb-3">
        <form action="controller" method="post">
            <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_admin_orders">
                <fmt:message key="orderTable.saveChanges"/>
            </button>
            <input type="hidden" name="saveFlag" value="true">
        </form>
    </div>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th><fmt:message key="orderTable.id"/> </th>
                <th><fmt:message key="orderTable.status"/></th>
                <th><fmt:message key="orderTable.user"/></th>
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
                    <td><c:out value="${order.getId()}"/> </td>
                    <td>
                        <form action="controller" class="order${order.getId()}">
                        <c:out value="${order.getStatus()}"/>
                        <select class="form-control" name="newStatus" form="order${order.getId()}">
                            <option  value="new"><fmt:message key="orderStatus.new"/></option>
                            <option  value="confirmed"><fmt:message key="orderStatus.confirmed"/></option>
                            <option  value="awaitingShipment"><fmt:message key="orderStatus.awaitingShipment"/></option>
                            <option  value="shipped"><fmt:message key="orderStatus.shipped"/></option>
                            <option  value="successful"><fmt:message key="orderStatus.successful"/></option>
                            <option  value="canceled"><fmt:message key="orderStatus.canceled"/></option>
                            <option  value="refunded"><fmt:message key="orderStatus.refunded"/></option>
                        </select>
                        </form>

                    </td>
                    <td>${order.getUser().getFio()}, ${order.getUser().getTelephone()} </td>
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
    <custom:pagination totalPageCount="${totalPageCount}" currentPage="${currentAdminOrdersPage}" command="to_admin_orders"/>
</body>
</html>