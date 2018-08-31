<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: ruslanmd
  Date: 31.08.18
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% int id = Integer.valueOf(request.getParameter("id"));%>
<% User user = ValidateService.getInstance().findById(id);%>

<html>
<head>
    <title></title>
</head>
<body>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%=user.getId()%>">
    Name : <input type="text" name="name" value="<%=user.getName()%>">
    Email : <input type="text" name="email" value="<%=user.getEmail()%>">
    Login : <input type="text" name="login" value="<%=user.getLogin()%>">
    <input type="submit" value="Save">
</form>
</body>
</html>
