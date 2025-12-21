package com.kagrawal.crudapp.web;

import com.kagrawal.crudapp.Exception.DAOException;
import com.kagrawal.crudapp.dao.StudentDAOImpl;
import com.kagrawal.crudapp.model.Pagination;
import com.kagrawal.crudapp.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

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
                case "add": showNewForm(req,resp);
                    break;
                case "edit": showEditForm(req,resp);
                    break;
                case "delete": deleteStudent(req,resp);
                    break;
                case "insert": insertStudent(req,resp);
                    break;
                case "update": updateStudent(req,resp);
                    break;
                case "list":
                default:
                    listStudents(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("rootCause", e.getCause());
            req.setAttribute("errorException", e);
//            e.printStackTrace();  developer logs not final
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, DAOException {

        int pageSize = 10;
        int pageNo = 1;

        String pageParam = req.getParameter("page");

        if (pageParam != null) {
            pageNo = Integer.parseInt(pageParam);
        }


        Pagination pagination = new Pagination(pageNo, pageSize);

        List<Student> studentList = studentDAO.getSelectedStudents(pagination);
        int totalRecords = studentDAO.getTotalStudents();
        int totalPages = (int) Math.ceil((double) (totalRecords / pageSize));

        req.setAttribute("students", studentList);
        req.setAttribute("currentPage", pageNo);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("student-list.jsp").forward(req, resp);
    }

    private  void deleteStudent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, DAOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentDAO.delete(id);
        resp.sendRedirect(req.getContextPath() + "/students?action=list&success=Deleted Successfully"); // calling view with message
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DAOException {
        req.getRequestDispatcher("student-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DAOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student st = studentDAO.getStudentById(id);
        req.setAttribute("student",st);
        req.getRequestDispatcher("student-form.jsp").forward(req, resp);
    }

    private void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DAOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");

        if (!validate(req,name,email,mobile)) {
            req.getRequestDispatcher("student-form.jsp").forward(req, resp);
            return;
        }
        studentDAO.insert(new Student(name.trim(),email.trim(),mobile.trim()));
        resp.sendRedirect("students?action=list&success=insert Successfully");
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DAOException {
        int id = Integer.parseInt(req.getParameter("updateId"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");

        if (!validate(req,name,email,mobile)) {
            req.setAttribute("student",studentDAO.getStudentById(id));
            req.getRequestDispatcher("student-form.jsp").forward(req, resp);
            return;
        }

        studentDAO.update(new Student(id,name.trim(),email.trim(),mobile.trim()));
        resp.sendRedirect("students?action=list&success=Updated Successfully");
    }

    private boolean validate(HttpServletRequest req, String name, String email, String mobile) {

        boolean isValid = true;

        if (name == null || !Pattern.matches("^[A-Za-z ]{3,50}$", name.trim())) {
            isValid = false;
            req.setAttribute("nameError", "Name should be 3 to 50 characters");
        }

        if (email == null || !Pattern.matches("^[A-Za-z0-9_.-]+@(.+)$", email.trim())) {
            isValid = false;
            req.setAttribute("emailError", "Invalid Email");
        }

        if (mobile == null || !Pattern.matches("^[0-9]{10}$", mobile.trim())) {
            isValid = false;
            req.setAttribute("mobileError", "Mobile number should be 10 digits");
        }

        return isValid;
    }

}
