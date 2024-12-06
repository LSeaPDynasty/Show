package com.servelt.librarysystem_servlet.comon;

import com.servelt.librarysystem_servlet.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    public static List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books");
        ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getString("ISBN"),rs.getString("BookName"),rs.getString("Publish"),rs.getString("Author"),rs.getString("information"),rs.getString("Quantity"),rs.getString("Publishtime"));
                books.add(book);
            }
        return books;
    }
}
