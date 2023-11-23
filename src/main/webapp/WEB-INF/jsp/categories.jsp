<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23.11.2023
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Категории товаров</title>
</head>
<body>
<ul>
    <c:forEach var="category" items="${categories}">
        <li> Номер: ${category.category()}, название: ${category.categoryName()}</li>
    </c:forEach>
</ul>
</body>
</html>
