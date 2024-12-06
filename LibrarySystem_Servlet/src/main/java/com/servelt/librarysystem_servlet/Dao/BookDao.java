package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.comon.Result;
import com.servelt.librarysystem_servlet.entity.Book;
import com.servelt.librarysystem_servlet.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDao {
    public ArrayList<Book> getBook(){
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            try (rs) {
                int i=0;
                while(rs.next()&&i<4) {
                    i++;
                   books.add(new Book(rs.getString("ISBN"),rs.getString("BookName"),rs.getString("Publish"),rs.getString("Author"),rs.getString("information"),rs.getString("Quantity"),rs.getString("Publishtime")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public ArrayList<Book> getAllBook(){
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
