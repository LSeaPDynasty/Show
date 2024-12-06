<!-- bookDetail.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.servelt.librarysystem_servlet.entity.Book" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>书籍详情</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #ffffff, #000000); /* 渐变色背景 */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-size: 36px; /* 增大字体大小 */
        }
        .container {
            max-width: 600px;
            padding: 60px; /* 增大内边距 */
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 60px; /* 增大底部间距 */
            font-size: 56px; /* 增大标题字体大小 */
        }
        .details {
            display: grid;
            grid-template-columns: 1fr 2fr;
            gap: 30px; /* 增大间距 */
            margin-top: 30px; /* 增大顶部间距 */
            text-align: left;
        }
        .details label {
            font-weight: bold;
            color: #333;
            font-size: 36px; /* 增大标签字体大小 */
        }
        .details p {
            margin: 0;
            color: #555;
            font-size: 36px; /* 增大段落字体大小 */
            line-height: 1.5; /* 增大行高 */
        }
        .submit-button {
            display: inline-block;
            padding: 15px 30px;
            font-size: 36px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .submit-button:hover {
            background-color: #0056b3;
        }

        /* 模态框样式 */
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
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .modal-header, .modal-footer {
            text-align: center;
        }
        .modal-header h2 {
            margin: 0;
            font-size: 48px;
            color: #333;
        }
        .modal-body p {
            margin: 20px 0;
            font-size: 36px;
            color: #555;
        }
        .modal-footer button {
            padding: 10px 20px;
            font-size: 36px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .modal-footer button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>书籍详情</h1>
        <div class="details">
            <label>书名:</label>
            <p><%=request.getParameter("BookName") %></p>
            <label>书籍号:</label>
            <p><%=request.getParameter("ISBN") %></p>
            <label>作者:</label>
            <p><%=request.getParameter("Author") %></p>
            <label>出版社:</label>
            <p><%=request.getParameter("Publish") %></p>
            <label>信息:</label>
            <p><%=request.getParameter("information") %></p>
            <label>数量:</label>
            <p><%=request.getParameter("Quantity") %></p>
            <label>发布时间:</label>
            <p><%=request.getParameter("Publishtime") %></p>
        </div>
        <button class="submit-button" onclick="showConfirmModal()">借书</button>
    </div>

    <!-- 确认模态框 -->
    <div id="confirmModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>确认借书</h2>
            </div>
            <div class="modal-body">
                <p style="text-align: center;">确定借这本书吗？</p>
            </div>
            <div class="modal-footer">
                <button onclick="borrowBook()">确定</button>
                <button onclick="closeModal()">取消</button>
            </div>
        </div>
    </div>

    <script>
        function showConfirmModal() {
            document.getElementById('confirmModal').style.display = 'block';
        }

        function closeModal() {

            document.getElementById('confirmModal').style.display = 'none';

        }

        function borrowBook() {
            console.log("borrowBook");
            $.ajax({
                url: "${pageContext.request.contextPath}/borrowBook",
                type: 'POST',
                data: {
                    ISBN: "<%=request.getParameter("ISBN") %>"
                    ,Quantity: "<%=request.getParameter("Quantity") %>"
                    ,BookName: "<%=request.getParameter("BookName") %>"
                },
                success: function (response) {
                    console.log("Success:", response);
                    // 关闭确认模态框
                    document.getElementById('confirmModal').style.display = 'none';
                    // 跳转到其他页面
                    window.location.href = "${pageContext.request.contextPath}/home.jsp";
                },
            })
            document.getElementById('confirmModal').style.display = 'none';

        }


    </script>
</body>
</html>
