package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.BookDao;
import com.servelt.librarysystem_servlet.comon.Result;
import com.servelt.librarysystem_servlet.entity.Book;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "bookListServlet", value = "/bookList")
public class BookListServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BookDao bookDao = new BookDao();
        ArrayList<Book> books = bookDao.getBook();

        Result<ArrayList<Book>> result = Result.success(books);
        PrintWriter out = resp.getWriter();
        out.print(result.toJsonString());
        out.flush();
    }
}
