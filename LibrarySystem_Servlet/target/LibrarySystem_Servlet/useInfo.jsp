<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <title>个人中心</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 80%;
      margin: 50px auto;
      background: #fff;
      padding: 20px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
      text-align: center;
      color: #333;
    }
    p {
      margin-left: 45%;
      font-size: 16px;
      color: #555;
    }
    .error {
      margin-left: 42%;
      font-size: 20px;
      color:red;
    }
    .btn {
      display: inline-block;
      padding: 10px 20px;
      background: #007BFF;
      color: #fff;
      text-decoration: none;
      border-radius: 5px;
      margin-top: 5px;
      margin-left:46%;
    }
    .btn:hover {
      background: #0056b3;
    }
    /* Modal styles */
    .modal {
      display: none;
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0, 0, 0, 0.4);
    }
    .modal-content {
      background-color: #fefefe;
      margin: 15% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
      max-width: 500px;
    }
    .close {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
    }
    .close:hover,
    .close:focus {
      color: black;
      text-decoration: none;
      cursor: pointer;
    }
  </style>
</head>
<body id="edit">
<div class="container">
  <h1>个人中心</h1>
  <p>账号：${user}</p>
  <p>用户名：${name}</p>
  <p class="error">${error}</p>
  <button id="editBtn" class="btn">修改信息</button>
</div>

<!-- The Modal -->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <h2>修改信息</h2>
    <form action="${pageContext.request.contextPath}/updateProfile" method="post" onsubmit="return validateForm()">
      <label for="name">用户名:</label>
      <input type="text" id="name" name="name" value="${name}"><br><br>
      <input type="submit" value="保存">
    </form>
  </div>
</div>

<script>
  // 获取模态框
  var modal = document.getElementById("myModal");

  // 获取打开模态框的按钮
  var btn = document.getElementById("editBtn");

  // 获取关闭模态框的 <span> 元素
  var span = document.getElementsByClassName("close")[0];

  // 当用户点击按钮时，打开模态框
  btn.onclick = function () {
    modal.style.display = "block";
  }

  // 当用户点击 <span> (x) 时，关闭模态框
  span.onclick = function () {
    modal.style.display = "none";
  }

  // 当用户点击模态框外部时，关闭模态框
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }

  // 表单验证函数
  function validateForm() {
    var username = $("#name").val().trim();
    if (username === "") {
      alert("用户名不能为空");
      return false;
    }
    return true;
  }
</script>

</body>
</html>
