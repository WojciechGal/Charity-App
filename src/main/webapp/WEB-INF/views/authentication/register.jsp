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
    <title>Rejestracja</title>
    <link rel="stylesheet" href="../../../resources/css/style.css" />
    <script src="../../../resources/js/passValid.js"></script>
</head>
<body>

<%@include file="../fragments/header.jspf"%>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input type="text" path="name" placeholder="Imię" />
            <div>
                <form:errors path="name" cssStyle="color: red"/>
            </div>
        </div>
        <div class="form-group">
            <form:input type="text" path="lastName" placeholder="Nazwisko" />
            <div>
                <form:errors path="lastName" cssStyle="color: red"/>
            </div>
        </div>
        <div class="form-group">
            <form:input type="email" path="email" placeholder="Email" />
            <div>
                <form:errors path="email" cssStyle="color: red"/>
            </div>
        </div>
        <div class="form-group">
            <form:input id="firstPass" type="password" path="password" placeholder="Hasło" />
            <div>
                <form:errors path="password" cssStyle="color: red"/>
            </div>
        </div>
        <div class="form-group">
            <input id="secondPass" type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@include file="../fragments/footer.jspf"%>

<script src="<c:url value="../../../resources/js/app.js"/>"></script>
</body>
</html>
