<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>catalog</title>
    <!--<link href="${pageContext.request.contextPath}/assets/style/adminMain.css" rel="stylesheet" type="text/css">-->
</head>
<jsp:include page="../modules/header.jsp"/>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="sidebar">
            <ul class="nav_list">
                <li>
                    <button type="submit" class="btn btn-link" name="changeLocale" value="en_US">
                        <fmt:message key="admin.orders"/>
                    </button>
                </li>
                <li>
                    <button type="submit" class="btn btn-link" name="changeLocale" value="en_US">
                        <fmt:message key="controlPanel.users"/>
                    </button>
                </li>
                <li>
                    <button type="submit" class="btn btn-link" name="changeLocale" value="en_US">
                        <fmt:message key="controlPanel.products"/>
                    </button>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
