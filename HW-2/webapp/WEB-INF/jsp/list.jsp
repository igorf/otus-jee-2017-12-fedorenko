<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Сотрудники</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
    <div class="col-sm-12">
        <table border="1">
            <tr>
                <th>Имя</th>
                <th>Должность</th>
                <th>Телефон</th>
                <th>Город</th>
                <th>з/п</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${pageContext.request.getAttribute('employeeList')}" var="item">
            <tr>
                <th>${item.firstname} ${item.lastname}</th>
                <th>${item.position}</th>
                <th>${item.phone}</th>
                <th>${item.city}</th>
                <th>${item.salary}</th>
                <th><a href="/remove?removeId=${item.id}">Удалить</a></th>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>