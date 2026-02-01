package com.kagrawal.crudapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static boolean driverLoaded = false;

    private static void loadDriver() {
        if (!driverLoaded) {
            try {
                Class.forName("org.postgresql.Driver");
                driverLoaded = true;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
            }
        }
    }

    public static Connection fetchConnection() throws SQLException {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASSWORD");

        if (url == null || user == null || pass == null) {
            throw new RuntimeException(
                    "Missing DB env vars. Found -> " +
                            "DB_URL=" + url + ", DB_USER=" + user
            );
        }

        loadDriver();
        return DriverManager.getConnection(url, user, pass);
    }
}