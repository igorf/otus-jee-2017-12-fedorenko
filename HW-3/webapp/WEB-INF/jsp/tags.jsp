<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Сотрудники c зп выше средней, список тегов</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
    <div class="col-sm-12">
        <ul>
            <c:forEach items="${pageContext.request.getAttribute('tagsList')}" var="item">
                <li>${item.key} -> ${item.value}</li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>