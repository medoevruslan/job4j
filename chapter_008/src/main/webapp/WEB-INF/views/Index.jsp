<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <style>
        nav ul li:last-child {
            margin-left: 1em;
        }

        .btn-filter {
            margin: 1em 0;
            width: 100%;
        }
    </style>
    <script>

        $(document).ready(function () {
            loadItems();
        });

        $(document).ready(function () {
            $('#regForm').validate ({
                rules: {
                    regLogin: "required",
                    regEmail: "required",
                    regPassword: "required"
                }
            });
        });

        $(document).ready(function () {
            $('#regForm').validate();
        });

        $(document).ready(function () {
            $.each(fetchManufacturers(), function () {
                $('#sel-manufact').append($("<option/>", {
                    value: this.value,
                    text: this.value
                }));
            });
            $('#sel-manufact').bind('change', function () {
                $('#sel-model').empty();
                $('#sel-model').append($("<option/>", {
                    text: '...',
                    selected: 'selected'
                }));
                var manufacturer = $('#sel-manufact option:selected').text();
                $.each(fetchModels(manufacturer), function () {
                    $('#sel-model').append($("<option/>", {
                        value: this.value,
                        text: this.value
                    }));
                });
            });
        });

        function loadItems(items = ${requestScope.items}) {
            $('#table tbody').empty();
            $.each(items, function () {
                if (!this.sold) {
                    $('#table tbody').append(
                        '<tr><td>' + this.header + '</td>'
                        + '<td> <img src="" class="image" alt="car_image" style="width:150px;height:100px"> </td>'
                        + '<td>' + this.price + '</td></tr>'
                    );
                    if (this.images[0] != null) {
                    $('tbody .image:last')
                        .attr('src', "${pageContext.servletContext.contextPath}/images" + this.images[0].path);
                    }
                }
            });
        }

        function filter() {
            var day = $('#filterDay').is(':checked');
            var photo = $('#filterPhoto').is(':checked');
            var manufacturer = $('#sel-manufact').val();
            var model = $('#sel-model').val();
            var items = ${requestScope.items};
            if (items != null) {
                items = filterLastDay(day, items);
                items = filterByPhoto(photo, items);
                items = filterByManufacturer(manufacturer, model, items);
                loadItems(items);
            }
        }

        function filterLastDay(filter, items) {
            var threshold = new Date().setHours(0, 0, 0, 0);
            if (filter) {
                items = items.filter(function (item) {
                    return item.created >= threshold;
                });
            }
            return items;
        }

        function filterByPhoto(filter, items) {
            if (filter) {
                items = items.filter(function (item) {
                   return item.images.length !== 0;
                });
            }
            return items;
        }

        function filterByManufacturer(manufacturer, model, items) {
            if (manufacturer !== "...") {
                if (model !== "...") {
                    items = items.filter(function (item) {
                        return (item.car.manufacturer === manufacturer && item.car.model === model);
                    });
                } else {
                    items = items.filter(function (item) {
                        return (item.car.manufacturer === manufacturer);
                    });
                }
            }
            return items;
        }

        function fetchManufacturers() {
            var manufacturers = [];
            <c:forEach var="manufacturer" items="${sessionScope.manufacturers}">
            manufacturers.push({
                id: "${manufacturer.id}",
                value: "${manufacturer.name}"
            });
            </c:forEach>
            return manufacturers;
        }

        function fetchModels(manufacturer) {
            var models = [];
            <c:forEach var="manufacturers" items="${sessionScope.manufAndModels}">
            <c:forEach var="model" items="${manufacturers.value}">
            if (manufacturer === "${manufacturers['key']}") {
                models.push({
                    id: "${model.id}",
                    value: "${model.name}"
                });
            }
            </c:forEach>
            </c:forEach>
            return models;
        }
        
        function checkAuth() {
            $.ajax({
                url: 'checkAuth',
                type: 'post',
                dataType: 'text/plain',
                async: false,
                complete: function (data) {
                    var result = data.responseText;
                    if (result === "false") {
                        $('#myModal').modal('show');
                    }
                }
            });
        }

        function createUser() {
            if ($('#regForm').valid()) {
                var user = new Object();
                user.login = $('#registerLogin').val();
                user.email = $('#email').val();
                user.password = $('#registerPassword').val();
                $.ajax({
                    url: 'create',
                    type: 'post',
                    contentType: 'json',
                    data: JSON.stringify(user),
                    complete: function () {
                        window.location = '${pageContext.servletContext.contextPath}/secure/profile';
                    }
                });
            }
        }

        function signIn() {
            var login = $('#signLogin').val();
            var password = $('#signPassword').val();
            $.ajax({
                async: false,
                url: 'signIn',
                type: 'post',
                // contentType: 'application/x-www-form-urlencoded',
                dataType: 'text/plain',
                data: ({login: login, password: password}),
                complete: function (data) {
                    if (data.responseText === 'false') {
                        $('#message').text('Wrong login or password, try again')
                    }
                    else {
                        window.location = '${pageContext.servletContext.contextPath}/secure/profile'
                    }
                }
            });
        }

    </script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <c:set var="user" value="guest" scope="page"/>
            <c:if test="${sessionScope.user != null}">
                <c:set var="user" value="${sessionScope.user.login}" scope="page"/>
            </c:if>
            <a class="navbar-brand" href="#">Carstore for ${user}</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <li><a href="#" data-toggle="modal" data-target="#myModal">Entry and register</a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <button type="button" class="btn btn-primary navbar-btn" onclick="location.href='${pageContext.servletContext.contextPath}/logout'">
                            Logout
                        </button>
                        <button type="button" class="btn btn-primary navbar-btn" onclick="location.href='${pageContext.servletContext.contextPath}/secure/profile'">
                            My items
                        </button>
                    </li>
                </c:otherwise>
            </c:choose>
            <li><button type="button" class="btn btn-success navbar-btn" onclick="checkAuth();">New Item</button></li>
        </ul>
    </div>
