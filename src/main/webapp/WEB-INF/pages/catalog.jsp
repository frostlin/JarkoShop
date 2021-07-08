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
                                <button class="dropdown-item" type="submit" name="filterMethod" value="avgRating">
                                    <fmt:message key="catalog.popularityFilter"/></button>
                                <button class="dropdown-item" type="submit" name="filterMethod" value="priceAsc">
                                    <fmt:message key="catalog.priceFilterAsc"/></button>
                                <button class="dropdown-item" type="submit" name="filterMethod" value="priceDesc">
                                    <fmt:message key="catalog.priceFilterDesc"/></button>
                                <input type="hidden" name="command" value="to_catalog">
                            </div>
                        </form>
                    </div>
                    <h3><fmt:message key="category.${currentItemsRange.get(0).getCategory().getName()}"/></h3>
                    <c:if test="${addProductMessageKey != null}">
                        <label class="mt-1" style="color: red"><fmt:message key="${addProductMessageKey}"/></label>
                    </c:if>
                    <form class="form-inline my-2 my-lg-0" action="controller" method="post">
                        <input class="form-control mr-sm-2" type="text" name="searchString" placeholder=<fmt:message key="header.search"/> aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="command" value="to_catalog">
                            <fmt:message key="header.search"/>
                        </button>
                    </form>
                </div>
                <ul class="list-group">
                    <c:forEach var="product" items="${currentItemsRange}" varStatus="status">
                        <li class="list-group-item list-group-item-action justify-content-start d-flex align-text-top">
                            <div class="justify-content-start" >
                               <c:if test="${!product.photos.isEmpty()}">
                                    <img class="align-self-center" src="${pageContext.request.contextPath}/assets/images/${product.id}/${product.getPhotos().get(0)}.jpeg" alt="img"  height="177" width="auto">
                                </c:if>
                            </div>

                            <div class="justify-content-end ml-3">
                                <div class="align-self-start">
                                    <form action="controller" method="post">
                                        <button type="submit" class="btn btn-lg btn-link " name="command" value="to_product">
                                            ${product.getBrand()} ${product.getModel()}
                                        </button>
                                        <p class="ml-2">
                                        <c:choose>
                                            <c:when test="${product.getRating() < 1}">
                                                <fmt:message key="catalog.noRating"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach begin="1" end="10" var="digit">
                                                    <c:choose>
                                                        <c:when test="${digit <= product.getRating()}">
                                                            <i class='bx bxs-star'></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${digit - 1 < product.getRating()}">
                                                                    <i class='bx bxs-star-half' ></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class='bx bx-star' ></i>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                        </p>
                                        <input type="hidden" name="productId" value="${product.getId()}">
                                    </form>

                                    <div class="ml-2">
                                        <c:forEach var="characteristic" items="${product.getCharacteristics()}">
                                            <c:if test="${characteristic.getValue() != null}">
                                                <c:out value="${characteristic.getValue()}, "/>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="align-self-end mt-4 ml-2">
                                    <form action="controller" method="post" class="my-auto">
                                        <button class="btn btn-outline-success " type="submit" name="command" value="add_to_cart">
                                            <i class='bx bxs-cart-download'></i>
                                            <c:choose>
                                                <c:when test="${currentLocale.equals('ru_RU')}">
                                                    <span class="badge badge-success ml-1"><fmt:formatNumber value="${product.price * conv}" currencyCode="BYN" currencySymbol="BYN" type="currency"/></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge badge-success ml-1"><fmt:formatNumber value="${product.price * conv}" type="currency"/></span>
                                                </c:otherwise>
                                            </c:choose>
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
                <custom:pagination totalPageCount="${totalPageCount}" currentPage="${currentProductsPage}" command="to_catalog"/>
            </div>
            <div class="col">

            </div>
        </div>
    </div>
</body>
</html>