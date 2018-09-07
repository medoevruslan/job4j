<%--
  Created by IntelliJ IDEA.
  User: ruslanmd
  Date: 02.09.18
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login : <input type="text" name="login" >
    Password : <input type="password" name="password">
    <input type="submit" value="Login">
</form>
</body>
</html>
