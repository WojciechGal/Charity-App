<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="text-align: center; margin-top: 30px">Witaj Adminie!</h1>

<div>Wszystkie donacje:</div>
<div>
    <c:forEach items="${donations}" var="donation">
        <p>${donation.id}, ${donation.quantity}, ${donation.city}, ${donation.pickUpTime}, ${donation.pickUpDate}</p>
    </c:forEach>
</div>
</body>
</html>
