package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author guzim
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static String url = "jdbc:mysql://localhost:3306/mydb";
    private static String username = "root";
    private static String password = "1234";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
       
            connection = DriverManager.getConnection(url, username, password);
        

        return connection;
    }

}
