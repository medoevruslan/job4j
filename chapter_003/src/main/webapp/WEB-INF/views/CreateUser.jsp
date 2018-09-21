<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <title></title>
</head>
<body>
<script>
    $(document).ready(function() {
        $('#form').validate({
            rules: {
                name: "required",
                email: {
                    required: true,
                    email: true
                },
                login: "required",
                password: "required",
                country: "required",
                city: "required",
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
                        $('#message').text('Login is already exist');
                    }
                }
            });
        });
    });

    $(document).ready(function () {
        $('#country').bind('change', function () {
            $('#city').empty();
            $.ajax({
                url: 'cities',
                type: 'get',
                contentType: 'text/plain',
                dataType: 'json',
                data: ({country: $('#country').val()}),
                complete: function (resp) {
                    var cities = JSON.parse(resp.responseText);
                    for (var i = 0; i < cities.length; i++) {
                        $('#city').append($('<option>', {
                            value: cities[i],
                            text: cities[i]
                        }));
                    }
                }
            });
        });
    });

</script>
<form action="${pageContext.servletContext.contextPath}/create" method="post" id="form">
    <input type="hidden" name="action" value="add">
    <div class="container">
        <div class="form-group col-md-5">
            <input class="form-control" type="text" name="name" id="name" placeholder="Name"><br/>
            <input class="form-control" type="email" name="email" id="email" placeholder="Email"><br/>
            <input class="form-control" type="text" name="login" id="login"
                   placeholder="Login"><span id="message" style="color: red"></span><br/>
            <input class="form-control" type="password" name="password" id="password" placeholder="Password"><br/>
            <label for="country">Country :</label>
            <select class="form-control" name="country" id="country">
                <c:forEach var="cntr" items="${applicationScope.countries}">
                    <option value="${cntr}" ${cntr eq cntr ? "selected" : ""}>${cntr}</option>
                </c:forEach>
                <option value="" selected="selected"></option>
            </select>
            <label for="city">City :</label>
            <select class="form-control" name="city" id="city">
                <option value="" selected="selected"></option>
            </select>
            <label for="role">Role :</label>
            <select class="form-control" name="role" id="role">
                <c:forEach items="${applicationScope.roles}" var="role">
                    <option value="${role}" ${role eq role ? "selected" : ""}>${role}</option>
                </c:forEach>
                <option value="" selected="selected"></option>
            </select>
            <br/>
            <div class="col-md-3">
                <input class="form-control button btn-default" type="submit" value="Create">
            </div>
        </div>
    </div>
</form>
</body>
</html>
