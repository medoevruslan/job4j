<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>NewCar</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.12.0/additional-methods.js"></script>
    <style><%@include file="/WEB-INF/style/style.css"%></style>

    <script>

        $(document).ready(function () {
            $(document).on('change','.upload', function () {
                var lastUpload = $('.upload:last');
                $('.errorSize').empty();
                var uploadInputs = document.querySelectorAll("input[type=file]").length;
                if (lastUpload[0].files[0].size < 3145728) {
                    if (uploadInputs < 5) {
                        $('.errorSize').remove();
                        $('<input type="file" class="upload" name="upload">').insertBefore('#save-btn');
                    }
                } else {
                    $('<div class="errorSize">File have to be less than 3MB</div>').insertAfter(lastUpload);
                    lastUpload.val('');
                }
            });
        });

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

        function fetchTransmissions() {
            var transmissions = [];
            <c:forEach var="transmission" items="${sessionScope.transmissions}">
            transmissions.push({
                id: "${transmission.id}",
                value: "${transmission.type}"
            });
            </c:forEach>
            return transmissions;
        }

        function fetchBodies() {
            var bodies = [];
            <c:forEach var="body" items="${sessionScope.bodies}">
            bodies.push({
                id: "${body.id}",
                value: "${body.type}"
            });
            </c:forEach>
            return bodies;
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

        $(document).ready(function () {
            $.each(fetchManufacturers(), function () {
                $('#sel-manufact').append($("<option/>", {
                    value: this.value,
                    text: this.value
                }));
            });
            $.each(fetchTransmissions(), function () {
                $('#sel-transmission').append($("<option/>", {
                    value: this.value,
                    text: this.value
                }));
            });
            $.each(fetchBodies(), function () {
                $('#sel-body').append($("<option/>", {
                    value: this.value,
                    text: this.value
                }));
            });
        });

        $(document).ready(function () {
            $('#sel-manufact').bind('change', function () {
                $('#sel-model').empty();
                var manufacturer = $('#sel-manufact option:selected').text();
                $.each(fetchModels(manufacturer), function () {
                    $('#sel-model').append($("<option/>", {
                        value: this.value,
                        text: this.value
                    }));
                });
            });
        });

    </script>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">New item</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Hello, ${sessionScope.user.login}</a></li>
            <li>
                <button type="button" class="btn btn-primary navbar-btn" onclick="location.href='${pageContext.servletContext.contextPath}/logout'">
                    Logout
                </button>
                <button type="button" class="btn btn-primary navbar-btn" onclick="location.href='${pageContext.servletContext.contextPath}/secure/profile'">
                    My items
                </button>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="work-space">
        <div class="fields">
            <form action="${pageContext.servletContext.contextPath}/newItem" id="form" enctype="multipart/form-data" method="post">
                <label for="title">Title</label>
                <input id="title" type="text" name="title" required>

                <label for="sel-manufact">Manufacturer</label>
                <select name="manufacturer" id="sel-manufact" required>
                    <option selected="selected"></option>
                </select>

                <label for="sel-model">Model</label>
                <select name="model" id="sel-model" required>
                    <option selected="selected"></option>
                </select>

                <label for="sel-body">Body</label>
                <select name="body_type" id="sel-body" required>
                    <option selected="selected"></option>
                </select>

                <label for="engine">Value of engine   cm<sup>3</sup></label>
                <input type="number" step="0.1" id="engine" name="engine" required>

                <label for="sel-transmission">Transmission</label>
                <select name="transmission" id="sel-transmission" required>
                    <option selected="selected"></option>
                </select>

                <label for="descr">Description</label>
                <textarea id="descr" rows="10" name="description" required></textarea>

                <label for="price">Price</label>
                <input type="number" class="price" id="price" name="price" required>

                <label for="upload">Image</label>
                <input type="file" class="upload" name="upload" id="upload">

                <input type="submit" class="save-btn" id="save-btn" value="Save">
            </form>
        </div>
    </div>
</div>

</body>
</html>