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
    <jsp:include page="modules/header.jsp"/>
    <body>
    <div class="container">
        <div class="row">
            <div class="col">

            </div>
            <div class="col-6">
                <br/>
                <ul class="list-group">
                    <c:forEach var="product" items="${productPageList}" varStatus="status">
                        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                            <c:out value="${product.brand} ${product.model}"/>

                            <span class="badge badge-success badge-pill">$<c:out value="${product.price}"/></span>
                        </li>
                    </c:forEach>
                </ul>
                <br/>
                <ul class="pagination">
                    <c:forEach begin="1" end="${productPageCount}" var="pageNumber">
                        <li><form action="controller" method="post" class="my-auto">
                            <c:choose>
                                <c:when test="${pageNumber == currentProductPage}">
                                    <button class="btn btn-primary mx-1" type="submit" disabled>
                                            ${pageNumber}
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-outline-primary mx-1" type="submit" name="nextProductPage" value="${pageNumber}">
                                            ${pageNumber}
                                    </button>
                                    <input type="hidden" name="command" value="to_catalog">
                                </c:otherwise>
                            </c:choose>
                        </form></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col">
                3 of 3
            </div>
        </div>
    </div>
</body>
</html>