package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.IsAdminDao;
import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.comon.Result;
import com.servelt.librarysystem_servlet.entity.Admin;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminServer")
public class IsAdminServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String adminname = req.getParameter("user");
        IsAdminDao isAdminDao = new IsAdminDao();
        try {
            boolean isAdmin = isAdminDao.isAdmin(adminname);
            if (isAdmin) {

                resp.getWriter().print(Result.success("是管理员").toJsonString());
            } else {
                resp.getWriter().print(Result.error("不是管理员").toJsonString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
