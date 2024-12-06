package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.entity.Book;
import com.servelt.librarysystem_servlet.entity.BorrowInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowDao {
    public ArrayList<BorrowInfo>  getBorrowInfo(String username) throws SQLException {
        ArrayList<BorrowInfo> borrowInfo = new ArrayList<>();
        String sql = "SELECT * FROM borrowinfo where BorrowUsername = ? ";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            try (rs) {
                while(rs.next()) {
                    borrowInfo.add(new BorrowInfo(rs.getString("BorrowUsername"),rs.getString("BorrowDate"),rs.getString("bookname")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return borrowInfo;

    }
}
