<%@ page import="com.servelt.librarysystem_servlet.entity.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书信息系统 - 主页</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>

</head>
<%
    String name = (String) session.getAttribute("name");
%>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }
    .header {
        background-color: #333;
        color: #fff;
        padding: 10px 0;
        text-align: center;
        position: sticky;
        top: 0;
        z-index: 1000;
    }
    .header a {
        color: #fff;
        text-decoration: none;
        margin-left: 10px;
    }
    .container {
        width: 80%;
        margin: 20px auto;
        display: flex;
        justify-content: space-between;
    }
    .sidebar {
        width: 25%;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    .content {
        width: 70%;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    .footer {
        text-align: center;
        padding: 10px 0;
        background-color: #333;
        color: #fff;
        position: fixed;
        bottom: 0;
        width: 100%;
    }
    .book-item {
        list-style-type: none;
        background-color: #fff;
        border: 1px solid #e0e0e0;
        border-radius: 8px;
        padding: 15px;
        margin-bottom: 20px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .book-link {
        text-decoration: none;
        color: #333;
        display: block;
    }

    .book-details {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .book-title {
        font-size: 18px;
        font-weight: 600;
        margin: 0;
    }

    .book-author {
        font-size: 14px;
        color: #777;
        margin: 0;
    }
    .borrowed-book-link {
        text-decoration: none;
        color: #333;
        font-size: 16px;
        display: flex;
        align-items: center;
    }

    .borrowed-book-link i {
        margin-right: 8px;
        color: #409EFF;
    }

    .borrowed-book-link span {
        display: inline-block;
        margin-left: 8px;
    }
    #borrow{
        max-height: 350px; /* 设置最大高度 */
        overflow-y: auto; /* 启用垂直滚动 */
    }
    #search{
        max-height: 200px; /* 设置最大高度 */
        overflow-y: auto; /* 启用垂直滚动 */
    }
    .book-info{
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .return-button {
        background-color: #409EFF;
        border-color: #409EFF;
        font-size: 14px;
        padding: 8px 16px;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s, border-color 0.3s;
    }

    .return-button:hover {
        background-color: #66b1ff;
        border-color: #66b1ff;
    }
</style>
<body>
<div id="app">
    <div class="header">
        <h1>图书信息系统</h1>
        <p><a href="useInfo.jsp" style="text-decoration: none" target="_blank">您好，${name}！</a></p>
        <a href="login.jsp">退出登录</a>
    </div>
    <div class="container">
        <div class="sidebar">
            <el-menu :default-active="activeIndex" @select="handleSelect">
                <el-menu-item index="1">
                    <i class="el-icon-document"></i>
                    <span slot="title">图书列表</span>
                </el-menu-item>
                <el-menu-item index="2">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">借阅记录</span>
                </el-menu-item>

                <el-menu-item index="4">
                    <i class="el-icon-search"></i>
                    <span slot="title">图书搜索</span>
                </el-menu-item>

                <el-menu-item v-if="isAdmin" index="6">
                    <i class="el-icon-setting"></i>
                    <span slot="title">管理员设置</span>
                </el-menu-item>
            </el-menu>
        </div>
        <div class="content">
            <component :is="currentComponent"></component>
        </div>
    </div>
    <div class="footer">
        <p>&copy; 2024 图书信息系统. All rights reserved.</p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
        var user = '<%= session.getAttribute("user") %>';


// 获取用户信息
    // 需要一个函数来从后端获取当前用户的详细信息
    // 示例：fetchUser()
    function fetchUser() {
        // 发送请求获取用户信息
    }

    // 获取图书列表
    // 需要一个函数来从后端获取所有图书的列表
    // 示例：fetchBooks()
    function fetchBooks(callback) {
        console.log("fetchbooks");
        $.ajax({
            url: "${pageContext.request.contextPath}/bookList",
            type: 'POST',
            data: '',
            success: function (response) {
                console.log(response); // 查看返回的数据
                if (response.data) {
                    callback(response.data);
                } else {
                    alert("没有数据");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("请求过程中发生错误，请稍后再试。");
                console.error(textStatus, errorThrown);
            }
        });
    }



    // 获取借阅记录
    // 需要一个函数来从后端获取用户的借阅记录
    // 示例：fetchBorrowedBooks()
    function fetchBorrowedBooks(callback) {
        // 发送请求获取借阅记录
        // 模拟数据
        console.log("fetchborrowedbooks");
        $.ajax({
            url: "${pageContext.request.contextPath}/borrowList",
            type: 'POST',
            data: '',
            success: function (response) {
                console.log(response); // 查看返回的数据
                if (response.data) {
                    callback(response.data);
                } else {
                    alert("没有数据");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("请求过程中发生错误，请稍后再试。");
                console.error(textStatus, errorThrown);
            }
        });
    }

        function fetchAdmin() {

            return new Promise((resolve, reject) => {
                var formData = {
                    user: user,

                };
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/adminServer",
                    data:formData,
                    dataType: "json",
                    success: function (response) {

                        if (response.success) {
                            resolve(true);
                        } else {
                            resolve(false);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.error(textStatus, errorThrown);
                        reject(false);
                    }
                });
            });
        }

    // 归还图书
    // 需要一个函数来向后端发送请求，标记某本图书已归还
    // 示例：returnBook(bookId)
    <%--function returnBook(borrow) {--%>
    <%--// 发送请求归还图书--%>
    <%--$.ajax({--%>
    <%--    url: "${pageContext.request.contextPath}/returnBook",--%>
    <%--    type: 'POST',--%>
    <%--    data: {--%>
    <%--        BookName: borrow.BookName,--%>
    <%--        ISBN: borrow.ISBN,--%>
    <%--        Quantity:borrow.Quantity--%>
    <%--    },--%>
    <%--    success: function (response) {--%>
    <%--        console.log(response); // 查看返回的数据--%>
    <%--        if (response.success) {--%>
    <%--            // 页面跳转--%>
    <%--            window.location.href = `home.jsp`;--%>
    <%--        } else {--%>
    <%--            alert("归还失败，请稍后再试。");--%>
    <%--        }--%>
    <%--    },--%>
    <%--    error: function (jqXHR, textStatus, errorThrown) {--%>
    <%--        alert("请求过程中发生错误，请稍后再试。");--%>
    <%--        console.error(textStatus, errorThrown);--%>
    <%--    }--%>
    <%--});--%>



    // 搜索图书
    // 需要一个函数来根据关键词搜索图书，并从后端获取结果
    // 示例：searchBooks(query)
    function searchBooks(query,callback) {
        console.log("searchbooks");
        $.ajax({
            url: "${pageContext.request.contextPath}/searchList",
            type: 'POST',
            data: {
                bookName: query
            },
            success: function (response) {
                console.log(response); // 查看返回的数据
                if (response.data) {
                    callback(response.data);
                } else {
                    alert("没有数据");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("请求过程中发生错误，请稍后再试。");
                console.error(textStatus, errorThrown);
            }
        });
        // 发送请求搜索图书
        // 模拟数据
        return [
            { id: 1, title: 'Vue.js实战', author: '尤雨溪' },
            { id: 2, title: '深入浅出Node.js', author: '朴灵' }
        ].filter(book => book.title.includes(query) || book.author.includes(query));
    }

    // 添加图书
    // 需要一个函数来向后端发送请求，添加新的图书
    // 示例：addBook(bookData)
    function addBook(bookData) {
        // 发送请求添加图书
        console.log(`添加图书: ${JSON.stringify(bookData)}`);
    }

    // 删除图书
    // 需要一个函数来向后端发送请求，删除指定的图书
    // 示例：deleteBook(bookId)
    function deleteBook(bookId) {
        // 发送请求删除图书
        console.log(`删除图书 ID: ${bookId}`);
    }

    // Vue 组件定义
    Vue.component('book-list', {
        template: `        <div>
            <h2>最新图书推荐</h2>
            <ul>
   <li v-for="book in books" :key="book.id" class="book-item">
    <a :href="'bookDetail.jsp?ISBN=' + encodeURIComponent(book.ISBN) + '&BookName=' + encodeURIComponent(book.BookName) + '&Publish=' + encodeURIComponent(book.Publish) + '&Author=' + encodeURIComponent(book.Author) + '&information=' + encodeURIComponent(book.information) + '&Quantity=' + encodeURIComponent(book.Quantity) + '&Publishtime=' + encodeURIComponent(book.Publishtime)" class="book-link" target="_blank">
        <div class="book-details">
            <div class="book-title">{{ book.BookName }}</div>
            <div class="book-author">{{ book.Author }}</div>
        </div>
    </a>
</li>

           </ul>

        </div>
    `,
        data() {
            return {
                books: []
            };
        },
        created() {
            // 使用回调函数
            fetchBooks((data) => {
                this.books = data;
            });
        }
    });

    Vue.component('borrowed-books', {
        template: `        <div class="borrowed-books" id="borrow">
            <h2>借阅记录</h2>
            <ul>
                <li v-for="borrow in borrows" :key="borrow.id" class="book-item">
                    <a href="#" class="book-link">
                        <div class="book-details">
                            <div class="book-title">{{ borrow.bookname }}</div>
                            <div class="book-author">{{ formatDate(borrow.BorrowDate) }}</div>

                            <el-button type="primary" class="return-button" @click="returnBook(borrow)">归还</el-button>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    `,
        data() {
            return {
                borrows: []
            };
        },
        methods: {
            formatDate(date) {
                return new Date(date).toLocaleDateString();
            },
            returnBook(borrow) {
                console.log("归还图书:", borrow); // 打印请求参数
                $.ajax({
                    url: "${pageContext.request.contextPath}/returnBook",
                    type: 'POST',
                    data: {
                        BookName: borrow.bookname
                    },
                    success: function (response) {
                        console.log(response); // 查看返回的数据
                        if (response.success) {
                            // 页面跳转
                            window.location.href = `home.jsp`;
                        } else {
                            alert("归还失败，请稍后再试。");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("请求过程中发生错误，请稍后再试。");
                        console.error(textStatus, errorThrown);
                    }
                });
            }
        },
        created() {
            // 使用回调函数
            fetchBorrowedBooks((data) => {
                this.borrows = data;
            });
        },
    });

    Vue.component('return-books', {
        template: `            <div>
                    <h2>归还图书</h2>
                    <ul>
                        <li v-for="book in books" :key="book.id">
                            <a href="#">
                                <i class="el-icon-back"></i> {{ book.title }} - {{ book.author }}
                            </a>
                            <el-button type="primary" @click="ReturnBook(book.id)">归还</el-button>
                        </li>
                    </ul>
                </div>
            `,
        data() {
            return {
                books: []
            };
        },
        methods: {
            ReturnBook(bookId) {
                // 调用 returnBook 函数归还图书
                returnBook(bookId);
            }
        },
        created() {
            // 使用模拟数据
            this.books = fetchBorrowedBooks();
            // 在组件创建时调用 fetchBorrowedBooks 函数获取借阅记录
            // this.fetchBorrowedBooks();
        }
    });

Vue.component('book-search', {
    template: `
        <div >
            <h2>图书搜索</h2>
            <el-input v-model="searchQuery" placeholder="请输入图书名称或作者" @input="searchBooks"></el-input>
            <ul id="search">
               <li v-for="book in searchResults" :key="book.id" class="book-item">
    <a :href="'bookDetail.jsp?ISBN=' + encodeURIComponent(book.ISBN) + '&BookName=' + encodeURIComponent(book.BookName) + '&Publish=' + encodeURIComponent(book.Publish) + '&Author=' + encodeURIComponent(book.Author) + '&information=' + encodeURIComponent(book.information) + '&Quantity=' + encodeURIComponent(book.Quantity) + '&Publishtime=' + encodeURIComponent(book.Publishtime)" class="book-link" target="_blank">
        <i class="el-icon-search"></i>
        <span class="book-info">
            <span class="book-title">{{ book.BookName }}</span>
            <span class="book-author">{{ book.Author }}</span>
        </span>
    </a>
</li>

            </ul>
        </div>
    `,
    data() {
        return {
            searchQuery: '',
            searchResults: []
        };
    },
    methods: {
        searchBooks() {
            if (this.searchQuery.trim() === '') {
                this.searchResults = [];
                return;
            }
            searchBooks(this.searchQuery, (data) => {
                this.searchResults = data;
            });
        }
    }
});


    Vue.component('book-management', {
        template: `            <div>
                    <h2>图书管理</h2>
                    <el-button type="primary" @click="showAddBookDialog = true">添加图书</el-button>
                    <el-dialog title="添加图书" :visible.sync="showAddBookDialog">
                        <el-form :model="newBook">
                            <el-form-item label="书名" prop="title">
                                <el-input v-model="newBook.title"></el-input>
                            </el-form-item>
                            <el-form-item label="作者" prop="author">
                                <el-input v-model="newBook.author"></el-input>
                            </el-form-item>
                            <el-form-item label="出版社" prop="publisher">
                                <el-input v-model="newBook.publisher"></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="showAddBookDialog = false">取消</el-button>
                            <el-button type="primary" @click="addBook">确定</el-button>
                        </div>
                    </el-dialog>
                    <ul>
                        <li v-for="book in books" :key="book.id">
                            <a href="#">
                                <i class="el-icon-document"></i> {{ book.title }} - {{ book.author }}
                            </a>
                            <el-button type="danger" @click="deleteBook(book.id)">删除</el-button>
                        </li>
                    </ul>
                </div>
            `,
        data() {
            return {
                showAddBookDialog: false,
                newBook: {
                    title: '',
                    author: '',
                    publisher: ''
                },
                books: []
            };
        },
        methods: {
            addBook() {
                // 调用 addBook 函数添加图书
                addBook(this.newBook);
                this.showAddBookDialog = false;
            },
            deleteBook(id) {
                // 调用 deleteBook 函数删除图书
                deleteBook(id);
            }
        },
        created() {
            // 使用模拟数据
            this.books = fetchBooks();
            // 在组件创建时调用 fetchBooks 函数获取图书列表
            // this.fetchBooks();
        }
    });

    Vue.component('admin-settings', {
        template: `            <div>
                    <h2>管理员设置</h2>
                    <p>这里可以进行管理员相关的设置。</p>
                </div>
            `
    });

    new Vue({
        el: '#app',
        data: {
            user: '示例用户',
            isAdmin: fetchAdmin().success,
            activeIndex: '1',
            currentComponent: 'book-list'
        },
        methods: {
            handleSelect(key) {
                switch (key) {
                    case '1':
                        this.currentComponent = 'book-list';
                        break;
                    case '2':
                        this.currentComponent = 'borrowed-books';
                        break;
                    case '3':
                        this.currentComponent = 'return-books';
                        break;
                    case '4':
                        this.currentComponent = 'book-search';
                        break;
                    case '5':
                        this.currentComponent = 'book-management';
                        break;
                    case '6':
                        window.location.href = 'admin-user.jsp';
                        break;
                }
            }
        },
        created: async function(){
            // 使用模拟数据
            this.user = ${name};
            this.isAdmin = await fetchAdmin();

        }
    })


</script>
</body>
</html>
