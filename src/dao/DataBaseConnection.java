package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/service_auto", "root", "123456");
        return connection;
    }
}
