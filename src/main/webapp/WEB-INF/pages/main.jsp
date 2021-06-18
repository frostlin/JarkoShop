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

        </div>
        <div class="col-6">
            <h1><fmt:message key="main.greeting"/></h1>

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