package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.BookDao;
import com.servelt.librarysystem_servlet.Dao.BorrowBookDao;
import com.servelt.librarysystem_servlet.comon.Result;
import com.servelt.librarysystem_servlet.entity.Book;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/borrowBook")
public class BorrowBookServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BorrowBookDao bbd = new BorrowBookDao();
        boolean flag = false;
        try {
            flag = bbd.borrowBook((String)req.getSession().getAttribute("user"),req.getParameter("BookName"),req.getParameter("ISBN"),Integer.parseInt(req.getParameter("Quantity")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("home.jsp");

    }

}
