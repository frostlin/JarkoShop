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
<body class="text-center">
<div class="container justify-content-center" style="width: 380px; margin-left: auto; margin-right: auto">
    <form action="controller" method="post" >
        <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="productTable.addNewProduct"/></h1>

        <input type="text" name = "newCategory" id="inputCategory" class="form-control" placeholder="<fmt:message key="productTable.category"/>" autofocus>
        <select class="form-control" name="selectedCategory">
            <c:forEach items="${categoryList}" var="category">
                <option  value="${category.getId()}">
                    <fmt:message key="category.${category.getName()}"/>
                </option>
            </c:forEach>
        </select><br/>

        <input type="text" name="newBrand" id="inputBrand" class="form-control" placeholder="<fmt:message key="productTable.brand"/>" autofocus>
        <select class="form-control" name="selectedBrand">
            <c:forEach items="${brandList}" var="brand">
                <option  value="${brand.getId()}">
                        ${brand.getName()}
                </option>
            </c:forEach>
        </select><br/>

        <input type="number" step="0.01" name = "price" id="inputPrice" class="form-control" placeholder="<fmt:message key="productTable.price"/>" autofocus>
        <input type="text" name="model" id="inputModel" class="form-control" placeholder="<fmt:message key="productTable.model"/>" autofocus>
        <input type="text" name="description" id="inputDescription" class="form-control" placeholder="<fmt:message key="productTable.description"/>" autofocus>
        <input type="number" name="warranty" id="inputWarranty" class="form-control" placeholder="<fmt:message key="productTable.warranty"/>" autofocus>
        <input type="number" name="amountStock" id="inputAmountStock" class="form-control" placeholder="<fmt:message key="productTable.stockAmount"/>" autofocus>

        <input type="file" name="photo" size = "50" />

        <c:if test="${errorSignUpMessageKey != null}">
            <label style="color: red"><fmt:message key="${errorSignUpMessageKey}"/></label>
        </c:if>
        <br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="add_new_product">
            <fmt:message key="productTable.addNewProduct"/>
        </button>
    </form>
</div>
</body>
</html>
