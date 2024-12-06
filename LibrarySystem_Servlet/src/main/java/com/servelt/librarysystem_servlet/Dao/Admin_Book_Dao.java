package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_Book_Dao {
    private static final String INSERT_BOOK_SQL = "INSERT INTO books (ISBN,BookName,Publish,Author,information,Quantity,Publishtime) VALUES (?,?,?,?,?,?,?);";
    private static final String DELETE_BOOK_SQL = "DELETE FROM books WHERE ISBN = ?;";
    private static final String UPDATE_BOOK_SQL = "UPDATE books SET ISBN = ?,BookName = ?,Publish = ?, Author = ? ,information = ?,Quantity = ?,Publishtime = ? WHERE ISBN = ?;";
    private static final String SEARCH_BOOKS_BY_ISBN = "SELECT * FROM books WHERE ISBN LIKE ?;";
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books;";

    public void addBook(Book book) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK_SQL);
        stmt.setString(1, book.getISBN());
        stmt.setString(2, book.getBookname());
        stmt.setString(3, book.getPublish());
        stmt.setString(4, book.getAuthor());
        stmt.setString(5, book.getInformation());
        stmt.setString(6, book.getQuantity());
        stmt.setString(7, book.getPublishtime());
        stmt.executeUpdate();
    }

    public boolean deleteBook(int isdn) throws SQLException {
        // 数据库连接和删除语句
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_BOOK_SQL);
        stmt.setInt(1, isdn);
        stmt.executeUpdate();
        return true;
    }

    public boolean updateBook(String oldisbn, Book book) throws SQLException {
        // 数据库连接和更新语句
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_BOOK_SQL);
        stmt.setString(1, book.getISBN());
        stmt.setString(2, book.getBookname());
        stmt.setString(3, book.getPublish());
        stmt.setString(4, book.getAuthor());
        stmt.setString(5, book.getInformation());
        stmt.setString(6, book.getQuantity());
        stmt.setString(7, book.getPublishtime());
        stmt.setString(8, oldisbn);
        stmt.executeUpdate();
        return true;
    }

    public Book searchBookByISBN(String isbn) throws SQLException {
        // 数据库连接和查询语句
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SEARCH_BOOKS_BY_ISBN);
        stmt.setString(1, isbn);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Book book = new Book(rs.getString("ISBN"), rs.getString("BookName"), rs.getString("Publish"), rs.getString("Author"), rs.getString("information"), rs.getString("Quantity"), rs.getString("Publishtime"));
                return book;
            }
        }

        return null;
    }
}

