<%--
  Created by IntelliJ IDEA.
  User: l
  Date: 2024/11/9
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .login-form h2 {
            margin-top: 0;
            margin-bottom: 20px;
        }
        .login-form label {
            display: block;
            margin-bottom: 5px;
            text-align: left;
        }
        .login-form input[type="text"],
        .login-form input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-form button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }
        .login-form button:hover {
            background-color: #0056b3;
        }
        .login-form .register-link {
            margin-top: 10px;
            text-align: center;
        }
        .login-form .register-link a {
            color: #007BFF;
            text-decoration: none;
        }
        .login-form .register-link a:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        function submitForm() {
            var username = $("#username").val().trim();
            var password = $("#password").val().trim();

            if (username === "" || password === "") {
                alert("请输入用户名和密码！");
                return false;
            }

            var formData = {
                username: username,
                password: password
            };

            console.log("Form Data:", formData); // 调试信息

            $.ajax({
                url: "${pageContext.request.contextPath}/adminlogin",
                type: 'POST',
                data: formData,
                success: function (response) {
                    handleResponse(response);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("请求过程中发生错误，请稍后再试。");
                }
            });

            return false; // 阻止表单默认提交
        }

        function handleResponse(response) {
            if (response.success) {
                window.location.href = "${pageContext.request.contextPath}/home.jsp"; // 直接跳转到 home.jsp
            } else {
                alert(response.message); // 弹出错误提示
            }
        }
    </script>
</head>
<body>
<div class="login-form">
    <h2>登录</h2>
    <form action="${pageContext.request.contextPath}/adminlogin" method="post" onsubmit="return submitForm()">
        <label>
            <input type="text" id="username" name="username" placeholder="用户名">
        </label><br>
        <label>
            <input type="password" id="password" name="password" placeholder="密码">
        </label><br>
        <button type="submit">登录</button>
    </form>

</div>
</body>
</html>
