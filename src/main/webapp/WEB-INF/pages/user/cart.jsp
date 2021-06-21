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
    <div class="row">
        <div class="col">
        </div>
        <div class="col-9">
            <div class="nav-item dropdown mt-3">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="cart.address"/>
                </a>
                <form action="controller" method="post">
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:forEach var="address" items="${currentUser.getAddresses()}">
                            <button class="dropdown-item" type="submit" name="address" value="${address}">
                                    ${address.toString()}</button>
                        </c:forEach>
                    </div>
                </form>
            </div>
            <ul class="list-group">
                <c:forEach var="cartItem" items="${cartItems}" varStatus="status">

                    <li class="list-group-item list-group-item-action justify-content-between d-flex align-text-top">
                        <div class="justify-content-start">
                            <div class="justify-content-center" style="width:76px;height:72px" >
                                <c:if test="${!cartItem.product.photos.isEmpty()}">
                                    <img class="align-self-center" src="${pageContext.request.contextPath}/assets/images/${cartItem.product.id}/${cartItem.product.getPhotos().get(0)}.jpeg" alt="img"  height="72">
                                </c:if>
                            </div>
                            <div class="align-self-start">
                                <h5><c:out value="${cartItem.product.brand} ${cartItem.product.model}"/></h5>
                                <c:forEach var="characteristic" items="${cartItem.product.getCharacteristics()}">
                                    <c:out value="${characteristic.getName()} ${characteristic.getValue()}, "/>
                                </c:forEach>
                                <p class="align-self-end" style="font-size: smaller"><fmt:message key="cart.warranty"/> <c:out value="${cartItem.product.warranty}"/></p>
                            </div>
                        </div>
                        <div class="justify-content-end align-self-center">
                            <form action="controller" method="post" class="my-auto">
                                <span class="badge badge-success badge-pill mr-2 mb-2">$<c:out value="${cartItem.getProduct().getPrice()}"/></span>
                                <button class="btn btn-outline-primary mx-1" type="submit" name="productIdToDelete" value="${cartItem.getProduct().getId()}">
                                        <fmt:message key="cart.delete"/>
                                </button>
                                <input type="hidden" name="userIdCartItemToDelete" value="${currentUser.getId()}">
                                <input type="hidden" name="command" value="delete_cart_item">

                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <h2><fmt:message key="cart.totalPrice"/> ${cartPrice}</h2>
            <form action="controller" method="post" class="my-auto">
            <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="checkout">
                <fmt:message key="cart.checkout"/>
            </button>
            </form>
        </div>
        <div class="col">
        </div>
    </div>
</div>
</body>
</html>