</nav>

<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <div class="checkbox">
            <label><input type="checkbox" name="day" id="filterDay" value="lastDay">Current day items</label>
        </div>
        <div class="checkbox">
            <label><input type="checkbox" name="photo" id="filterPhoto" value="withPhoto">Only with photo</label>
        </div>
        <label for="sel-manufact">Filter by manufacturer and model</label>
        <select class="form-control" id="sel-manufact">
            <option selected="selected">...</option>
        </select>
        <label for="sel-model"></label>
        <select class="form-control" id="sel-model">
            <option selected="selected">...</option>
        </select>
        <button type="submit" class="btn btn-success btn-filter" onclick="filter();">Filter</button>
    </div>
</div>

<div>
    <table class="table table-bordered form-group" id="table">
        <thead>
        <tr style="background: #f6f6f6">
            <th>Item name</th>
            <th class="col-md-2">Photo</th>
            <th class="col-md-1">Price</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>

<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×</button>
                <h4 class="modal-title" id="myModalLabel">Login/Registration </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-8">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#Login" data-toggle="tab">Login</a></li>
                            <li><a href="#Registration" data-toggle="tab">Registration</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <!--Signin form -->
                        <div class="tab-content">
                            <div class="tab-pane active" id="Login">
                                <form role="form" class="form-horizontal">
                                    <div class="form-group" style="margin-top: 10px">
                                        <label for="signLogin" class="col-sm-2 control-label">
                                            Login</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="signLogin" placeholder="Login"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="signPassword" class="col-sm-2 control-label">
                                            Password </label>
                                        <div class="col-sm-6">
                                            <input type="password" class="form-control" id="signPassword" placeholder="Password"/>
                                        </div>
                                        <p><span id="message"></span></p>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                        </div>
                                        <div class="col-sm-10">
                                            <button type="button" class="btn btn-primary btn-sm" onclick="signIn()"> Submit </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!--Registration form-->
                            <div class="tab-pane" id="Registration">
                                <form role="form" class="form-horizontal" id="regForm">
                                    <div class="form-group" style="margin-top: 10px">
                                        <label for="registerLogin" class="col-sm-2 control-label">
                                            Login</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="registerLogin" name="regLogin" placeholder="Login"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="col-sm-2 control-label">
                                            Email</label>
                                        <div class="col-sm-10">
                                            <input type="email" class="form-control" id="email" name="regEmail" placeholder="Email" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="registerPassword" class="col-sm-2 control-label">
                                            Password</label>
                                        <div class="col-sm-10">
                                            <input type="password" class="form-control" id="registerPassword" name="regPassword" placeholder="Password" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                        </div>
                                        <div class="col-sm-10">
                                            <button type="button" class="btn btn-primary btn-sm" onclick="createUser()">
                                                Save & Continue</button>
                                            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"
                                                    aria-hidden="true">
                                                Cancel</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>