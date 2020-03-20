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
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.age}</td>
        </tr>
        <tr>
            <form name="update" action="/PP_war_exploded/admin/UpdateUserServlet" method="post">
                <td>
                    <input title="Id"  type="hidden" name="id" value="${user.id}">
                </td>
                <td>
                    <input title="Name" type="text" name="name">
                </td>
                <td>
                    <input title="Password" type="text" name="password">
                </td>
                <td>
                    <input title="Age" type="number" name="age" >
                </td>
                <td>
                    <input type="submit" title="Submit" value="Update">
                </td>
            </form>
        </tr>
    </tbody>
</table>

</body>
</html>
