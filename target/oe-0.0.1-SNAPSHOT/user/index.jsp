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
<form action="" method="post">
    <div>
        <div>Username</div>
        <input name="username">
    </div>
    <div>
        <div>Password</div>
        <input name="password">
    </div>
    <div>
        <div>Enabled</div>
        <input name="enabled" type="radio" value="true"> Yes
        <input name="enabled" type="radio" value="false"> No
    </div>
    <div>
        <div>Photo</div>
        <input name="photo">
    </div>
    <div>
        <div>Fullname</div>
        <input name="fullname">
    </div>
    <div>
        <div>Birthday</div>
        <input name="birthday">
    </div>
    <div>
        <div>Mark</div>
        <input name="mark">
    </div>
    <div>
        <button formaction="/student/create">Create</button>
        <button formaction="/student/update">Update</button>
        <a href="/student/delete">Delete</a>
        <a href="/student/index">Reset</a>
    </div>
</form>
<hr>
<h3>LIST</h3>
<table border="1" style="width:100%">
    <thead>
    <tr>
        <th>No.</th>
        <th>Username</th>
        <th>Password</th>
        <th>Enabled</th>
        <th>Fullname</th>
        <th>Photo</th>
        <th>Birthday</th>
        <th>Mark</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>No.</td>
        <td>Username</td>
        <td>Password</td>
        <td>Enabled</td>
        <td>Fullname</td>
        <td>Photo</td>
        <td>Birthday</td>
        <td>Mark</td>
        <td>
            <a href="">Edit</a> |
            <a href="">Delete</a>
        </td>
    </tr>
    </tbody>
</table>
</body>

</html>
