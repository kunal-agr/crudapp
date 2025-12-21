package com.kagrawal.crudapp.dao;

import com.kagrawal.crudapp.model.Pagination;
import com.kagrawal.crudapp.model.Student;

import java.util.List;

public interface StudentDAO {
    void insert(Student student);
    void delete(int id);
    Student getStudentById(int id);
    void update(Student student);
    List<Student> getSelectedStudents(Pagination pagination);
    int getTotalStudents();
}
