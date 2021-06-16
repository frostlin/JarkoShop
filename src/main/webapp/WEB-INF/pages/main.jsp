<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>

<!DOCTYPE html>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/WEB-INF/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>main</title>
    </head>

    <body>
    <jsp:include page="modules/header.jsp"/>


    <br/>
        <h1><fmt:message key="main.greeting"/></h1>
    <br/>





    <script type="text/javascript" src="${pageContext.request.contextPath}/WEB-INF/assets/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/WEB-INF/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>