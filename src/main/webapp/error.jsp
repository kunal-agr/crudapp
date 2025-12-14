<%@ page isErrorPage="true" %>
<html>
    <body>
        <h1>Error Occurred</h1>

        <p><%= request.getAttribute("errorMessage") %></p>
        <p><%= exception %></p>

        <a href="students?action=list">Go Back</a>
    </body>
</html>
