package com.kagrawal.crudapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kagrawal.crudapp.dao.StudentDAO;
import com.kagrawal.crudapp.dao.StudentDAOImpl;
import com.kagrawal.crudapp.model.Pagination;
import com.kagrawal.crudapp.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/students/*")
public class StudentApiServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        studentDAO = new StudentDAOImpl();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            int id = Integer.parseInt(pathInfo.substring(1));
            Student student = studentDAO.getStudentById(id);
            objectMapper.writeValue(resp.getOutputStream(), student);
            return;
        }

        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));

        Pagination pagination = new Pagination();
        pagination.setPageNo(page);
        pagination.setPageSize(size);

        List<Student> students = studentDAO.getSelectedStudents(pagination);
        int total = ((StudentDAOImpl) studentDAO).getTotalStudents();

        Map<String, Object> response = new HashMap<>();
        response.put("data", students);
        response.put("page", page);
        response.put("size", size);
        response.put("total", total);

        objectMapper.writeValue(resp.getOutputStream(), response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        Student student = objectMapper.readValue(req.getInputStream(), Student.class);
        studentDAO.insert(student);

        resp.setStatus(HttpServletResponse.SC_CREATED);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Student created successfully");

        objectMapper.writeValue(resp.getOutputStream(), response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        Student student = objectMapper.readValue(req.getInputStream(), Student.class);
        student.setId(id);

        studentDAO.update(student);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Student updated successfully");

        objectMapper.writeValue(resp.getOutputStream(), response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        studentDAO.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Student deleted successfully");

        objectMapper.writeValue(resp.getOutputStream(), response);
    }
}