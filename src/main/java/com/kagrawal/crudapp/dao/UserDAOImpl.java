package com.kagrawal.crudapp.dao;

import com.kagrawal.crudapp.Exception.DAOException;
import com.kagrawal.crudapp.model.User;
import com.kagrawal.crudapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static final String REGISTER_SQL = "INSERT INTO users(name,mobile,username,password) VALUES(?,?,?,?)";
    private static final String INSERT_SQL = "SELECT * FROM users WHERE username=? AND password=?";
    private static final String EXISTS_SQL = "SELECT 1 FROM users WHERE username=?";

    @Override
    public void register(User user) {
        try(Connection conn = JDBCUtils.fetchConnection();
            PreparedStatement ps = conn.prepareStatement(REGISTER_SQL)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getMobile());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public User insert(String username, String password) {
        User user = null;
        try(Connection conn = JDBCUtils.fetchConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    // FIX: Using column names ensures correct mapping
                    user = new User();
                    user.setName(rs.getString("name"));
                    user.setMobile(rs.getString("mobile"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return user;
    }

    @Override
    public Boolean exists(String username) {
        try(Connection conn = JDBCUtils.fetchConnection();
            PreparedStatement ps = conn.prepareStatement(EXISTS_SQL)) {

            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}