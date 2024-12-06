package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.AdminDao;
import com.servelt.librarysystem_servlet.Dao.UserDao;
import com.servelt.librarysystem_servlet.comon.Result;
import com.servelt.librarysystem_servlet.entity.Admin;
import com.servelt.librarysystem_servlet.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet( value = "/adminlogin")
public class adminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        String password = req.getParameter("password");


        Admin admin = new Admin(password, username);
        AdminDao adminDao = new AdminDao();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Admin loginAdmin = adminDao.loginAdmin(admin);


            if (loginAdmin != null) {
                req.getSession().setAttribute("user", username);
                req.getSession().setAttribute("name",username );
                PrintWriter out = resp.getWriter();
                out.print(Result.success("登录成功").toJsonString());

                out.flush();
            } else {
                PrintWriter out = resp.getWriter();
                out.print(Result.error("用户名或密码错误").toJsonString());
                out.flush();
            }
        } catch (SQLException e) {
            PrintWriter out = resp.getWriter();
            out.print(Result.error("Database error: " + e.getMessage()).toJsonString());
            out.flush();
        }
    }
}
