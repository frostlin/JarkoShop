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
            <div class="col-9">
                <div class="d-flex flex-row justify-content-between align-items-center pr-0 m">
                    <div class="nav-item dropdown mt-3">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="catalog.filters"/>
                        </a>
                        <form action="controller" method="post">
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <button class="dropdown-item" type="submit" name="changeLocale" value="en_US">
                                    <fmt:message key="catalog.dateFilter"/></button>
                                <button class="dropdown-item" type="submit" name="changeLocale" value="en_US">
                                    <fmt:message key="catalog.priceFilter"/></button>
                                <input type="hidden" name="command" value="change_locale">
                            </div>
                        </form>
                    </div>
                    <c:if test="${addProductMessageKey != null}">
                        <label class="mt-1" style="color: red"><fmt:message key="${addProductMessageKey}"/></label>
                    </c:if>
                    <form class="form-inline my-2 my-lg-0" action="controller" method="post">
                        <input class="form-control mr-sm-2" type="text" name="searchString" placeholder=<fmt:message key="header.search"/> aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="command" value="search">
                            <fmt:message key="header.search"/>
                        </button>
                    </form>
                </div>
                <ul class="list-group">
                    <c:forEach var="product" items="${productPageList}" varStatus="status">
                        <li class="list-group-item list-group-item-action justify-content-start d-flex align-text-top">
                            <div class="justify-content-center" style="width:150px;height:144px" >
                               <c:if test="${!product.photos.isEmpty()}">
                                    <img class="align-self-center" src="${pageContext.request.contextPath}/assets/images/${product.id}/${product.getPhotos().get(0)}.jpeg" alt="img"  height="144">
                                </c:if>
                            </div>

                            <div class="justify-content-end">
                                <div class="align-self-start">
                                    <h5><c:out value="${product.brand} ${product.model}"/></h5>
                                    <c:forEach var="characteristic" items="${product.getCharacteristics()}">
                                        <c:out value="${characteristic.getName()} ${characteristic.getValue()}, "/>
                                    </c:forEach>
                                </div>
                                <div class="align-self-end mt-4">
                                    <span class="badge badge-success badge-pill mr-2 mb-2">$<c:out value="${product.price}"/></span>
                                    <form action="controller" method="post" class="my-auto">
                                    <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="add_to_cart">
                                        <fmt:message key="catalog.add_to_card"/>
                                    </button>
                                    <input type="hidden" name="productId" value="${product.getId()}">
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                    <c:if test="${addProductMessageKey != null}">
                        <label style="color: red"><fmt:message key="${addProductMessageKey}"/></label>
                    </c:if>
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

            </div>
        </div>
    </div>
</body>
</html>