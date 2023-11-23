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
<p>Номер продукта: ${product.id()}, название продукта и его количество: ${product.nameAndCount()} шт.</p>
</body>
</html>
