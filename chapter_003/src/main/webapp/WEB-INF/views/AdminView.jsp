
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/logout" method="post">
    <h2 align="center">Welcome, ${sessionScope.user.name}<br><input type="submit" value="Logout"> </h2>
</form>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    Create user : <input type="submit" value="Push">
</form>
<br/>
<table border="1 px solid black" cellspacing="0" cellpadding="7">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Login</th>
        <th>Role</th>
        <th>CreateDate</th>
        <th>Action</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.login}</td>
            <td>${user.role}</td>
            <td>${user.createDate}</td>
            <td><form action="${pageContext.servletContext.contextPath}/edit" method="get">
                <input type="hidden" name="id" value=${user.id}>
                <input type="submit" value="Edit">
                </form></td>
            <td><form action="${pageContext.servletContext.contextPath}/edit" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value=${user.id}>
                <input type="submit" value="Delete">
                </form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
