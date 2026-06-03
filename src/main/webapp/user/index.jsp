<%--
  Created by IntelliJ IDEA.
  User: Duonglbd
  Date: 6/3/2026
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
<h3>EDITION</h3>
<form method="post">
    <div>
        <div>Email</div>
        <input name="email" value="${}">
    </div>
    <div>
        <div>Password</div>
        <input name="password" value="${}">
    </div>
    <div>
        <div>Fullname</div>
        <input name="fullname" value="${}">
    </div>
    <div>
        <div>Role</div>
        <input type="radio" name="admin" value="true"> Admin
        <input type="radio" name="admin" value="false"> User
    </div>
    <div>
        <div>Status</div>
        <input type="radio" name="enabled" value="true"> Active
        <input type="radio" name="enabled" value="false"> Inactive
    </div>
    <div>
        <button formaction="/oe/user/create">Create</button>
        <button formaction="/oe/user/update">Update</button>
        <a href="/oe/user/delete/${}">Delete</a>
        <a href="/oe/user/list">Reset</a>
    </div>
</form>
<hr>
<table border="1" style="width:100%">
    <thead>
    <tr>
        <th>No.</th>
        <th>Email</th>
        <th>Password</th>
        <th>Fullname</th>
        <th>Role</th>
        <th>Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${}</td>
        <td>${}</td>
        <td>${}</td>
        <td>${}</td>
        <td>${}</td>
        <td>${}</td>
        <td>
            <a href="/oe/user/edit/${}">Edit</a> |
            <a href="/oe/user/delete/${}">Delete</a>
        </td>
    </tr>
    </tbody>
</table>
</body>

</html>
