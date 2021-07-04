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
            <th><fmt:message key="productTable.brand"/></th>
            <th><fmt:message key="productTable.model"/></th>
            <th><fmt:message key="productTable.category"/></th>
            <th><fmt:message key="productTable.characteristics"/></th>
            <th><fmt:message key="productTable.price"/></th>
            <th><fmt:message key="productTable.warranty"/></th>
            <th><fmt:message key="productTable.stockAmount"/></th>
            <th><fmt:message key="productTable.description"/></th>
            <th><fmt:message key="productTable.photos"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${currentItemsRange}" var="product" varStatus="loop">
            <!--<form action="controller" class="order${product.getId()}">-->
            <tr>
                <td><c:out value="${product.getId()}"/> </td>
                <td><c:out value="${product.getBrand()}"/> </td>
                <td><form action="controller" method="post">
                    <button type="submit" class="btn btn-sm btn-link" name="command" value="to_product">
                            ${product.getModel()}
                    </button>
                    <input type="hidden" name="productId" value="${product.getId()}">
                </form>
                </td>
                <td><c:out value="${product.getCategory().getName()}"/> </td>
                <td>
                    <c:forEach items="${product.getCharacteristics()}" var="characteristic">
                        <c:out value="${characteristic.getName()}"/>:
                            <c:out value="${characteristic.getValue()}"/><br/>
                    </c:forEach>
                </td>
                <td>
                    <c:choose>
                    <c:when test="${currentLocale.equals('ru_RU')}">
                        <fmt:formatNumber value="${product.price * conv}" currencyCode="BYN" currencySymbol="BYN" type="currency"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber value="${product.price * conv}" type="currency"/>
                    </c:otherwise>
                </c:choose>
                </td>
                <td><c:out value="${product.getWarranty()}"/> </td>
                <td><c:out value="${product.getStockAmount()}"/> </td>
                <td><c:out value="${product.getDescription()}"/> </td>
                <td>
                <c:forEach items="${product.getPhotos()}" var="photo">
                    <c:out value="${photo}"/>
                </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination">
        <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
            <li><form action="controller" method="post" class="my-auto">
                <c:choose>
                    <c:when test="${pageNumber == currentProductsPage}">
                        <button class="btn btn-primary mx-1" type="submit" disabled>
                                ${pageNumber}
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-primary mx-1" type="submit" name="nextItemPage" value="${pageNumber}">
                                ${pageNumber}
                        </button>
                        <input type="hidden" name="command" value="to_admin_products">
                    </c:otherwise>
                </c:choose>
            </form></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>