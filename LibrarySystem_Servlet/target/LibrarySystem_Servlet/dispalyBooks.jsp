<%--
  Created by IntelliJ IDEA.
  User: Colorful
  Date: 2024/11/30
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.servelt.librarysystem_servlet.entity.Book" %>
<%@ page import="com.servelt.librarysystem_servlet.comon.DatabaseUtil" %>
<html>
<head>
    <title>Books List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f4f4f9;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 3px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
<h1>Books List</h1>
<table>
    <tr>
        <th>ISBN</th>
        <th>Book Name</th>
        <th>Publisher</th>
        <th>Author</th>
        <th>Information</th>
        <th>Quantity</th>
        <th>Publish Time</th>
    </tr>
    <%
        List<Book> books = DatabaseUtil.getAllBooks();
        for (Book book : books) {
    %>
    <tr>
        <td><%= book.getISBN() %></td>
        <td><%= book.getBookname() %></td>
        <td><%= book.getPublish() %></td>
        <td><%= book.getAuthor() %></td>
        <td><%= book.getInformation() %></td>
        <td><%= book.getQuantity() %></td>
        <td><%= book.getPublishtime() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
