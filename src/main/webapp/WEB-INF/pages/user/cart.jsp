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
            <c:choose>
                <c:when test="${'t'.equals(successfulOrder)}">
                    <h2><fmt:message key="cart.successful"/></h2>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${cartItems.size() == 0}">
                            <h2><fmt:message key="cart.empty"/></h2>
                        </c:when>
                        <c:when test="${'t'.equals(successfulOrder)}">
                            <h2><fmt:message key="cart.successful"/></h2>
                        </c:when>
                        <c:otherwise>
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
                                        <form action="controller" method="post">
                                            <span class="badge badge-success badge-pill mr-2 mb-2">$<c:out value="${cartItem.getProduct().getPrice()}"/></span>
                                            <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="to_cart">
                                                <fmt:message key="cart.delete"/>
                                            </button>
                                            <input type="hidden" name="userIdCartItemToDelete" value="${currentUser.getId()}">
                                            <input type="hidden" name="productIdToDelete" value="${cartItem.getProduct().getId()}">
                                        </form>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <form action="controller" method="post" id="checkout">
                            <div>
                                <fmt:message key="cart.address"/>
                                <c:choose>
                                    <c:when test="${currentUser.getAddresses().size() != 0}">
                                        <select class="form-control" name="shippingAddress" form="checkout">
                                            <c:forEach var="address" items="${currentUser.getAddresses()}">
                                                <option  value="${address.getId()}">${address.toString()}</option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="cart.noAddresses"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <h2><fmt:message key="cart.totalPrice"/> ${cartPrice}</h2>
                            <button class="btn btn-outline-primary mx-1" type="submit" name="command" value="checkout">
                                <fmt:message key="cart.checkout"/>
                            </button>
                            <input type="hidden" name="productIdToDelete" value="${cartItem.getProduct().getId()}">
                            <input type="hidden" name="comment" value="test comment">

                        </form>
                    </div>
                    </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>


        <div class="col">
        </div>
    </div>
</div>
</body>
</html>