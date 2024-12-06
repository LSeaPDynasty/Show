package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBookDao {
    public ArrayList<Book> searchBook(String bookName){
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books where BookName like ?||Author like ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,"%" + bookName + "%" );
            stmt.setString(2,"%" + bookName + "%" );
            ResultSet rs = stmt.executeQuery();
            try (rs) {
                while(rs.next()) {
                    books.add(new Book(rs.getString("ISBN"),rs.getString("BookName"),rs.getString("Publish"),rs.getString("Author"),rs.getString("information"),rs.getString("Quantity"),rs.getString("Publishtime")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}
