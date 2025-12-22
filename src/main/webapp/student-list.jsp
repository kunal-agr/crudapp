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

        int currentPage = (request.getAttribute("currentPage") != null) ? (int) request.getAttribute("currentPage") : 1;
        int totalPages = (request.getAttribute("totalPages") != null) ? (int) request.getAttribute("totalPages") : 1;
        int pageSize = (request.getAttribute("pageSize") != null) ? (int) request.getAttribute("pageSize") : 5;
        int totalRecords = (request.getAttribute("totalRecords")!=null) ? (int) request.getAttribute("totalRecords") : 5;

        int start = (currentPage - 1) * pageSize + 1;
        int end = Math.min(start + pageSize - 1, totalRecords);
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

    <div class="d-flex justify-content-between mb-3">
        <!-- PAGE SIZE -->
        <div class="text-muted">
            Showing
            <strong><%=start%> <i class="fa-solid fa-arrow-right"></i><%=end%></strong>
             of
             <strong><%=totalRecords %></strong>
        </div>
        <form action="students" method="get" class="d-flex align-items-center mb-3 me-2">
            <span class="me-2">Show</span>

            <select name="pageSize"
                    class="form-select form-select-sm w-auto"
                    onchange="this.form.submit()">
                <option value="5"   <%= pageSize == 5   ? "selected" : "" %>>5</option>
                <option value="10"  <%= pageSize == 10  ? "selected" : "" %>>10</option>
                <option value="20"  <%= pageSize == 20  ? "selected" : "" %>>20</option>
                <option value="50"  <%= pageSize == 50  ? "selected" : "" %>>50</option>
                <option value="100" <%= pageSize == 100 ? "selected" : "" %>>100</option>
                <option value="200" <%= pageSize == 200 ? "selected" : "" %>>200</option>

            </select>
            <span class="ms-2">entries</span>
        </form>


        <!-- GO TO PAGE -->
        <form action="students" method="get" class="d-flex align-items-center mb-3">
            <span class="text-muted me-2">Go</span>
            <input type="number" name="page" min="1" max="<%= totalPages %>"
                   class="form-control form-control-sm me-2" style="width:70px;">
            <input type="hidden" name="pageSize" value="<%= pageSize %>">
            <button class="btn btn-sm btn-primary">GO</button>
        </form>
    </div>

    <% if (totalPages > 1) {%>
        <!-- PAGINATION -->
        <nav>
            <ul class="pagination justify-content-center">

                <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                    <a class="page-link" href="students?page=1&pageSize=<%= pageSize %>&pageSize=<%=pageSize%>
                    ">First</a>
                </li>

                <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                    <a class="page-link"
                       href="students?page=<%= currentPage - 1 %>&pageSize=<%=pageSize%>">&laquo;</a>
                </li>

                <%
                    for (int i = 1; i <= totalPages; i++) {
                %>
                <li class="page-item <%= i == currentPage ? "active" : "" %>">
                    <a class="page-link"
                       href="students?page=<%= i %>&pageSize=<%=pageSize%>"><%= i %></a>
                </li>
                <%
                    }
                %>

                <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                    <a class="page-link"
                       href="students?page=<%= currentPage + 1 %>&pageSize=<%=pageSize%>">&raquo;</a>
                </li>

                <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                    <a class="page-link"
                       href="students?page=<%= totalPages %>&pageSize=<%=pageSize%>">Last</a>
                </li>

            </ul>
        </nav>
    <% } %>
    </body>
</html>
