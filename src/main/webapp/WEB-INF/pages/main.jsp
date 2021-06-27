<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>main</title>
</head>
<body>
    <jsp:include page="modules/header.jsp"/>
    <div class="container">
    <div class="row">
        <div class="col">
            <h1><fmt:message key="main.greeting"/></h1>
        </div>
        <div class="col-6">
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
        </div>
    </div>
    </div>


    <c:choose>
        <c:when test="${role.equals('admin')}">
            admin
        </c:when>
        <c:when test="${role.equals('user')}">
            user
        </c:when>
    </c:choose>


</body>
</html>