<%@ page language="java" import="java.util.*,com.kagrawal.crudapp.model.Student" %>
<html>
    <head>
        <title>MVC CRUD APPLICATION</title>
        <link rel = "stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"></link>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body class="container mt-4">

        <h2 class="text-center mb-3">MVC CRUD APPLICATION</h2>
        <h2 class="text-center mb-3">SERVLET + JSP + JDBC</h2>

        <%
            String success = (String)request.getParameter("success");
            if (success != null) {
        %>
            <div class="alert alert-success text-center"><%= success %></div>
        <%
            }
        %>
        <a href="students?action=add" class="btn btn-primary">
        <i class="fa-solid fa-user-plus me-2"></i> Add Student</a>

        <table class="table table-bordered table-striped table-hover mt-3">
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
                <td>
                    <a href="students?action=edit&id=<%=s.getId()%>" class="btn bg-info mx-3"><i class="fa-solid fa-pen-to-square"></i></a>
                    <a href="students?action=delete&id=<%=s.getId()%>" onclick="return confirm('Are you sure you want to delete?') " class="btn bg-danger"><i class="fa-solid fa-trash-can"></i></a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No Students Found</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

    </body>
</html>
