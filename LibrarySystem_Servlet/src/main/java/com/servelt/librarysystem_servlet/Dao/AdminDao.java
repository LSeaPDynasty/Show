package com.servelt.librarysystem_servlet.Dao;

import com.servelt.librarysystem_servlet.comon.DatabaseConnection;
import com.servelt.librarysystem_servlet.entity.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    public Admin loginAdmin(Admin admin) throws SQLException
    {
        Admin admin1=null;
        DatabaseConnection databaseConnection=new DatabaseConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM admin WHERE adminname = ? AND adminpwd = ?";
        try {
            preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, admin.getAdminname());
            preparedStatement.setString(2, admin.getAdminpassword());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admin1 = new Admin(resultSet.getString("adminname"),resultSet.getString("adminpwd"));

            }
            return admin1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return admin1;
    }
}
