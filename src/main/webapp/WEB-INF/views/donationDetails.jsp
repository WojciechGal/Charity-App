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
</head>
<body>

<%@include file="fragments/headerForm.jspf" %>


<section class="help">
    <h2>Szczegóły twojego daru</h2>
    <div class="help--slides active">
        <p><span style="font-weight: bolder">Numer seryjny: </span>${donation.id}</p>
        <p><span style="font-weight: bolder">Organizacja odbierająca dar: </span>${donation.institution.name}</p>
        <p><span style="font-weight: bolder">Status: </span><c:choose>
            <c:when test="${0 == donation.status}">Nieodebrany</c:when>
            <c:otherwise>Odebrany</c:otherwise>
        </c:choose></p>
        <p><span style="font-weight: bolder">Faktyczna data i godzina przekazania daru: </span><c:choose><c:when
                test="${empty donation.receivedOn}">Nieodebrany</c:when><c:otherwise><fmt:parseDate
                value="${donation.receivedOn}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <fmt:formatDate pattern="HH:mm, dd.MM.yyyy" value="${parsedDateTime}"/></c:otherwise></c:choose></p>
        <p><span style="font-weight: bolder">Umówiona data i godzina przekazania
                    daru: </span>${donation.pickUpTime}, <fmt:parseDate
                value="${donation.pickUpDate}" pattern="yyyy-MM-dd" var="parsedDate2" type="date"/>
            <fmt:formatDate pattern="dd.MM.yyyy" value="${parsedDate2}"/></p>
        <p><span style="font-weight: bolder">Data i godzina utworzenia wpisu: </span><fmt:parseDate
                value="${donation.createdOn}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime3" type="both"/>
            <fmt:formatDate pattern="HH:mm, dd.MM.yyyy" value="${parsedDateTime3}"/></p>
        <p><span style="font-weight: bolder">Przekazane dary:</span>
            <c:forEach items="${donation.categories}" var="category">
        <p>- ${category.name} -</p>
        </c:forEach>
        </p>
        <p><span style="font-weight: bolder">Ilość worków: </span>${donation.quantity}</p>
        <c:if test="${donation.status == 0}">
            <p><a href="/donationReceived/${donation.id}" class="btn btn--without-border"><span
                    style="font-weight: bolder">Oznacz jako
                        odebrany</span></a></p>
        </c:if>
        <p><a href="/myDonations" class="btn btn--without-border"><span style="font-weight: bolder">Powrót</span></a>
        </p>
    </div>
</section>


<%@include file="fragments/footer.jspf" %>

<script src="../../resources/js/app.js"></script>
</body>
</html>