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
    <c:if test="${errorCommitReview != null}">
    <label style="color: red"><fmt:message key="${errorCommitReview}"/></label>
    </c:if>
    <c:if test="${addProductMessageKey != null}">
    <label style="color: red"><fmt:message key="${addProductMessageKey}"/></label>
    </c:if>
    <h1><fmt:message key="product.${currentProduct.getCategory().getName()}"/> ${currentProduct.getBrand()} ${currentProduct.getModel()}</h1>
<br/>
    <div class="row">
        <div class="col"></div>
        <div class="col"></div>
        <div class="col-8">
            <div>
                <div id="demo" class="carousel slide" data-ride="carousel" style="width: 400px; height: 400px; background-color: #EFEFEF">
                    <ol class="carousel-indicators" style="filter: invert(1);">
                        <c:forEach items="${currentProduct.getPhotos()}" var="photo" varStatus="loop">
                            <c:choose>
                                <c:when test="${loop.index == 0}">
                                    <li data-target="#demo" data-slide-to="${loop.index}" class="active"></li>
                                </c:when>
                                <c:otherwise>
                                    <li data-target="#demo" data-slide-to="${loop.index}"></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ol>

                    <div class="carousel-inner" style=" background-color: #EFEFEF">
                        <c:forEach items="${currentProduct.getPhotos()}" var="photo" varStatus="loop">
                            <c:choose>
                                <c:when test="${loop.index == 0}">
                                    <div class="carousel-item active " style="width: 400px; height: 400px">
                                        <img class="d-block w-100" src="${pageContext.request.contextPath}/assets/images/${currentProduct.id}/${photo}.jpeg" alt="" style="object-fit: contain; height: 100%">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="carousel-item" style="width: 400px; height: 400px">
                                        <img class="d-block w-100" src="${pageContext.request.contextPath}/assets/images/${currentProduct.id}/${photo}.jpeg" alt="" style="object-fit: contain; height: 100%">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>

                    <a class="carousel-control-prev" href="#demo" data-slide="prev">
                        <span class="carousel-control-prev-icon" style="filter: invert(1);"></span>
                    </a>
                    <a class="carousel-control-next" href="#demo" data-slide="next">
                        <span class="carousel-control-next-icon" style="filter: invert(1);"></span>
                    </a>
                </div>
            </div><br/>
            <form action="controller" method="post" class="my-auto">
                <button class="btn btn-outline-success " type="submit" name="command" value="add_to_cart">
                    <i class='bx bxs-cart-download'></i>
                    <c:choose>
                        <c:when test="${currentLocale.equals('ru_RU')}">
                            <span class="badge badge-success ml-1"><fmt:formatNumber value="${currentProduct.price * conv}" currencyCode="BYN" currencySymbol="BYN" type="currency"/></span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-success ml-1"><fmt:formatNumber value="${currentProduct.price * conv}" type="currency"/></span>
                        </c:otherwise>
                    </c:choose>
                </button>
                <input type="hidden" name="productId" value="${currentProduct.getId()}">
            </form>
        </div>


        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="reviewTable.characteristic"/> </th>
                <th scope="col"><fmt:message key="reviewTable.value"/> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${currentProduct.getCategory().getCharacteristics()}" var="characteristic" varStatus="loop">
                <tr>
                    <th>
                        <c:if test="${role.equals('admin')}">
                            <div class="d-flex flex-row justify-content-between">
                                <form action="controller" method="post" class="align-content-end">
                                    <h5><c:out value="${characteristic.getName()}"/></h5>
                                    <input type="text" name="characteristicName" class="form-control" placeholder="<fmt:message key="reviewTable.characteristic"/>" autofocus>

                                    <c:out value="${characteristic.getDescription()}"/>
                                    <input type="text" name="characteristicDesc" class="form-control" placeholder="<fmt:message key="productTable.description"/>" autofocus>

                                    <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="edit_characteristic">
                                        <fmt:message key="productTable.editCharacteristic"/>
                                    </button>
                                    <input type="hidden" name="characteristicId" value="${characteristic.getId()}">
                                    <input type="hidden" name="productId" value="${currentProduct.getId()}">

                                </form>
                            </div>
                        </c:if>
                        <c:if test="${!role.equals('admin')}">
                            <h5><c:out value="${characteristic.getName()}"/></h5>
                            <c:out value="${characteristic.getDescription()}"/>
                        </c:if>
                    </th>
                    <td>
                        <div class="d-flex flex-row justify-content-between ">
                        <c:choose>
                            <c:when test="${loop.index < currentProduct.getCharacteristics().size() && currentProduct.getCharacteristics().get(loop.index).getValue() != null}">
                                <c:out value="${currentProduct.getCharacteristics().get(loop.index).getValue()}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="reviewTable.noInfo"/>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${role.equals('admin')}"> <br/>
                            <form action="controller" method="post" class="align-content-end">
                                <input type="text" name="characteristicValue" id="inputCharacteristic" class="form-control" placeholder="<fmt:message key="reviewTable.characteristic"/>" autofocus>
                                <input type="hidden" name="characteristicId" value="${characteristic.getId()}">
                                <input type="hidden" name="productId" value="${currentProduct.getId()}">

                                <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="edit_product_characteristic">
                                    <fmt:message key="productTable.editCharacteristic"/>
                                </button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="d-flex flex-column">
            <c:if test="${role.equals('admin')}">
                <br/>
                <h5>Добавить новую характеристику</h5>
                    <form action="controller" method="post" class="my-auto">
                        <div>
                            <input type="text" name="characteristicName" id="inputCharacteristicName" class="form-control" placeholder="<fmt:message key="reviewTable.characteristic"/>" autofocus>
                            <textarea id="charDesc" name="characteristicDesc" rows="4" cols="50" placeholder=<fmt:message key="productTable.description"/>></textarea>
                        </div>
                        <div>
                            <input type="hidden" name="categoryId" value="${currentProduct.getCategory().getId()}">
                            <input type="hidden" name="productId" value="${currentProduct.getId()}">
                            <button class="btn btn-outline-primary mx-1 mb-5" type="submit" name="command" value="add_new_characteristic">
                                <fmt:message key="productTable.addNewCharacteristic"/> <fmt:message key="product.${currentProduct.getCategory().getName()}"/>
                            </button>
                        </div>
                    </form>
            </c:if>
            <br/>


            <h5><fmt:message key="reviewTable.leaveReview"/></h5>
            <form action="controller" method="post">
                <c:choose>
                    <c:when test="${role.equals('guest')}">
                         <textarea id="review" name="review" rows="4" cols="50" disabled><fmt:message key="review.notSignedIn"/></textarea>
                    </c:when>
                    <c:otherwise>
                        <textarea id="review" name="content" rows="4" cols="50"></textarea>
                        <select class="form-control" name="rating">
                            <c:forEach begin="1" end="10" var="digit">
                                <option  value="${digit}">${digit}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-outline-primary mx-1 mb-5" type="submit" name="command" value="commit_review">
                            <fmt:message key="review.commit"/>
                        </button>
                    </c:otherwise>
                </c:choose>
                <br>
            </form>
        </div>
    </div>

    </div>
    <table class="table table-bordered">
        <thead>
        <tr><th scope="col"><fmt:message key="reviewTable.review"/> </th>`</tr>
        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="review" varStatus="loop">
            <tr>
                <td>
                    <div class="flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1"><c:out value="${review.getUser().getLogin()}"/></h5>

                            <small><c:out value="${review.getDate()}"/></small>
                        </div>
                        <c:forEach begin="1" end="10" var="digit">
                            <c:choose>
                                <c:when test="${digit <= review.getRating()}">
                                    <i class='bx bxs-star'></i>
                                </c:when>
                                <c:otherwise>
                                    <i class='bx bx-star' ></i>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <p class="my-3"><c:out value="${review.getContent()}"/></p>

                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>