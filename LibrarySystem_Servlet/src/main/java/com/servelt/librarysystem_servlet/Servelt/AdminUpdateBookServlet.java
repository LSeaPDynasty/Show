package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.Admin_Book_Dao;
import com.servelt.librarysystem_servlet.entity.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateBookServlet")
public class AdminUpdateBookServlet extends HttpServlet {

    private Admin_Book_Dao adminBookDao;

    public void init() {
        adminBookDao = new Admin_Book_Dao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String oldisdn=request.getParameter("oldISBN");
        String isdn=request.getParameter("newISBN");
        String bookName=request.getParameter("newBookName");
        String publish=request.getParameter("newPublish");
        String author=request.getParameter("newAuthor");
        String information=request.getParameter("newInformation");
        String quantity= request.getParameter("newQuantity");
        String publishTime=request.getParameter("newPublishTime");


        // 创建书籍对象
        Book newbook = new Book(isdn,bookName,publish,author,information,quantity,publishTime);

        try {
            boolean isUpdated = adminBookDao.updateBook(oldisdn,newbook);
            if (isUpdated) {
                response.getWriter().println("书籍更新成功");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "书籍未找到");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
        }
    }

    // 如果需要处理 GET 请求，可以重写 doGet 方法
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理 GET 请求的逻辑
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported");
    }
}

