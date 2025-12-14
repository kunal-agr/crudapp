<%@ page language="java" import="com.kagrawal.crudapp.model.Student" %>
<html>
    <head>
            <title>MVC CRUD FORM</title>
            <link rel = "stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"></link>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        </head>
    <body class="container">
        <h2 class="text-center mb-3">MVC CRUD APPLICATION</h2>
        <h2 class="text-center mb-3">SERVLET + JSP + JDBC</h2>

        <%
            Student student = (Student) request.getAttribute("student");
            boolean isEdit = (student != null) ? true : false;
        %>

        <h4 class="text-danger mb-4"><%= isEdit ? "Edit" : "Add Student"%></h4>
        <form action="students?action=<%= isEdit ? "update" : "insert"%>" method="post">
        <%
            if (isEdit) {
        %>
                <input type="hidden" name="updateId" value = "<%= (student!=null ? student.getId() : "")%>"/>
        <%
            }
        %>
            <div class="mb-3">
                <label>Name</label>
                <input class="form-control" type = "text" name="name" value = "<%= (student!=null ? student.getName() : "")%>" placeholder = "Enter Name" />
            </div>

            <div class="mb-3">
                <label>Email</label>
                <input class="form-control" type = "email" name="email" value = "<%= (student!=null ? student.getEmail() : "")%>" placeholder = "Enter Email" />
            </div>

            <div class="mb-3">
                <label>Mobile</label>
                <input class="form-control" type = "text" name="mobile" value = "<%= (student!=null ? student.getMobile() : "")%>" placeholder = "Enter Mobile" />
            </div>

            <button type="submit" class="btn btn-success">Save</button>
            <a href="#" class="btn btn-secondary">Cancel</a>
        </form>
    </body>
</html>