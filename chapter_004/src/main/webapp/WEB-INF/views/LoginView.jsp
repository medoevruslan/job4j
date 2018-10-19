<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <jsp:include page="/WEB-INF/views/css/Style.css" />
    </style>
    <script>

        function validate() {
            var access = false;
            var login = $('#login').val();
            var password = $('#password').val();
            var users = JSON.parse('${requestScope.users}');
            $.each(users, function () {
                if(login === this.login && password === this.password) {
                    access = true;
                    var role = this.role.name;
                    $.ajax({
                        url: 'login',
                        type: 'post',
                        contentType: 'json',
                        data: JSON.stringify(this),
                        complete: function () {
                            window.location = '${pageContext.servletContext.contextPath}/' + role
                        }
                    });
                }
            });
            if (!access) {
                $('#message').text("Login or password is invalid, please try again.")
            }
        }

    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3 col-md-push-5">
            <div class="form-login">
                <h4>Welcome</h4>
                <input type="text" class="form-control" id="login" name="login" placeholder="login">
                </br>
                <input type="text" class="form-control" id="password" name="password" placeholder="password">
                </br>
                <div class="wrapper">
                    <span class="group-btn">
                     <input type="button" class="btn btn-primary btn-md" value="login" onclick="validate();">
                    </span>
                </div>
                <span id="message" style="color: red"></span>
            </div>
        </div>
    </div>
</div>
</body>
</html>
