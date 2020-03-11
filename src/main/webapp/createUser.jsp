<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 27.02.2020
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form name="user" action="/PP1_war_exploded/CreateUserServlet" method="post">
    <p>Name</p>
    <input title="Name" type="text" name="name">
    <p>Password</p>
    <input title="Email" type="text" name="password">
    <p>Age</p>
    <input title="Age" type="number" name="age">
    <input type="submit" title="Submit" value="OK">
</form>

</body>
</html>
