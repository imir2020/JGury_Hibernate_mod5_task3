<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.11.2023
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список работников</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/employees" method="post">
    <h2>Внести данные нового работника:</h2>
    <label for="lastName"><br/>Фамилия:
        <input type="text" name="lastName" id="lastName">
    </label><br/>

    <label for="name"><br/>Имя:
        <input type="text" name="name" id="name">
    </label><br/>

    <label for="middleName"><br/>Отчество:
        <input type="text" name="middleName" id="middleName">
    </label><br/>

    <label for="dateBirth"><br/>Дата рождения:
        <input type="date" name="dateBirth" id="dateBirth">
    </label><br/>

    <label for="phoneNumber"><br/>Номер телефона:
        <input type="text" name="phoneNumber" id="phoneNumber">
    </label><br/>

    <label for="address"><br/>Адрес:
        <input type="text" name="address" id="address">
    </label><br/>

    <label for="rankId"><br/>Номер должности:
        <input type="text" name="rankId" id="rankId">
    </label><br/>

    <input type="submit" value="send">

</form>
<h2>Список работников:</h2>
<c:forEach var="employee" items="${requestScope.allEmployees}">
    <li>${employee.lastName} ${employee.name} ${employee.middleName}</li>
</c:forEach>
<%@include file="header.jsp" %>
<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
            <br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
