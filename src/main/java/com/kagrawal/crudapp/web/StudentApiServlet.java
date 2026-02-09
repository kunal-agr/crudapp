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
import java.util.List;
import java.util.Map;

@WebServlet("/api/students/*")
public class StudentApiServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private ObjectMapper mapper;

    @Override
    public void init() {
        studentDAO = new StudentDAOImpl();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            String path = req.getPathInfo();

            // Handle ID based fetch (/api/students/123)
            if (path != null && path.length() > 1) {
                int id = Integer.parseInt(path.substring(1));
                Student s = studentDAO.getStudentById(id);
                if (s == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    mapper.writeValue(resp.getOutputStream(), Map.of("error", "Student not found"));
                    return;
                }
                mapper.writeValue(resp.getOutputStream(), s);
                return;
            }

            // Handle Pagination fetch
            int page = getParam(req, "page", 1);
            int size = getParam(req, "size", 5);

            Pagination p = new Pagination();
            p.setPageNo(page);
            p.setPageSize(size);

            List<Student> list = studentDAO.getSelectedStudents(p);
            // Cast to implementation to access getTotalStudents()
            int total = ((StudentDAOImpl) studentDAO).getTotalStudents();

            Map<String, Object> responseData = Map.of(
                    "data", list,
                    "page", page,
                    "size", size,
                    "total", total
            );

            mapper.writeValue(resp.getOutputStream(), responseData);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(resp.getOutputStream(), Map.of("error", "Server error", "message", e.getMessage()));
        }
    }

    // Robust parameter parsing
    private int getParam(HttpServletRequest req, String name, int def) {
        String val = req.getParameter(name);
        if (val == null || val.trim().isEmpty()) return def;
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Student s = mapper.readValue(req.getInputStream(), Student.class);
            studentDAO.insert(s);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            mapper.writeValue(resp.getOutputStream(), Map.of("message", "Created"));
        } catch (Exception e) {
            resp.setStatus(500);
            mapper.writeValue(resp.getOutputStream(), Map.of("error", "Insert failed"));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = req.getPathInfo();
            int id = Integer.parseInt(path.substring(1));
            Student s = mapper.readValue(req.getInputStream(), Student.class);
            s.setId(id);
            studentDAO.update(s);
            mapper.writeValue(resp.getOutputStream(), Map.of("message", "Updated"));
        } catch (Exception e) {
            resp.setStatus(500);
            mapper.writeValue(resp.getOutputStream(), Map.of("error", "Update failed"));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = req.getPathInfo();
            int id = Integer.parseInt(path.substring(1));
            studentDAO.delete(id);
            mapper.writeValue(resp.getOutputStream(), Map.of("message", "Deleted"));
        } catch (Exception e) {
            resp.setStatus(500);
            mapper.writeValue(resp.getOutputStream(), Map.of("error", "Delete failed"));
        }
    }
}