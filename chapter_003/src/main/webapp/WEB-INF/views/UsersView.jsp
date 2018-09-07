<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body >
<c:set var="user" value="${user}" scope="request"/>
<form action="${pageContext.servletContext.contextPath}/logout" method="post">
    <h2 align="center">Welcome, ${user.name}<br><input align="center" type="submit" value="Logout"></h2>
</form>
<br/>
<table border="1 px solid black" cellspacing="0" cellpadding="7">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Login</th>
        <th>CreateDate</th>
        <th>Action</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.login}</td>
        <td>${user.createDate}</td>
        <td><form action="${pageContext.servletContext.contextPath}/edit" method="get">
            <input type="hidden" name="id" value="${user.id}">
            <input type="submit" value="Edit">
            </form></td>
    </tr>
</table>
</body>
</html>
