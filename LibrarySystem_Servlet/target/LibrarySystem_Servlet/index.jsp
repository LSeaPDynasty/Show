<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  // 检查 session 中是否有用户登录信息
  String user = (String) session.getAttribute("user");
  if (user == null) {
    // 如果用户未登录，重定向到登录页面
    response.sendRedirect("login.jsp");
  } else {
    // 如果用户已登录，可以继续显示当前页面或跳转到主页
    // 这里选择跳转到主页
    response.sendRedirect("home.jsp");
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>图书信息系统</title>
</head>
<body>
</body>
</html>
