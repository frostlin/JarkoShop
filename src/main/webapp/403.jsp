<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>

<html>
<head>
    <title>Error 403</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
</head>
<jsp:include page="WEB-INF/pages/modules/header.jsp"/>

<body>
<div class="container">
    <h5><fmt:message key="errorPage.403comment"/></h5>
    <br/>
    <h5><fmt:message key="errorPage.statusCode"/> 403</h5>
    <br/>
</div>
</body>
</html>