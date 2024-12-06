package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean registerUser(User user) throws SQLException
    {
        String sql = "INSERT INTO user (username, password,name) VALUES (?, ?, ?)";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getUsername());
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    public User loginUser(User user) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        User foundUser = null;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            try (rs) {
                if (rs.next()) {

                    foundUser = new User(rs.getString("username"), rs.getString("password"),rs.getString("name"));
                }
            }
        }

        return foundUser;
    }
    public boolean isUsernameExists(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection conn = databaseConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public String getName(String username) throws SQLException {
        String sql = "SELECT name FROM user WHERE username = ? ";
        DatabaseConnection databaseConnection = new DatabaseConnection();
       Connection conn = databaseConnection.getConnection();
        String name = null;
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return name;
        }
    public boolean updateUserInfo(String username,String name) throws SQLException {
        String sql = "UPDATE user SET name = ? WHERE username = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, username);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }




}

