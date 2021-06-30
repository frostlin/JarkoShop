<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>
<html>
<head>
    <title>User profile</title>
</head>
<jsp:include page="../modules/header.jsp"/>

<body>
<div class="container">
        <div class="row">
        <div class="col">
            <h4><fmt:message key="profile.username"/>${currentUser.getLogin()}</h4>
            <form action="controller" method="post">
                <button class="btn btn-outline-primary" type="submit" name="command" value="to_change_password">
                    <fmt:message key="profile.changePass"/>
                </button>
            </form>
            <form action="controller" method="post">
                <button class="btn btn-outline-primary" type="submit" name="command" value="logout">
                    <fmt:message key="profile.logout"/>
                </button>
            </form>
        </div>

    <div class="col-9">


    </div>

    <table class="table table-bordered">
        <thead>
        <tr><th scope="col"><fmt:message key="reviewTable.review"/> </th>`</tr>
        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="review" varStatus="loop">
            <tr>
                <td>
                    <div class="flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1"><c:out value="${review.getUser().getLogin()}"/></h5>
                            <small><c:out value="${review.getDate()}"/></small>
                        </div>
                        <p class="my-3"><c:out value="${review.getContent()}"/></p>
                        <p style="font-size: smaller"><fmt:message key="review.rating"/> <c:out value="${review.getRating()}"/> / 10</p>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
