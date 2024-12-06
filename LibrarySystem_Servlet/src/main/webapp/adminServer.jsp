<%--
  Created by IntelliJ IDEA.
  User: Colorful
  Date: 2024/11/30
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>图书管理系统 - 管理员页面</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>        body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 20px;
    }
    h1, h2, h3 {
        color: #333;
    }
    #book-form, #books-list {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        margin-bottom: 20px;
    }
    form {
        display: flex;
        flex-direction: column;
    }
    label {
        margin-top: 10px;
        font-weight: bold;
    }
    input[type="text"],
    input[type="number"],
    input[type="date"] {
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 15px;
        transition: background-color 0.3s;
    }
    button:hover {
        background-color: #45a049;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f2f2f2;
    }
    @media (max-width: 768px) {
        form {
            flex-direction: column;
        }
        input[type="text"],
        input[type="number"],
        input[type="date"] {
            width: 100%;
        }
    }
    </style>
</head>
<body>
<h1>图书管理系统 - 管理员页面</h1>
<div id="book-form">
    <!-- 添加书籍 -->
    <h2>添加新书</h2>
    <form action="${pageContext.request.contextPath}/addBookServlet" method="post" id="add-book-form">
        <label for="ISBN">国际标准书号</label>
        <input type="text" id="ISBN" name="ISBN" required>
        <label for="bookName">书名:</label>
        <input type="text" id="bookName" name="bookName" required>
        <label for="Publish">出版社:</label>
        <input type="text" id="Publish" name="Publish" required>
        <label for="Author">作者:</label>
        <input type="text" id="Author" name="Author" required>
        <label for="Information">简介:</label>
        <input type="text" id="Information" name="Information" required>
        <label for="Quantity">数量:</label>
        <input type="number" id="Quantity" name="Quantity" required>
        <label for="PublishTime">出版年份:</label>
        <input type="date" id="PublishTime" name="PublishTime" required>
        <button type="submit">添加</button>
    </form>

    <!-- 删除书籍 -->
    <form id="delete-book-form" action="${pageContext.request.contextPath}/deleteBookServlet" method="post">
        <h3>删除书籍</h3>
        <label>Book ID:</label>
        <input type="number" name="bookId" required>
        <button type="submit">删除</button>
    </form>

    <!-- 修改书籍 -->
    <form id="update-book-form" action="${pageContext.request.contextPath}/updateBookServlet" method="post">
        <h3>修改书籍</h3>
        <label>Book ID:</label>
        <input type="text" name="oldISBN" required>
        <label>New ISBN:</label>
        <input type="text" name="newISBN">
        <label>New BookName:</label>
        <input type="text" name="newBookName">
        <label>New Publish:</label>
        <input type="text" name="newPublish">
        <label>New Author:</label>
        <input type="text" name="newAuthor">
        <label>New Information:</label>
        <input type="text" name="newInformation">
        <label>New Quantity:</label>
        <input type="number" name="newQuantity">
        <label>New Year:</label>
        <input type="date" name="newPublishTime">
        <button type="submit">更新</button>
    </form>

    <!-- 查询书籍 -->
    <form id="search-book-form" action="SearchBookServlet" method="get">
        <h3>查询书籍</h3>
        <label>Search by ISBN:</label>
        <input type="text" name="ISBN">
        <button type="submit">查找</button>
    </form>
</div>



<script src="scripts.js"></script>
</body>
</html>