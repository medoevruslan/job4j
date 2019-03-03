<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>

        $(document).ready(function () {
            loadItems();
        });

        $(document).ready(function () {
            $(document).on('change','input[type="checkbox"]', function () {
                var isChecked = this.checked;
                var id = $(this).attr('value');
                $.ajax({
                    url: '${pageContext.servletContext.contextPath}/updateItem',
                    type: 'post',
                    contentType: 'application/x-www-form-urlencoded',
                    data: ({'isSold' : isChecked, 'id' : id})
                });
            });
        });

        function loadItems() {
            $.ajax({
                url: 'profile',
                type: 'post',
                dataType: 'json',
                complete: function (data) {
                    var items = JSON.parse(data.responseText);
                    $.each(items, function () {
                        $('#table tbody').append(
                            '<tr><td>' + this.header + '</td>'
                            + '<td><img src="" class="image" style="width:150px;height:100px" alt="car_image"></td>'
                            + '<td>' + this.price + '</td>'
                            + '<td><input class="checkbox" type="checkbox" name="sold"></td></tr>'
                        );
                        if (this.images[0] != null) {
                            $('tbody .image:last')
                                .attr('src', "${pageContext.servletContext.contextPath}/images" + this.images[0].path);
                        }
                        $('tbody .checkbox:last').attr({value: this.id, checked: this.sold});
                    });
                }
            });
        }


    </script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">Main page</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Hello, ${sessionScope.user.login}</a></li>
            <li>
                <button type="button" class="btn btn-primary navbar-btn"
                        onclick="location.href='${pageContext.servletContext.contextPath}/logout'">Logout</button>
                <button type="button" class="btn btn-success navbar-btn"
                        onclick="location.href='${pageContext.servletContext.contextPath}/newItem'">New item</button>
            </li>
        </ul>
    </div>
</nav>

<div>
    <table class="table table-bordered form-group" id="table">
        <thead>
        <tr style="background: #f6f6f6">
            <th>Item name</th>
            <th class="col-md-3">Photo</th>
            <th class="col-md-1">Price</th>
            <th class="col-sm-1">Sold</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

</body>
</html>