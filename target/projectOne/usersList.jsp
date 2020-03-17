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
                <!--post-->
                <form name="delete" action="/PP1_war_exploded/DeleteUserServlet" method="post">
                    <input title="Name"  type="hidden" name="name" value="${user.email}">
                    <input type="submit" title="Submit" value="Delete">
                </form>
            </td>
            <td>
                <form name="update" action="/PP1_war_exploded/UpdateUserServlet" method="get">
                    <input title="Id"  type="hidden" name="id" value="${user.id}">
                    <%--<input title="Name"  type="hidden" name="name" value="${user.email}">
                    <input title="Password"  type="hidden" name="password" value="${user.password}">
                    <input title="Age"  type="hidden" name="age" value="${user.age}">--%>
                    <input type="submit" title="Submit" value="Update">
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<br>

<a href="/PP1_war_exploded/CreateUserServlet"><h3>Create user</h3></a>

</body>
</html>
