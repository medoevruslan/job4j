<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="user" value="${requestScope.user}"/>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body >
<div class="container">
    <form action="${pageContext.servletContext.contextPath}/logout" method="post">
        <h2 class="text-center">Welcome, ${user.name}</h2>
        <div class="col-md-4 col-md-offset-4 form-group">
            <input class="form-control button btn-default" type="submit" value="Logout">
        </div>
        <br/>
    </form>
    <table class="table table-bordered form-group" id="table">
        <tr class="active">
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Login</th>
            <th>Country</th>
            <th>City</th>
            <th>CreateDate</th>
            <th>Action</th>
        </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.login}</td>
            <td>${user.country}</td>
            <td>${user.city}</td>
            <td>${user.createDate}</td>
            <td><form action="${pageContext.servletContext.contextPath}/edit" method="get">
                <input type="hidden" name="id" value="${user.id}">
                <div class="form-group">
                    <input class="form-control button btn-default" type="submit" value="Edit">
                </div>
            </form></td>
        </tr>
    </table>
</div>
</body>
</html>
