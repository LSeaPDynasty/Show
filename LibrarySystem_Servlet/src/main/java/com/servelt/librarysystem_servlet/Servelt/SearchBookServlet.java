package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.BorrowDao;
import com.servelt.librarysystem_servlet.Dao.SearchBookDao;
import com.servelt.librarysystem_servlet.comon.Result;
import com.servelt.librarysystem_servlet.entity.Book;
import com.servelt.librarysystem_servlet.entity.BorrowInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/searchList")
public class SearchBookServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        SearchBookDao searchbookdao = new SearchBookDao();
        ArrayList<Book> searchbook = null;
        searchbook = searchbookdao.searchBook(req.getParameter("bookName"));
        Result<ArrayList<Book>> result = Result.success(searchbook);
        PrintWriter out = resp.getWriter();
        out.print(result.toJsonString());
        out.flush();
    }

}
