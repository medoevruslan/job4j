<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="activeRole" value="${sessionScope.user.role}"/>
<c:set var="user" value="${requestScope.user}"/>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            var countryFor;
            var cityFor;
            if ('${activeRole}' === "admin") {
                countryFor = $('#adm-country');
                cityFor = $('#adm-city');
            } else {
                countryFor = $('#usr-country');
                cityFor = $('#usr-city');
            }
            countryFor.bind('change', function () {
                cityFor.empty();
                $.ajax({
                    url: 'cities',
                    type: 'get',
                    contentType: 'text/plain',
                    dataType: 'json',
                    data: ({country : countryFor.val()}),
                    complete: function (resp) {
                        var cities = JSON.parse(resp.responseText);
                        for(var i = 0; i < cities.length; i++) {
                            cityFor.append($('<option>', {
                                value: cities[i],
                                text: cities[i]
                            }));
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${user.id}">
    <c:choose>
        <c:when test="${activeRole eq 'admin'}">
            <div class="container">
                <div class="form-group">
                    <label for="adm-name">Name :</label>
                    <input class="form-control" type="text" name="name" value="${user.name}" id="adm-name"><br>
                    <label for="adm-email">Email :</label>
                    <input class="form-control" type="text" name="email" value="${user.email}" id="adm-email"><br>
                    <label for="adm-login">Login :</label>
                    <input class="form-control" type="text" name="login" value="${user.login}" id="adm-login"><br>
                    <label for="adm-paswword">Password :</label>
                    <input class="form-control" type="text" name="password" value="${user.password}" id="adm-paswword"><br>
                    <div class="row form-group">
                        <div class="col-md-4 text-center">
                            <label for="adm-country">Country</label>
                            <select class="form-control" name="country" id="adm-country">
                                <c:forEach var="cntr" items="${applicationScope.countries}">
                                    <option value="${cntr}" ${cntr eq cntr ? "selected" : ""}>${cntr}</option>
                                </c:forEach>
                                <option value="${user.country}" selected="${user.country}">${user.country}</option>
                            </select>
                        </div>
                        <div class="col-md-4 text-center">
                            <label for="adm-city">City</label>
                            <select class="form-control" name="city" id="adm-city">
                                <option value="${user.city}" selected="${user.city}">${user.city}</option>
                            </select>
                        </div>
                        <div class="col-md-4 text-center">
                            <label for="role">Role</label>
                            <select class="form-control" name="role" id="role"><br>
                                <c:forEach items="${applicationScope.roles}" var="role">
                                    <option value="${role}" ${role eq role ? "selected" : ""}>${role}</option>
                                </c:forEach>
                                <option value="${user.role}" selected="${user.role}">${user.role}</option>
                            </select>
                        </div>
                    </div>
                    <input class="form-control button btn-default" type="submit" value="Save">
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                <div class="form-group">
                    <label for="usr-name">Name :</label>
                    <input class="form-control" type="text" name="name" value="${requestScope.user.name}" id="usr-name"><br>
                    <label for="usr-email">Email :</label>
                    <input class="form-control" type="text" name="email" value="${requestScope.user.email}" id="usr-email"><br>
                    <label for="usr-login">Login :</label>
                    <input class="form-control" type="text" name="login" value="${requestScope.user.login}" id="usr-login"><br>
                    <label for="usr-password">Password :</label>
                    <input class="form-control" type="text" name="password" value="${requestScope.user.password}" id="usr-password"><br>
                    <div class="row form-group">
                        <div class="col-md-6 text-center">
                            <label for="usr-country">Country :</label>
                            <select class="form-control" name="country" id="usr-country">
                                <c:forEach var="cntr" items="${applicationScope.countries}">
                                    <option value="${cntr}" ${cntr eq cntr ? "selected" : ""}>${cntr}</option>
                                </c:forEach>
                                <option value="${user.country}" selected="${user.country}">${user.country}</option>
                            </select>
                        </div>
                        <div class="col-md-6 text-center">
                            <label for="usr-city">City :</label>
                            <select class="form-control" name="city" id="usr-city">
                                <option value="${user.city}" selected="${user.city}">${user.city}</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="role" value="${requestScope.user.role}">
                    <input class="form-control button btn-default" type="submit" value="Save">
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
