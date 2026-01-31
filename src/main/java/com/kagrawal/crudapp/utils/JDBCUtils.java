package com.kagrawal.crudapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String URL =
            System.getenv("DB_URL") != null
                    ? System.getenv("DB_URL")
                    : "jdbc:postgresql://" +
                    System.getenv("DB_HOST") + ":" +
                    System.getenv("DB_PORT") + "/" +
                    System.getenv("DB_NAME");


    private static final String USER =
            System.getenv("DB_USER") != null
                    ? System.getenv("DB_USER")
                    : "postgres";

    private static final String PASS =
            System.getenv("DB_PASSWORD") != null
                    ? System.getenv("DB_PASSWORD")
                    : "password";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found", e);
        }
    }

    public static Connection fetchConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}