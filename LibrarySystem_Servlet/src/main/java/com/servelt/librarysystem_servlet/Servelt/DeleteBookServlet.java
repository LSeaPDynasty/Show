package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.Admin_Book_Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteBookServlet")
public class DeleteBookServlet extends HttpServlet {

    private Admin_Book_Dao adminBookDao;

    public void init() {
        adminBookDao = new Admin_Book_Dao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");

        // 参数验证
        if (bookIdStr == null || bookIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ID cannot be null or empty");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdStr);
            if (bookId <= 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ID must be a positive number");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Book ID format");
            return;
        }


        boolean isDeleted = false;
        try {
            isDeleted = adminBookDao.deleteBook(bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isDeleted) {
            System.out.println("书籍删除成功");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}