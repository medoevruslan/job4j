<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: ruslanmd
  Date: 31.08.18
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body >
<form action="createUser.jsp" method="get">
    Create user : <input type="submit" value="Push">
</form>
<br/>
<table border="1 px solid black" cellspacing="0" cellpadding="7">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Login</th>
        <th>CreateDate</th>
        <th>Action</th>
        <th>Action</th>
    </tr>
    <% for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getCreateDate()%></td>
        <td><form action=updateUser.jsp method="get">
            <input type="hidden" name="id" value=<%=user.getId()%>>
            <input type="submit" value="Edit"></form></td>
        <td><form action="<%=request.getContextPath()%>/edit" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value=<%=user.getId()%>>
            <input type="submit" value="Delete"></form></td>
    </tr>
    <%}%>
</table>
</body>
</html>
