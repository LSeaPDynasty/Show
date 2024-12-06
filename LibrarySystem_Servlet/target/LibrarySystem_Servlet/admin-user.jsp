<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="style.css">

<head>
    <meta charset="UTF-8">
    <title>admin-user</title>
</head>
<body>
<div>

<div class="navbar">
    <a href="admin-user.html" class="active">用户管理</a>
    <a href="adminServer.jsp" >图书管理</a>
</div>
<form action="searchuser" method="post">
    <div class="search-container">
        <input type="text" name="query" placeholder="搜索..." value="all">
        <input type="hidden" name="formtype" value="search">
        <button type="submit">搜索</button>
    </div>
</form>

<div class="mybutton">
    <button type="button" onclick="adduserdialog.show()">添加用户</button>
    <button type="button" onclick="deleteuserdialog.show()">删除用户</button>

</div>
<table id="usertable">
    <thead>
    <tr>
        <th>用户名</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>年级</th>
        <th>性别</th>
        <th>联系方式</th>
        <th>密码</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
    <dialog id="editthisuser">
        <form action="searchuser" method="post">
            <h2>编辑</h2>
            <label for="username3">用户名:</label>
            <input type="text" id="username3" name="username" placeholder="用户名唯一且不可修改" required><br><br>

            <label for="password3">密码:</label>
            <input type="text" id="password3" name="password" required><br><br>

            <label for="name3">姓名:</label>
            <input type="text" id="name3" name="name" required><br><br>

            <label for="age3">年龄:</label>
            <input type="number" id="age3" name="age" required><br><br>

            <label for="gender3">性别:</label>
            <input type="text" id="gender3" name="gender" required><br><br>

            <label for="grade3">年级:</label>
            <input type="text" id="grade3" name="grade" required><br><br>

            <label for="contact3">联系方式:</label>
            <input type="text" id="contact3" name="contact" required><br><br>

            <input type="hidden" name="formtype" value="edituser">
            <div class="dialog-button">
                <button onclick="editthisuser.close()">取消</button>
                <button type="submit">修改</button>
            </div>
        </form>

    </dialog>
    <dialog id="adduserdialog">
    <form action="searchuser" method="post">
        <h2>添加用户</h2>
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">密码:</label>
        <input type="text" id="password" name="password" required><br><br>

        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="age">年龄:</label>
        <input type="number" id="age" name="age" required><br><br>

        <label for="gender">性别:</label>
        <input type="text" id="gender" name="gender" required><br><br>

        <label for="grade">年级:</label>
        <input type="text" id="grade" name="grade" required><br><br>

        <label for="contact">联系方式:</label>
        <input type="text" id="contact" name="contact" required><br><br>

        <input type="hidden" name="formtype" value="adduser">
        <div class="dialog-button">
            <button onclick="adduserdialog.close()">取消</button>
            <button type="submit">添加</button>
        </div>
    </form>
    </dialog>
    <dialog id="deleteuserdialog">
    <form action="searchuser" method="post">
        <h2>删除用户</h2>
        <label for="username2">用户名:</label>
        <input type="text" id="username2" name="username" required><br><br>

        <label for="password2">密码:</label>
        <input type="text" id="password2" name="password" required><br><br>

        <input type="hidden" name="formtype" value="deleteuser">
        <div class="dialog-button">
            <button onclick="deleteuserdialog.close()">取消</button>
            <button type="submit">添加</button>
        </div>
    </form>
    </dialog>
</body>
</html>