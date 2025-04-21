package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static  String URL = "jdbc:mysql://localhost:3306/BankingSystem";
    private static  String USER = "root"; // Replace with your MySQL username
    private static  String PASSWORD = "root"; // Replace with your MySQL password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database! ");
        }
    }	
}
