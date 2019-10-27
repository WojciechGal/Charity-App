<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Zmiana hasła</title>
    <link rel="stylesheet" href="../../../resources/css/style.css" />
</head>
<body>

<%@include file="../fragments/header.jspf"%>

<section class="login-page">
    <h2>Zmień hasło</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input type="password" path="pass1" placeholder="Hasło"/>
            <div>
                <form:errors path="pass1" cssStyle="color: red"/>
            </div>
        </div>
        <div class="form-group">
            <form:input type="password" path="pass2" placeholder="Powtórz hasło"/>
            <div>
                <form:errors path="pass2" cssStyle="color: red"/>
            </div>
        </div>
        <div>
            <form:hidden path="id" value="${user.id}"/>
            <form:hidden path="email" value="${user.email}"/>
            <form:hidden path="enabled" value="${user.enabled}"/>
            <form:hidden path="lastName" value="${user.lastName}"/>
            <form:hidden path="name" value="${user.name}"/>
            <form:hidden path="password" value="${user.password}"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Aktualizuj</button>
        </div>
    </form:form>
</section>

<%@include file="../fragments/footer.jspf"%>

<script src="<c:url value="../../../resources/js/app.js"/>"></script>
</body>
</html>

