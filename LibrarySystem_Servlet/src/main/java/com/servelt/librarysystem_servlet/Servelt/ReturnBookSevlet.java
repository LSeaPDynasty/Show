package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.BorrowBookDao;
import com.servelt.librarysystem_servlet.Dao.ReturnBookDao;
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

@WebServlet("/returnBook")
public class ReturnBookSevlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ReturnBookDao rbd = new ReturnBookDao();
        boolean flag = false;
        try {
            flag = rbd.returnBook((String)req.getSession().getAttribute("user"),req.getParameter("BookName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Result<String>result = Result.success("返还成功");
        PrintWriter out = resp.getWriter();
        out.print(result.toJsonString());
        out.flush();

    }
}
