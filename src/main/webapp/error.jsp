<%@ page isErrorPage="true" %>
<html>
    <head>
        <title>MVC CRUD FORM</title>
        <link rel = "stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"></link>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body class="container mt-5">
        <h2 class="text-danger">Error Occurred</h2>
        <h5 class="text-muted">something went wrong...</h5>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            String errorCause = (String) request.getAttribute("errorCause");
        %>
        <div class="card mb-3">
            <div class="card-header bg-dark text-light">
                Debugging Information
            </div>

            <div class="card-body">
                <p><strong><%= errorMessage %></strong></p>
                <p><strong><%= errorCause %></strong></p>
                <a href="students?action=list" class="btn btn-primary">Go Back</a>
            </div>
        </div>

    </body>
</html>
