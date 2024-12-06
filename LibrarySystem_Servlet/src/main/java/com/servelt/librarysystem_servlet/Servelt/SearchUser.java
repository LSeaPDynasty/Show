package com.servelt.librarysystem_servlet.Servelt;

import com.google.gson.Gson;
import com.servelt.librarysystem_servlet.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchuser")
public class SearchUser extends HttpServlet {
    Gson gson = new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("formtype");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String dburl = "jdbc:mysql://47.121.188.252:3306/libraryinformationsystem";
            final String dbuser = "librarysystem";
            final String dbpwd = "tZNC7saAR4CCTdLM";
            Connection cn = DriverManager.getConnection(dburl, dbuser, dbpwd);
            String sql;
            PreparedStatement st = null;
            switch (operation) {
                case "search":
                    System.out.println("searchuser service");
                    String query = req.getParameter("query");
                    if (!query.equals("all")) {
                        sql = "select * from user where username=? or name=?";
                        st = cn.prepareStatement(sql);
                        st.setString(1, query);
                        st.setString(2, query);
                    } else {
                        sql = "select * from user";
                        st = cn.prepareStatement(sql);
                    }
                    ResultSet rs = st.executeQuery();
                    List<User> userlist = new ArrayList<>();
                    System.out.println("    Resultset List:");
                    while (rs.next()) {
                        User user = new User();
                        user.setUsername(rs.getString("username"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        user.setAge(rs.getInt("age"));
                        user.setGrade(rs.getString("grade"));
                        user.setGender(rs.getString("gender"));
                        user.setContact(rs.getString("contact"));
                        userlist.add(user);
                        System.out.println("       " + user.getUsername());
                    }
                    resp.setContentType("text/html");
                    resp.setCharacterEncoding("UTF-8");
                    {
                        ServletOutputStream out = resp.getOutputStream();
                        out.write(("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<link rel=\"stylesheet\" href=\"style.css\">\n" + "\n" + "<head>\n" + "    <meta charset=\"GBK\">\n" + "    <title>admin-user</title>\n" + "</head>\n" + "<body>\n" + "<div>\n" + "\n" + "<div class=\"navbar\">\n" + "    <a href=\"admin-user.jsp\" class=\"active\">用户管理</a>\n" + "    <a href=\"admin-book.html\" >图书管理</a>\n" + "</div>\n" + "<form action=\"searchuser\" method=\"post\">\n" + "    <div class=\"search-container\">\n" + "        <input type=\"text\" name=\"query\" placeholder=\"搜索...\" value=\"all\">\n" + "        <input type=\"hidden\" name=\"formtype\" value=\"search\">\n" + "        <button type=\"submit\">搜索</button>\n" + "    </div>\n" + "</form>\n" + "\n" + "<div class=\"mybutton\">\n" + "    <button type=\"button\" onclick=\"adduserdialog.show()\">添加用户</button>\n" + "    <button type=\"button\" onclick=\"deleteuserdialog.show()\">删除用户</button>\n"  + "</div>\n" + "<table id=\"usertable\">\n" + "    <thead>\n" + "    <tr>\n" + "        <th>用户名</th>\n" + "        <th>姓名</th>\n" + "        <th>年龄</th>\n" + "        <th>年级</th>\n" + "        <th>性别</th>\n" + "        <th>联系方式</th>\n" + "        <th>密码</th>\n" + "        <th>操作</th>\n" + "    </tr>\n" + "    </thead>\n") .getBytes());
                        out.write(( "    <tbody>\n" .getBytes()));
                        for(User user : userlist) {
                            out.write("<tr>".getBytes());
                            out.write(("<td>"+user.getUsername()+"</td>").getBytes());
                            out.write(("<td>"+user.getName()+"</td>").getBytes());
                            out.write(("<td>"+user.getAge()+"</td>").getBytes());
                            out.write(("<td>"+user.getGrade()+"</td>").getBytes());
                            out.write(("<td>"+user.getGender()+"</td>").getBytes());
                            out.write(("<td>"+user.getContact()+"</td>").getBytes());
                            out.write(("<td>"+user.getPassword()+"</td>").getBytes());
                            out.write(("<td><button type=\"button\" onclick=\"editthisuser.show()\">编辑</button></td>").getBytes());
                            out.write("</td>".getBytes());
                        }
                        out.write((" </tbody>\n"+"</table>\n" +"    <dialog id=\"editthisuser\">\n" +"        <form action=\"searchuser\" method=\"post\">\n" +"            <h2>编辑</h2>\n" +"            <label for=\"username3\">用户名:</label>\n" +"            <input type=\"text\" id=\"username3\" name=\"username\" placeholder=\"用户名唯一且不可修改\" required><br><br>\n" +"\n" +"            <label for=\"password3\">密码:</label>\n" +"            <input type=\"text\" id=\"password3\" name=\"password\" required><br><br>\n" +"\n" +"            <label for=\"name3\">姓名:</label>\n" +"            <input type=\"text\" id=\"name3\" name=\"name\" required><br><br>\n" +"\n" +"            <label for=\"age3\">年龄:</label>\n" +"            <input type=\"number\" id=\"age3\" name=\"age\" required><br><br>\n" +"\n" +"            <label for=\"gender3\">性别:</label>\n" +"            <input type=\"text\" id=\"gender3\" name=\"gender\" required><br><br>\n" +"\n" +"            <label for=\"grade3\">年级:</label>\n" +"            <input type=\"text\" id=\"grade3\" name=\"grade\" required><br><br>\n" +"\n" +"            <label for=\"contact3\">联系方式:</label>\n" +"            <input type=\"text\" id=\"contact3\" name=\"contact\" required><br><br>\n" +"\n" +"            <input type=\"hidden\" name=\"formtype\" value=\"edituser\">\n" +"            <div class=\"dialog-button\">\n" +"                <button onclick=\"editthisuser.close()\">取消</button>\n" +"                <button type=\"submit\">修改</button>\n" +"            </div>\n" +"        </form>\n" +"\n" +"    </dialog>\n" +"    <dialog id=\"adduserdialog\">\n" +"    <form action=\"searchuser\" method=\"post\">\n" +"        <h2>添加用户</h2>\n" +"        <label for=\"username\">用户名:</label>\n" +"        <input type=\"text\" id=\"username\" name=\"username\" required><br><br>\n" +"\n" +"        <label for=\"password\">密码:</label>\n" +"        <input type=\"text\" id=\"password\" name=\"password\" required><br><br>\n" +"\n" +"        <label for=\"name\">姓名:</label>\n" +"        <input type=\"text\" id=\"name\" name=\"name\" required><br><br>\n" +"\n" +"        <label for=\"age\">年龄:</label>\n" +"        <input type=\"number\" id=\"age\" name=\"age\" required><br><br>\n" +"\n" +"        <label for=\"gender\">性别:</label>\n" +"        <input type=\"text\" id=\"gender\" name=\"gender\" required><br><br>\n" +"\n" +"        <label for=\"grade\">年级:</label>\n" +"        <input type=\"text\" id=\"grade\" name=\"grade\" required><br><br>\n" +"\n" +"        <label for=\"contact\">联系方式:</label>\n" +"        <input type=\"text\" id=\"contact\" name=\"contact\" required><br><br>\n" +"\n" +"        <input type=\"hidden\" name=\"formtype\" value=\"adduser\">\n" +"        <div class=\"dialog-button\">\n" +"            <button onclick=\"adduserdialog.close()\">取消</button>\n" +"            <button type=\"submit\">添加</button>\n" +"        </div>\n" +"    </form>\n" +"    </dialog>\n" +"    <dialog id=\"deleteuserdialog\">\n" +"    <form action=\"searchuser\" method=\"post\">\n" +"        <h2>删除用户</h2>\n" +"        <label for=\"username2\">用户名:</label>\n" +"        <input type=\"text\" id=\"username2\" name=\"username\" required><br><br>\n" +"\n" +"        <label for=\"password2\">密码:</label>\n" +"        <input type=\"text\" id=\"password2\" name=\"password\" required><br><br>\n" +"\n" +"        <input type=\"hidden\" name=\"formtype\" value=\"deleteuser\">\n" +"        <div class=\"dialog-button\">\n" +"            <button onclick=\"deleteuserdialog.close()\">取消</button>\n" +"            <button type=\"submit\">添加</button>\n" +"        </div>\n" +"    </form>\n" +"    </dialog>\n"+"</body>\n" + "</html>").getBytes());
                    }
                    rs.close();
                    System.out.println("searchuser done");
                    break;
                case "adduser":
                    sql = "insert into user (username,password,name,age,grade,gender,contact)values(?,?,?,?,?,?,?)";
                    st = cn.prepareStatement(sql);
                    st.setString(1, req.getParameter("username"));
                    st.setString(2, req.getParameter("password"));
                    st.setString(3, req.getParameter("name"));
                    st.setString(4, req.getParameter("age"));
                    st.setString(5, req.getParameter("grade"));
                    st.setString(6, req.getParameter("gender"));
                    st.setString(7, req.getParameter("contact"));
                    st.executeUpdate();
                    break;
                case "edituser":
                    sql = "update user set name=?,password=?,age=?,grade=?,gender=?,contact=? where username=?";
                    st = cn.prepareStatement(sql);
                    st.setString(1, req.getParameter("name"));
                    st.setString(2, req.getParameter("password"));
                    st.setString(3, req.getParameter("age"));
                    st.setString(4, req.getParameter("grade"));
                    st.setString(5, req.getParameter("gender"));
                    st.setString(6, req.getParameter("contact"));
                    st.setString(7, req.getParameter("username"));
                    st.executeUpdate();
                    break;
                case "deleteuser":
                    sql = "delete from user where username=?&&password=?";
                    st = cn.prepareStatement(sql);
                    st.setString(1, req.getParameter("username"));
                    st.setString(2, req.getParameter("password"));
                    st.executeUpdate();
                    break;

            }
            st.close();
            cn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}