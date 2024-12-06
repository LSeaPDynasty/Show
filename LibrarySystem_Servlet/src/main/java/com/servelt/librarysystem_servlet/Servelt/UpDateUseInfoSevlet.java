package com.servelt.librarysystem_servlet.Servelt;

import com.servelt.librarysystem_servlet.Dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.sql.SQLException;

@WebServlet("/updateProfile")
public class UpDateUseInfoSevlet extends HttpServlet {
    public void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException
    {
        String name = request.getParameter("name");
        String username = (String)request.getSession().getAttribute("user");
        try {
            boolean result = new UserDao().updateUserInfo(username, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("name",name);
        response.sendRedirect("home.jsp");
    }


}
