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
    private ObjectMapper mapper;

    @Override
    public void init() {
        studentDAO = new StudentDAOImpl();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try {
            String path = req.getPathInfo();

            if (path != null && path.length() > 1) {
                int id = Integer.parseInt(path.substring(1));
                Student s = studentDAO.getStudentById(id);
                if (s == null) {
                    resp.setStatus(404);
                    mapper.writeValue(resp.getOutputStream(), Map.of("error","Not found"));
                    return;
                }
                mapper.writeValue(resp.getOutputStream(), s);
                return;
            }

            int page = Integer.parseInt(req.getParameter("page"));
            int size = Integer.parseInt(req.getParameter("size"));

            Pagination p = new Pagination();
            p.setPageNo(page);
            p.setPageSize(size);

            List<Student> list = studentDAO.getSelectedStudents(p);
            int total = ((StudentDAOImpl) studentDAO).getTotalStudents();

            mapper.writeValue(resp.getOutputStream(), Map.of(
                    "data", list,
                    "page", page,
                    "size", size,
                    "total", total
            ));

        } catch (Exception e) {
            resp.setStatus(500);
            mapper.writeValue(resp.getOutputStream(), Map.of(
                    "error","Server error",
                    "message", e.getMessage()
            ));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Student s = mapper.readValue(req.getInputStream(), Student.class);
            studentDAO.insert(s);
            resp.setStatus(201);
            mapper.writeValue(resp.getOutputStream(), Map.of("message","Created"));
        } catch (Exception e) {
            resp.setStatus(500);
            mapper.writeValue(resp.getOutputStream(), Map.of("error","Insert failed"));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            Student s = mapper.readValue(req.getInputStream(), Student.class);
            s.setId(id);
            studentDAO.update(s);
            mapper.writeValue(resp.getOutputStream(), Map.of("message","Updated"));
        } catch (Exception e) {
            resp.setStatus(500);
            mapper.writeValue(resp.getOutputStream(), Map.of("error","Update failed"));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            studentDAO.delete(id);
            mapper.writeValue(resp.getOutputStream(), Map.of("message","Deleted"));
        } catch (Exception e) {
            resp.setStatus(500);
            mapper.writeValue(resp.getOutputStream(), Map.of("error","Delete failed"));
        }
    }
}