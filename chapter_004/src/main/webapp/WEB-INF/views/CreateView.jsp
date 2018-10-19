<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        <jsp:include page="/WEB-INF/views/css/Style.css" />
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script>

        $(document).ready(function () {
            $('#form').validate ({
                rules: {
                    name: "required",
                    login: "required",
                    password: "required",
                    city: "required",
                    street: "required",
                    house: {
                        digits: true
                    },
                    role: "required"
                }
            });
        });

        $(document).ready(function () {
            $('#login').bind('change', function () {
                $('#message').empty();
                var logn = $('#login').val();
                $.ajax({
                    url: 'checkLogin',
                    type: 'get',
                    data: ({login: logn}),
                    contentType: 'text/plain',
                    dataType: 'text/plain',
                    complete: function (resp) {
                        var result = resp.responseText;
                        if (result === "exist") {
                            $('#message').text('Login already exist');
                        }
                    }
                })
            });
        });

        function create() {
            if ($('#form').valid()) {
                var user = constructUser();
                $.ajax({
                    url: 'create',
                    type: 'post',
                    contentType: 'json',
                    data: JSON.stringify(user),
                    complete: function () {
                        window.location = '${pageContext.servletContext.contextPath}/admin'
                    }
                });
            }
        }

        function constructUser() {
            var user = new Object();
            var role = new Object();
            var address = new Object();
            var musicType = [];
            $("input[name='checkbox']:checked").each(function (i) {
                musicType[i] = $(this).val();
            });
            user.id = '${user.id}';
            user.name = $('#name').val();
            user.login = $('#login').val();
            user.password = $('#password').val();
            address.id = '${address.id}';
            address.userId = '${address.userId}';
            address.city = $('#city').val();
            address.street = $('#street').val();
            address.house = $('#house').val();
            role.name = $('#role').val();
            user.role = role;
            user.address = address;
            user.musicType = musicType;
            return user;
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
                            <li><a href="${pageContext.servletContext.contextPath}/admin">Main Page</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>
            </div>
        </div>
    </div>
    <div class="col-md-10 content">
        <form class="form-group" id="form">
            <div class="col-md-5 content col-md-push-3">
                <input type="text" class="form-control" id="name" name="name" placeholder="Name...">
                <span id="message" style="color: red"></span>
                <input type="text" class="form-control" id="login" name="login" placeholder="Login...">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password...">
                <select id="role" name="role" class="form-control">
                    <c:forEach var="role" items="${applicationScope.roles}">
                        <option value="${role.name}" ${role.name eq role.name ? 'selected' : ''}>${role.name}</option>
                    </c:forEach>
                    <option value="" selected="selected"></option>
                </select>
                <div id="address">
                    <input type="text" class="form-control" id="city" name="city" placeholder="City...">
                    <input type="text" class="form-control" id="street" name="street" placeholder="Street...">
                    <input type="text" class="form-control" id="house" name="house" placeholder="House №...">
                </div>
                <div class="col-md-8 col-md-push-2">
                    <h4>Choose your favor music type</h4>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="checkbox" value="Rap" > Rap
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="checkbox" value="Jazz" > Jazz
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="checkbox" value="Funk" > Funk
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="checkbox" value="Rock" > Rock
                    </label>
                </div>
                <div class="col-md-6 col-md-push-3">
                    <br>
                    <input type="button" class="form-control button btn-default" value="Create" onclick="create()">
                </div>
            </div>
        </form>
    </div>
    <footer class="pull-left footer">
        <p class="col-md-12">
        <hr class="divider">
        Copyright &COPY; 2018 Meloman
    </footer>
</div>
</body>
</html>
