<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23.11.2023
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список продуктов</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/product" method="post">

    <label for="productId">Product:
        <input type="ok" name="productId" id="productId" required>
    </label><br>
    <button type="submit">Search</button>
    <br>
<%@include file="header.jsp"%>
<%--<p>Номер продукта:${product.id()}, название продукта и его количество:${product.nameAndCount()} шт.</p>--%>
</form>
</body>
</html>
