package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.BookDao;
import com.servelt.librarysystem_servlet.Dao.BorrowDao;
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

@WebServlet("/borrowList")
public class BorrowListServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BorrowDao borrowDao = new BorrowDao();
        ArrayList<BorrowInfo> borrowInfo = null;
        try {
            borrowInfo = borrowDao.getBorrowInfo((String)req.getSession().getAttribute("user"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 打印书籍列表，确保有数据
        Result<ArrayList<BorrowInfo>> result = Result.success(borrowInfo);
        PrintWriter out = resp.getWriter();
        out.print(result.toJsonString());
        out.flush();
    }


}
