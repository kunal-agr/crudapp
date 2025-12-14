<%@ page language="java" import="java.util.*,com.kagrawal.crudapp.model.Student" %>
<html>
<body>

<h2>MVC CRUD APPLICATION</h2>
<h2>SERVLET + JSP + JDBC</h2>

<a href="students?action=add">Add Student</a>

<table border="1" cellpadding="5">
    <thead>
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
        if (students != null && !students.isEmpty()) {
            for (Student s : students) {
    %>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getName() %></td>
        <td><%= s.getEmail() %></td>
        <td><%= s.getMobile() %></td>
        <td>
            <a href="students?action=edit&id=<%=s.getId()%>">Update</a>
            <a href="students?action=delete&id=<%=s.getId()%>">Delete</a>
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
