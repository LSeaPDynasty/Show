package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.UserDao;
import com.servelt.librarysystem_servlet.comon.Result;
import com.servelt.librarysystem_servlet.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(username, password);
        UserDao userDao = new UserDao();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            User loginUser = userDao.loginUser(user);
            String name = userDao.getName(username);

            if (loginUser != null) {
                req.getSession().setAttribute("user", username);
                req.getSession().setAttribute("name",name );
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
