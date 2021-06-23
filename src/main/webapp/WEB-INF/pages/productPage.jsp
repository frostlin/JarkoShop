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
                    <ol class="carousel-indicators">
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
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#demo" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>
            </div><br/>
            <form action="controller" method="post" class="my-auto">
                <button class="btn btn-primary mx-1 mb-5" type="submit" name="command" value="add_to_cart">
                    <fmt:message key="catalog.addToCard"/>
                </button>
                <input type="hidden" name="productId" value="${currentProduct.getId()}">
            </form>
        </div>


        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="reviewTable.characteristic"/> </th>
                <th scope="col"> value</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${currentProduct.getCharacteristics()}" var="characteristic" varStatus="loop">
                <tr>
                    <th>
                        <h5><c:out value="${characteristic.getName()}"/></h5>
                        <c:out value="${characteristic.getDescription()}"/>
                    </th>
                    <td>
                        <c:choose>
                            <c:when test="${characteristic.getValue() != null}">
                                <c:out value="${characteristic.getValue()}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="reviewTable.noInfo"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <br/><br/><br/><br/><br/>
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
            <br><br>
        </form>
    </div>
        <table class="table table-bordered">
            <thead>
            <tr><th scope="col"><fmt:message key="reviewTable.review"/> </th>`</tr>
            </thead>
            <tbody>
            <c:forEach items="${reviews}" var="review" varStatus="loop">
                <tr>
                    <td>
                        <h5><c:out value="${review.getUser().getLogin()}"/></h5>
                        <p class="my-3"><c:out value="${review.getContent()}"/></p>
                        <p style="font-size: smaller"><fmt:message key="review.rating"/> <c:out value="${review.getRating()}"/> / 10 <br/>
                            <c:out value="${review.getDate()}"/></p>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</body>
</html>