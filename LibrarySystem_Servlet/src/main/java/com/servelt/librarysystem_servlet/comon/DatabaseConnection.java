package com.servelt.librarysystem_servlet.comon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://47.121.188.252:3306/libraryinformationsystem";
    private static final String DB_USERNAME = "librarysystem";
    private static final String DB_PASSWORD = "tZNC7saAR4CCTdLM";

    /**
     * 获取数据库连接
     * @return 数据库连接对象
     * @throws SQLException 如果获取连接时发生错误
     */
    public Connection getConnection() throws SQLException {
        try {
            // 加载 MySQL 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC 驱动未找到！", e);
        }

        // 建立数据库连接
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

}
