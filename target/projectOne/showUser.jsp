<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 27.02.2020
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Show User</h1>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Age</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.age}</td>
        </tr>
    </tbody>
</table>
<br>
<a href="/PP1_war_exploded/ListUsersServlet"><h3>User List</h3></a>
</body>
</html>
