package com.kagrawal.crudapp.dao;

import com.kagrawal.crudapp.Exception.DAOException;
import com.kagrawal.crudapp.model.Pagination;
import com.kagrawal.crudapp.model.Student;
import com.kagrawal.crudapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private static final String INSERT_SQL = "INSERT INTO student(name,email,mobile) VALUES(?,?,?)";
    private static final String DELETE_SQL = "DELETE FROM student WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE student SET name = ?, email = ?, mobile = ? WHERE id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM student WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM student ORDER BY ID";
    private static final String BASE_SQL = "SELECT * FROM student";
    private static final String PAGE_SQL = " limit ? offset ?";
    private static final String COUNT_SQL = "SELECT COUNT(*) FROM student";


    @Override
    public void insert(Student student) {
        try (Connection con = JDBCUtils.fetchConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT_SQL)) {

            stmt.setString(1,student.getName());
            stmt.setString(2,student.getEmail());
            stmt.setString(3,student.getMobile());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to insert student",e);
        }
    }

    @Override
    public void delete(int id) {

        try (Connection con = JDBCUtils.fetchConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to delete student", e);
        }
    }


    @Override
    public Student getStudentById(int id) {
        Student student = null;
        try (Connection con = JDBCUtils.fetchConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1,id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));
                    student.setMobile(rs.getString("mobile"));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Unable to fetch student",e);
        }
        return student;
    }

    @Override
    public void update(Student student) {
        try (Connection con = JDBCUtils.fetchConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_SQL)) {

            stmt.setString(1,student.getName());
            stmt.setString(2,student.getEmail());
            stmt.setString(3,student.getMobile());

            stmt.setInt(4,student.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Unable to update student",e);
        }
    }

    @Override
    public List<Student> getSelectedStudents(Pagination pagination) {
        List<Student> studentList = new ArrayList<>();

        String sql = BASE_SQL + PAGE_SQL;

        try (Connection con = JDBCUtils.fetchConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pagination.getPageSize());
            stmt.setInt(2, pagination.getOffset());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));
                    student.setMobile(rs.getString("mobile"));
                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Unable to fetch students", e);
        }

        return studentList;
    }

    public int getTotalStudents() {
        try (Connection con = JDBCUtils.fetchConnection();
             PreparedStatement stmt = con.prepareStatement(COUNT_SQL);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Unable to count students", e);
        }
        return 0;
    }

}
