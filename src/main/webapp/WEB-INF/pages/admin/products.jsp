<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="customTags" %>

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
            <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_add_new_product">
                <fmt:message key="productTable.addNewProduct"/>
            </button>
        </form>
    </div>
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
                    <c:if test="${currentLocale.equals('ru_RU')}">
                        <fmt:formatNumber value="${product.price * conv}" currencyCode="BYN" currencySymbol="BYN" type="currency"/>
                    </c:if>
                    <c:if test="${currentLocale.!equals('ru_RU')}">
                        <fmt:formatNumber value="${product.price * conv}" type="currency"/>
                    </c:if>
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
    <custom:pagination totalPageCount="${totalPageCount}" currentPage="${currentProductsPage}"/>
</body>
</html>