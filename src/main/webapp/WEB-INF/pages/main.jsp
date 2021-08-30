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
            <fmt:message key="main.latestProduct"/>
            <div class="card" style="width: 18rem;">
                <c:if test="${!latestProduct.getPhotos().isEmpty()}">
                    <img src="${pageContext.request.contextPath}/assets/images/${latestProduct.getId()}/${latestProduct.getPhotos().get(0)}.jpeg"  alt="..." width="130px">
                </c:if>
                <div class="card-body">
                    <h5 class="card-title"><fmt:message key="product.${latestProduct.getCategory().getName()}"/></h5>
                    <p class="card-text">${latestProduct.getBrand()} ${latestProduct.getModel()}</p>
                    <p class="card-text"><c:forEach var="characteristic" items="${latestProduct.getCharacteristics()}">
                        <c:if test="${characteristic.getValue() != null}">
                            <c:out value="${characteristic.getValue()}, "/>
                        </c:if>
                    </c:forEach></p>
                    <a href="controller?command=to_product&productId=${latestProduct.getId()}" class="btn btn-primary stretched-link"><fmt:message key="main.toProduct"/> </a>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>