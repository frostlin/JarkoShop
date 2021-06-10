<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="l10n.text"/>

<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
<div class="container" style="width: 980px; margin-left: auto; margin-right: auto">
    <footer class="bg-light text-center text-lg-start" style="width: 980px; margin-left: auto; margin-right: auto">
        <div class="container p-1">
            <div class="row">
                <div class="mb-4 ml-3 mb-md-0 text-left">
                    <h5 class="text-uppercase">Footer text</h5>
                    <p>
                        Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iste atque ea quis
                        molestias. Fugiat pariatur maxime quis culpa corporis vitae repudiandae
                        aliquam voluptatem veniam, est atque cumque eum delectus sint!
                    </p>
                </div>
            </div>
        </div>
        <div class="text-center p-1" style="background-color: rgba(0, 0, 0, 0.2);">
            © 2020 Copyright:
            <a class="text-dark" href="https://mdbootstrap.com/">MDBootstrap.com</a>
        </div>
    </footer>
</div>
</body>
</html>