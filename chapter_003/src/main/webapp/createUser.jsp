<%--
  Created by IntelliJ IDEA.
  User: ruslanmd
  Date: 31.08.18
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    <input type="hidden" name="action" value="add">
    Name : <input type="text" name="name"><br/>
    Email : <input type="text" name="email"><br/>
    Login : <input type="text" name="login"><br/>
    <input type="submit" value="create">
</form>
</body>
</html>
