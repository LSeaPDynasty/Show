package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.Admin_Book_Dao;
import com.servelt.librarysystem_servlet.entity.Book;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addBookServlet")
public class AddBookServlet extends HttpServlet {

    private Admin_Book_Dao adminBookDao;
    public void init() {
        adminBookDao = new Admin_Book_Dao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isdn=request.getParameter("ISBN");
        String bookName=request.getParameter("bookName");
        String publish=request.getParameter("Publish");
        String author=request.getParameter("Author");
        String information=request.getParameter("Information");
        String quantity= request.getParameter("Quantity");
        String publishTime=request.getParameter("PublishTime");

        Book book=new Book(isdn,bookName,publish,author,information,quantity,publishTime);

        try {

            // 参数验证
            System.out.println(isdn);
            if (isdn == null || isdn.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ISBN cannot be null or empty");
                return;
            }
            if (bookName == null || bookName.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book Name cannot be null or empty");
                return;
            }
            if (quantity == null ) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity cannot be null or empty");
                return;
            }
            if (publishTime == null || publishTime.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Publish Time cannot be null or empty");
                return;
            }

            try {
                adminBookDao.addBook(book);
                System.out.println("添加成功");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
            } catch (NumberFormatException e) {
                // 捕获数量转换异常
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Quantity format");
            } catch (Exception e) {
                // 捕获其他异常
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
