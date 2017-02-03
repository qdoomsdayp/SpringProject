package com.stage.Controller;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by wital on 24.01.2017.
 */
public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static Connection con;

    public Connect() throws SQLException, ClassNotFoundException {

    }

    public Connection getConnect() throws SQLException, ClassNotFoundException {
        if (con != null)
            return con;

        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.registerDriver(new FabricMySQLDriver());
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return con;

    }
}
