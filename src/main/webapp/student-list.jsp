<%@ page language="java" import="java.util.*,com.kagrawal.crudapp.model.Student" %>
<html>
    <head>
        <title>MVC CRUD APPLICATION</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
    </head>

    <body class="container mt-4">

    <h2 class="text-center mb-3">MVC CRUD APPLICATION</h2>
    <h2 class="text-center mb-3">SERVLET + JSP + JDBC</h2>

    <%
        String success = request.getParameter("success");
        if (success != null) {
    %>
        <div class="alert alert-success text-center"><%= success %></div>
    <%
        }

        int currentPage = (request.getAttribute("currentPage") != null)
                ? (int) request.getAttribute("currentPage")
                : 1;

        int totalPages = (request.getAttribute("totalPages") != null)
                ? (int) request.getAttribute("totalPages")
                : 1;
    %>

    <a href="students?action=add" class="btn btn-outline-primary mb-3">
        <i class="fa-solid fa-user-plus me-2"></i> Add Student
    </a>

    <table class="table table-bordered table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Action</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            int cnt = 1;

            if (students != null && !students.isEmpty()) {
                for (Student s : students) {
        %>
        <tr>
            <td class="text-center"><%= cnt++ %></td>
            <td class="text-center"><%= s.getName() %></td>
            <td class="text-center"><%= s.getEmail() %></td>
            <td class="text-center"><%= s.getMobile() %></td>
            <td class="text-center">
                <a href="students?action=edit&id=<%= s.getId() %>" class="btn btn-info btn-sm mx-1">
                    <i class="fa-solid fa-pen-to-square"></i>
                </a>
                <a href="students?action=delete&id=<%= s.getId() %>"
                   onclick="return confirm('Are you sure you want to delete?')"
                   class="btn btn-danger btn-sm mx-1">
                    <i class="fa-solid fa-trash-can"></i>
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" class="text-center">No Students Found</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <!-- PAGINATION -->
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">

            <li class="page-item <%= (currentPage == 1) ? "disabled" : "" %>">
                <a class="page-link" href="students?page=<%= currentPage - 1 %>">&laquo;</a>
            </li>

            <%
                for (int i = 1; i <= totalPages; i++) {
            %>
            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                <a class="page-link" href="students?page=<%= i %>"><%= i %></a>
            </li>
            <%
                }
            %>

            <li class="page-item <%= (currentPage == totalPages) ? "disabled" : "" %>">
                <a class="page-link" href="students?page=<%= currentPage + 1 %>">&raquo;</a>
            </li>

        </ul>
    </nav>

    </body>
</html>
