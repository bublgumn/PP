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
        <th>Logging in</th>
    </tr>
    </thead>
    <tbody>
    <form name="LoginIn" action="/PP_war_exploded/newLoginIn" method="post">
        <tr>
            <td>
                <input title="Name" type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>
                <input title="Password" type="text" name="password">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" title="Submit" value="Sign in">
            </td>
        </tr>
    </form>
    </tbody>
</table>

<a href="/PP_war_exploded/CreateUserServlet"><h3>Create user</h3></a>

</body>
</html>



