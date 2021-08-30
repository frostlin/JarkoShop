<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="customTags" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <meta charset="utf-8">
    <title>catalog</title>
</head>
<jsp:include page="../modules/header.jsp"/>
<body>
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="orderTable.id"/> </th>
        <th><fmt:message key="userTable.role"/></th>
        <th><fmt:message key="userTable.surname"/></th>
        <th><fmt:message key="userTable.name"/></th>
        <th><fmt:message key="userTable.lastname"/></th>
        <th><fmt:message key="userTable.telephone"/></th>
        <th><fmt:message key="userTable.dateRegistered"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${currentItemsRange}" var="user" varStatus="loop">
        <tr>
            <td><c:out value="${user.getId()}"/> </td>
            <td><c:out value="${user.getRole().getRoleName()}"/> </td>
            <td><c:out value="${user.getSurname()}"/> </td>
            <td><c:out value="${user.getName()}"/> </td>
            <td><c:out value="${user.getLastname()}"/> </td>
            <td><c:out value="${user.getTelephone()}"/> </td>
            <td><c:out value="${user.getDateRegistered()}"/> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<custom:pagination totalPageCount="${totalPageCount}" currentPage="${currentAdminUsersPage}" command="to_admin_users"/>
</body>
</html>