package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.entity.BorrowInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowBookDao {
    public boolean borrowBook(String bun,String bn,String ISBN,Integer Quantity) throws SQLException {

        String sql1 = "UPDATE books SET Quantity = ? WHERE ISBN = ?";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement(sql1);
        stmt1.setString(1, ""+(Quantity-1));
        stmt1.setString(2, ISBN);
        int rowsAffected = stmt1.executeUpdate();
        String sql2 = "insert into borrowinfo values(?,now(),?)";
        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        stmt2.setString(1, bun);
        stmt2.setString(2, bn);
        rowsAffected = stmt2.executeUpdate();
        return rowsAffected > 0;

    }
}
