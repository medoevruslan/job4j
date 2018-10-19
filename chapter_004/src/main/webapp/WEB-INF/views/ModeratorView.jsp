<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ModeratorView</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <jsp:include page="/WEB-INF/views/css/Style.css" />
    </style>
    <script>

        function doFilter(role) {
            var table = document.getElementById('table');
            var row = table.getElementsByTagName('tr');
            if (role !== '') {
                for (var r = 1; r < row.length; r++) {
                    row[r].style.display = '';
                    var coll = row[r].childNodes[5];
                    if (coll.childNodes[0].data !== role) {
                        row[r].style.display = 'none';
                    }
                }
            } else {
                for (var r = 1; r < row.length; r++) {
                    row[r].style.display = ''
                }
            }
        }

    </script>
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
                            <!-- Dropdown-->
                            <li class="panel panel-default" id="dropdown">
                                <a data-toggle="collapse" href="#dropdown-lvl1">
                                    <span class="glyphicon glyphicon-user"></span> Filter by role <span class="caret"></span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-lvl1" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <c:forEach var="role" items="${applicationScope.roles}">
                                                <li>
                                                    <a href="#" onclick="doFilter('${role.name}');">
                                                        <span>${role.name}</span>
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </li>
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
                <th>Role</th>
                <th>Action</th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.address.name}</td>
                    <td><c:forEach var="music" items="${user.musicType}">
                        <c:out value="${music.name}"/>
                    </c:forEach></td>
                    <td>${user.role.name}</td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="submit" class="form-control button btn-default" value="Edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <footer class="pull-left footer">
        <p class="col-md-12">
        <hr class="divider">
        Copyright &COPY; 2018 Meloman
    </footer>
</div></body>
</html>