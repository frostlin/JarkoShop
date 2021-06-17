<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Title</title>
</head>
<body>
<jsp:include page="../modules/header.jsp"/>

<label style="color: red">${searchResult}</label><br/>
        this is search
</body>
</html>
