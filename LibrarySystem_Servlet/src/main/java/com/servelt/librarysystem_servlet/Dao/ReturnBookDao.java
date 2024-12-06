package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnBookDao {
    public boolean returnBook(String un,String bookName) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        String sql1 = "select * from books where bookname=?";
        PreparedStatement stmt1 = conn.prepareStatement(sql1);
        stmt1.setString(1, bookName);
        ResultSet rs = stmt1.executeQuery();
        rs.next();
        String ISBN = rs.getString("ISBN");
        int Quantity = Integer.parseInt(rs.getString("Quantity"));

        String sql2 = "UPDATE books SET Quantity = ? WHERE ISBN = ?";

        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        stmt2.setString(1, ""+(Quantity+1));
        stmt2.setString(2, ISBN);
        stmt2.executeUpdate();
        String sql3 = "delete from borrowinfo where bookname=? and BorrowUsername = ?";
        PreparedStatement stmt3 = conn.prepareStatement(sql3);
        stmt3.setString(1, bookName);
        stmt3.setString(2, un);
        int rowsAffected=stmt3.executeUpdate();
        return rowsAffected!=0;

    }
}
