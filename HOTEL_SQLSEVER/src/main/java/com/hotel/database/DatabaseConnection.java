package com.hotel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // sử dụng mysql
//    private static final String URL = "jdbc:mysql://localhost:3306/BOOK_HOTEL";
//
//    private static final String USER = "root";
//    private static final String PASSWORD = "311127";
//    
    
    // sử dụng sqlsever
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;"
      + "databaseName=BOOK_HOTEL;"
      + "encrypt=false;"
      + "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    
   
    static {
        try {
            
            System.out.println("JDBC Driver loaded (nếu cần).");
        } catch (Exception e) {
            System.err.println("Không thể load JDBC Driver: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.err.println("Kết nối CSDL thất bại: " + e.getMessage());
            return null;
        }
    }
}
