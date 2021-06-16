<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>

<!DOCTYPE html>
<html>
<head>
    <title>main</title>
</head>
<body>
<jsp:include page="../modules/header.jsp"/>

<br/>
<h1><fmt:message key="main.greeting"/></h1>
<br/>

</body>
</html>