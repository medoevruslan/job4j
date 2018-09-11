<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${error != '' }">
    <div style="color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    <input type="hidden" name="action" value="add">
    Name : <input type="text" name="name"><br/>
    Email : <input type="text" name="email"><br/>
    Login : <input type="text" name="login"><br/>
    Password : <input type="password" name="password"><br/>
    Role : <select name="role">
                <c:forEach items="${applicationScope.roles}" var="role">
                    <option value="${role}" ${role eq role ? "selected" : ""}>${role}</option>
                </c:forEach>
    <input type="submit" value="create">
</form>
</body>
</html>
