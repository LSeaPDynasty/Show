package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.UserDao;
import com.servelt.librarysystem_servlet.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        System.out.println("RegisterServlet: " + username);
        System.out.println("RegisterServlet: " + password);
        System.out.println("RegisterServlet: " + confirmPassword);

        // 检查密码是否匹配
        if (!password.equals(confirmPassword)) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('密码不匹配'); history.back();</script>");
            return;
        }

        User user = new User(username, password);
        UserDao userDao = new UserDao();

        try {
            if (userDao.isUsernameExists(username)) {
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.print("<script>alert('用户名已存在'); history.back();</script>");
                return;
            }

            boolean registerUser = userDao.registerUser(user);
            if (registerUser) {
                // 注册成功，重定向到 login.jsp
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            } else {
                // 注册失败
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.print("<script>alert('注册失败'); history.back();</script>");
            }
        } catch (SQLException e) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('数据库错误: " + e.getMessage() + "'); history.back();</script>");
        }
    }


}
