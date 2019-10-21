<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Strona użytkownika</title>
    <link rel="stylesheet" href="../../resources/css/style.css"/>
    <link rel="stylesheet" href="../../resources/css/errors.css"/>
    <link rel="stylesheet" href="../../resources/css/table.css"/>
</head>
<body>

<%@include file="fragments/headerForm.jspf" %>


<section class="help">
    <h2>Wszystkie twoje dary</h2>
    <div class="help--slides active">
        <table>
            <thead>
            <tr>
                <th>Numer</th>
                <th>Status</th>
                <th>Faktyczna data i godzina odebrania</th>
                <th>Umówiona data i godzina odebrania</th>

            </tr>
            </thead>
            <tbody>
            <c:if test="${empty donations}">
                <tr>
                    <td colspan="4">Brak danych</td>
                </tr>
            </c:if>
            <c:if test="${not empty donations}">
                <c:forEach items="${donations}" var="donation" varStatus="stat">
                    <tr>
                        <td>${stat.count}</td>
                        <td>
                            <c:choose>
                                <c:when test="${0 == donation.status}">Nieodebrany</c:when>
                                <c:otherwise>Odebrany</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${empty donation.receivedOn}">-</c:when>
                                <c:otherwise><fmt:parseDate
                                        value="${donation.receivedOn}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                                    <fmt:formatDate pattern="HH:mm, dd.MM.yyyy" value="${parsedDateTime}"/></c:otherwise>
                            </c:choose>
                        </td>
                        <td>${donation.pickUpTime}, <fmt:parseDate
                                value="${donation.pickUpDate}" pattern="yyyy-MM-dd" var="parsedDate2" type="date"/>
                            <fmt:formatDate pattern="dd.MM.yyyy" value="${parsedDate2}"/></td>
                        <td class="lastTd"><a href="/donationDetails/${donation.id}" class="btn btn--without-border">Szczegóły</a></td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</section>


<%@include file="fragments/footer.jspf" %>

<script src="../../resources/js/app.js"></script>
</body>
</html>