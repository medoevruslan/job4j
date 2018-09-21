<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var access = false;
            var login = $('#login').val();
            var password = $('#password').val();
            var users = (JSON.parse('${requestScope.users}'));
            $.each(users, function() {
                if (login == this.login && password == this.password) {
                    access = true;
                    var role = this.role;
                  $.ajax({
                      url: 'signin',
                      type: 'post',
                      contentType: 'json',
                      data: JSON.stringify(this),
                      success: function() {
                          window.location = '${pageContext.servletContext.contextPath}/' + role;
                      }
                  });
                }
            });
            if (!access) {
                $('#error').text('Login or password is invalid.');
            }
            return access;
        }
    </script>
</head>
<body>
<form>
    <div class="container">
        <div class="form-group" id="error" style="color: #ff0000"></div>
        <div class="form-group">
        <label for="login">Login :</label><input type="text" name="login" id="login" class="form-control">
        <label for="password"> Password :</label><input type="password" name="password" id="password" class="form-control">
        </div>
        <div class="form-group col-md-6 col-md-offset-3">
        <input type="button" class="btn btn-default form-control" value="Login" onclick="return validate();">
        </div>
    </div>
</form>
</body>
</html>
