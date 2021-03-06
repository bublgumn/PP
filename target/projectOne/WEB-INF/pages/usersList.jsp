<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 27.02.2020
  Time: 18:58
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!--
this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
-->

<h1>User list</h1>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Age</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.age}</td>
            <td>
                <form name="delete" action="/PP_war_exploded/admin/DeleteUserServlet" method="post">
                    <input title="Id"  type="hidden" name="id" value="${user.id}">
                    <input type="submit" title="Submit" value="Delete">
                </form>
            </td>
            <td>
                <form name="update" action="/PP_war_exploded/admin/UpdateUserServlet" method="get">
                    <input title="Id"  type="hidden" name="id" value="${user.id}">
                    <input type="submit" title="Submit" value="Update">
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<br>

<a href="/PP_war_exploded/CreateUserServlet"><h3>Create user.</h3></a>
<a href="/PP_war_exploded/user"><h3>User page.</h3></a>

</body>
</html>
