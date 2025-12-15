<%@ page language="java" import="com.kagrawal.crudapp.model.Student" %>
<html>
    <head>
        <title>MVC CRUD FORM</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    </head>

    <body class="container">

        <h2 class="text-center mb-3">MVC CRUD APPLICATION</h2>
        <h2 class="text-center mb-3">SERVLET + JSP + JDBC</h2>

        <%
            Student student = (Student) request.getAttribute("student");
            boolean isEdit = (student != null);

            String nameError = (String) request.getAttribute("nameError");
            String emailError = (String) request.getAttribute("emailError");
            String mobileError = (String) request.getAttribute("mobileError");
        %>

        <h4 class="text-danger mb-4"><%= isEdit ? "Edit Student" : "Add Student" %></h4>

        <form action="students?action=<%= isEdit ? "update" : "insert" %>" method="post">

            <% if (isEdit) { %>
                <input type="hidden" name="updateId" value="<%= student.getId() %>">
            <% } %>

            <div class="mb-3">
                <label>Name</label>
                <input class="form-control" type="text" name="name"
                value="<%= (request.getParameter("name") != null) ? request.getParameter("name") : (student != null ? student.getName() : "") %>"
                placeholder="Enter Name"
                required
                pattern="[A-Za-z ]{3,50}"
                title="name should be atleast 3 to 50"
                >
                <p class="text-danger"><%= nameError != null ? nameError : "" %></p>
            </div>

            <div class="mb-3">
                <label>Email</label>
                <input class="form-control" type="email" name="email"
                value="<%=(request.getParameter("email") != null) ? request.getParameter("email") : (student != null ? student.getEmail() : "") %>"
                placeholder="Enter Email"
                required
                >
                <p class="text-danger"><%= emailError != null ? emailError : "" %></p>
            </div>

            <div class="mb-3">
                <label>Mobile</label>
                <input class="form-control" type="text" name="mobile"
                value="<%=(request.getParameter("mobile") != null) ? request.getParameter("mobile") : (student != null ? student.getMobile() : "") %>"
                placeholder="Enter Mobile"
                required
                pattern="[0-9]{10}"
                title="Number should be of 10 digits"
                >
                <p class="text-danger"><%= mobileError != null ? mobileError : "" %></p>
            </div>

            <button type="submit" class="btn btn-success">Save</button>
            <a href="students?action=list" class="btn btn-secondary">Cancel</a>

        </form>

    </body>
</html>
