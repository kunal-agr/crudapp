package com.kagrawal.crudapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String URL;
    private static final String USER;
    private static final String PASS;

    static {
        URL = System.getenv("DB_URL");
        USER = System.getenv("DB_USER");
        PASS = System.getenv("DB_PASSWORD");

        if (URL == null || USER == null || PASS == null) {
            throw new RuntimeException(
                    "Database environment variables not set. " +
                            "Required: DB_URL, DB_USER, DB_PASSWORD"
            );
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static Connection fetchConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}