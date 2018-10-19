<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="user" value="${requestScope.user}"/>

<html>
<head>
    <title>User View</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <jsp:include page="/WEB-INF/views/css/Style.css" />
    </style>
</head>
<body>

<jsp:include page="HeadCommonView.jsp"/>

<div class="container-fluid main-container">
    <div class="col-md-2 sidebar">
        <div class="row">
            <!-- Menu -->
            <div class="side-menu">
                <nav class="navbar navbar-default" role="navigation">
                    <!-- Main Menu -->
                    <div class="side-menu-container">
                        <ul class="nav navbar-nav">
                            <li class="active"><span class="glyphicon glyphicon-dashboard"></span> Actions</li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>
            </div>
        </div>
    </div>
    <div class="col-md-10 content">
        <table class="table table-bordered form-control" id="table">
            <tr class="active">
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Password</th>
                <th>Address</th>
                <th>MusicTypes</th>
                <th>Action</th>
            </tr>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.address.name}</td>
                <td><c:forEach var="music" items="${user.musicType}">
                    <c:out value="${music.name}"/>
                </c:forEach></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" class="form-control button btn-default" value="Edit">
                    </form>
                </td>
            </tr>
        </table>
    </div>
    <footer class="pull-left footer">
        <p class="col-md-12">
        <hr class="divider">
        Copyright &COPY; 2018 Meloman
    </footer>
</div></body>
</html>