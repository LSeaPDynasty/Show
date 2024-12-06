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

@WebServlet("/searchBookServlet")
public class AdminSearchBookServlet extends HttpServlet {

    private Admin_Book_Dao adminBookDao;

    public void init() {
        adminBookDao = new Admin_Book_Dao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String isbn = request.getParameter("ISBN");
        System.out.println("ISBN: " + isbn);

        // 参数验证
        if (isbn == null || isbn.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Keyword cannot be null or empty");
            return;
        }

        try {
            Book book = adminBookDao.searchBookByISBN(isbn);
            if (book != null ) {
                request.setAttribute("books", book);
                System.out.println("查询成功");
                System.out.println(book.getISBN());
                System.out.println(book.getBookname());
                System.out.println(book.getPublish());
                System.out.println(book.getAuthor());
                System.out.println(book.getInformation());
                System.out.println(book.getQuantity());
                System.out.println(book.getPublishtime());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No books found for the given keyword");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
        }
    }

    // 如果需要处理 POST 请求，可以重写 doPost 方法
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理 POST 请求的逻辑
        doPost(request, response);
    }
}
