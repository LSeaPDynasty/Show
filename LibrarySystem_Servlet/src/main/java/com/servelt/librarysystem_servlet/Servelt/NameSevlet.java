package com.servelt.librarysystem_servlet.Servelt;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/getName")
public class NameSevlet extends HttpServlet {
    public void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {

        String name = request.getParameter("name");
        response.getWriter().println("Hello " + name);
    }
    public void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }
}
