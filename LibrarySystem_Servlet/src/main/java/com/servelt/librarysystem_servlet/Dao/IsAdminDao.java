package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsAdminDao {

    public boolean isAdmin(String adminname) throws SQLException {
        String sql = "SELECT * FROM admin WHERE adminname = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection conn = databaseConnection.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, adminname);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
