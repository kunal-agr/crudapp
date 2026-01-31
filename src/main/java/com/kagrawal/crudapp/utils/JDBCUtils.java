package com.kagrawal.crudapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASSWORD");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found", e);
        }
    }

    public static Connection fetchConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASSWORD");

        System.out.println("DB_URL: " + (url == null ? "NULL" : "SET"));
        System.out.println("DB_USER: " + (user == null ? "NULL" : "SET"));
        System.out.println("DB_PASSWORD: " + (pass == null ? "NULL" : "SET"));

        if (url == null || user == null || pass == null) {
            throw new RuntimeException("Database environment variables are missing");
        }
        return DriverManager.getConnection(url, user, pass);
    }
}
