<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${user.id}">
    <c:choose>
        <c:when test="${sessionScope.user.role eq 'admin'}">
    Name : <input type="text" name="name" value="${user.name}"><br>
    Email : <input type="text" name="email" value="${user.email}"><br>
    Login : <input type="text" name="login" value="${user.login}"><br>
    Password : <input type="text" name="password" value="${user.password}"><br>
    Role : <select name="role"><br>
                <c:forEach items="${applicationScope.roles}" var="role">
                    <option value="${role}" ${role eq role ? "selected" : ""}>${role}</option>
                </c:forEach>
            <input type="submit" value="Save">
        </c:when>
        <c:otherwise>
    Name : <input type="text" name="name" value="${user.name}"><br>
    Email : <input type="text" name="email" value="${user.email}"><br>
    Login : <input type="text" name="login" value="${user.login}"><br>
    Password : <input type="text" name="password" value="${user.password}"><br>
            <input type="hidden" name="role" value="${user.role}">
            <input type="submit" value="Save">
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
