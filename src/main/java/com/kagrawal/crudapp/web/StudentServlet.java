package com.kagrawal.crudapp.web;

import com.kagrawal.crudapp.Exception.DAOException;
import com.kagrawal.crudapp.dao.StudentDAOImpl;
import com.kagrawal.crudapp.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/", "/students"})
public class StudentServlet extends HttpServlet {

    private StudentDAOImpl studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    break;
                case "edit":
                    break;
                case "delete":
                    break;
                case "insert":
                    break;
                case "update":
                    break;
                case "list":
                default:
                    listStudents(req, resp);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, DAOException {

        List<Student> studentList = studentDAO.getAllStudents();
        req.setAttribute("students", studentList);
        req.getRequestDispatcher("student-list.jsp").forward(req, resp);
    }
}
