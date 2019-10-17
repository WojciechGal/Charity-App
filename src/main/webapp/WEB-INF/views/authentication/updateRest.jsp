<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Zmiana danych</title>
    <link rel="stylesheet" href="../../../resources/css/style.css" />
</head>
<body>

<%@include file="../fragments/header.jspf"%>

<section class="login-page">
    <h2>Zmień dane</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input type="text" path="name"/>
            <div>
                <form:errors path="name" cssStyle="color: red"/>
            </div>
        </div>
        <div class="form-group">
            <form:input type="text" path="lastName"/>
            <div>
                <form:errors path="lastName" cssStyle="color: red"/>
            </div>
        </div>
        <div class="form-group">
            <form:input type="email" path="email"/>
            <div>
                <form:errors path="email" cssStyle="color: red"/>
            </div>
        </div>
            <div>
                <form:hidden path="password" value="${user.password}"/>
                <form:hidden path="id" value="${user.id}"/>
                <form:hidden path="enabled" value="${user.enabled}"/>
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
