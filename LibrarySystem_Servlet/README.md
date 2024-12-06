
# 项目概述

这是一个基于 Vue.js 的图书信息系统，包含图书列表、借阅记录、归还图书、图书搜索和图书管理等功能。目前前端已经实现了基本的功能，但后端接口尚未完成。
请按照以下接口文档进行后端开发和前端修改。

## 接口文档

### 1. 获取用户信息

- **接口**: `GET /api/user`
- **请求参数**: 无
- **返回数据**:
  - user: 示例用户
  - isAdmin: true
- **对应函数**: `fetchUser()`

### 2. 获取图书列表

- **接口**: `GET /api/books`
- **请求参数**: 无
- **返回数据**:
  - id: 1
  - title: Vue.js实战
  - author: 尤雨溪
  - id: 2
  - title: 深入浅出Node.js
  - author: 朴灵
  - id: 3
  - title: JavaScript高级程序设计
  - author: 尼古拉斯·泽卡斯
- **对应函数**: `fetchBooks()`

### 3. 获取借阅记录

- **接口**: `GET /api/borrowed-books`
- **请求参数**: 无
- **返回数据**:
  - id: 1
  - title: Vue.js实战
  - author: 尤雨溪
  - id: 2
  - title: 深入浅出Node.js
  - author: 朴灵
- **对应函数**: `fetchBorrowedBooks()`

### 4. 归还图书

- **接口**: `POST /api/return-book/:id`
- **请求参数**:
  - id: 图书ID (路径参数)
- **请求体**: 无
- **返回数据**:
  - message: 图书已成功归还
- **对应函数**: `returnBook(id)`

### 5. 搜索图书

- **接口**: `GET /api/search-books?query=关键词`
- **请求参数**:
  - query: 关键词 (查询参数)
- **返回数据**:
  - id: 1
  - title: Vue.js实战
  - author: 尤雨溪
  - id: 2
  - title: 深入浅出Node.js
  - author: 朴灵
- **对应函数**: `searchBooks(query)`

### 6. 添加图书

- **接口**: `POST /api/add-book`
- **请求参数**: 无
- **请求体**:
  - title: 新书名称
  - author: 作者名称
- **返回数据**:
  - message: 图书添加成功
- **对应函数**: `addBook(title, author)`

### 7. 删除图书

- **接口**: `DELETE /api/delete-book/:id`
- **请求参数**:
  - id: 图书ID (路径参数)
- **请求体**: 无
- **返回数据**:
  - message: 图书删除成功
- **对应函数**: `deleteBook(id)`

### 8. 更新图书信息

- **接口**: `PUT /api/update-book/:id`
- **请求参数**:
  - id: 图书ID (路径参数)
- **请求体**:
  - title: 更新后的书名
  - author: 更新后的作者
- **返回数据**:
  - message: 图书信息更新成功
- **对应函数**: `updateBook(id, title, author)`
# Result 类的前后端数据交互说明

# 数据返回介绍

`Result` 类用于封装 API 响应结果，以便于前后端之间的数据交互。该类包含三个主要属性：`success`、`message` 和 `data`。这些属性分别表示操作是否成功、返回的消息以及返回的数据。

## 后端返回的数据格式

### 成功响应

- **success**: 表示操作是否成功，布尔值。
- **message**: 返回的消息，字符串。
- **data**: 返回的数据，可以是任意类型（例如对象、数组、字符串等）。

### 失败响应

- **success**: 表示操作是否成功，布尔值。
- **message**: 返回的消息，字符串。
- **data**: 返回的数据，可以是任意类型（例如对象、数组、字符串等）。

## 前端读取的数据格式

### 解析响应

前端在接收到后端返回的数据后，可以解析并使用这些数据。具体步骤如下：

1. **检查 `success` 属性**：
   - 如果 `success` 为 `true`，表示操作成功。
   - 如果 `success` 为 `false`，表示操作失败。

2. **读取 `message` 属性**：
   - 无论操作成功与否，`message` 属性都会包含后端返回的消息。

3. **读取 `data` 属性**：
   - 如果操作成功且 `data` 不为空，可以进一步处理 `data` 中的数据。
   - 如果操作失败或 `data` 为空，可以根据需要进行相应的处理。

## 具体应用场景

### 获取用户信息

- **接口**: `GET /api/user`
- **请求参数**: 无
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 用户信息对象

### 获取图书列表

- **接口**: `GET /api/books`
- **请求参数**: 无
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 图书列表数组

### 获取借阅记录

- **接口**: `GET /api/borrowed-books`
- **请求参数**: 无
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 借阅记录数组

### 归还图书

- **接口**: `POST /api/return-book/:id`
- **请求参数**:
  - id: 图书ID (路径参数)
- **请求体**: 无
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 无

### 搜索图书

- **接口**: `GET /api/search-books?query=关键词`
- **请求参数**:
  - query: 关键词 (查询参数)
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 搜索结果数组

### 添加图书

- **接口**: `POST /api/add-book`
- **请求参数**: 无
- **请求体**:
  - title: 新书名称
  - author: 作者名称
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 无

### 删除图书

- **接口**: `DELETE /api/delete-book/:id`
- **请求参数**:
  - id: 图书ID (路径参数)
- **请求体**: 无
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 无

### 更新图书信息

- **接口**: `PUT /api/update-book/:id`
- **请求参数**:
  - id: 图书ID (路径参数)
- **请求体**:
  - title: 更新后的书名
  - author: 更新后的作者
- **返回数据**:
  - success: 操作是否成功
  - message: 返回的消息
  - data: 无


## 注意事项

- 所有接口均需处理异常情况，并返回相应的错误信息。
- 确保接口的安全性和稳定性，避免未授权访问。

如有任何问题，请联系项目负责人。
