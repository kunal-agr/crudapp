<!DOCTYPE html>
<html>
<head>
    <title>Error Occurred</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
</head>

    <body class="container my-5">

    <h2 class="text-danger">Something went wrong!</h2>
    <p class="text-muted">We encountered an issue processing your request.</p>

    <div class="card mt-4">
        <div class="card-header bg-dark text-white">
            Error Information
        </div>
        <div class="card-body bg-light">

            <p><strong>Status:</strong></p>
            <pre id="status">-</pre>

            <p><strong>Message:</strong></p>
            <pre id="message">-</pre>

            <p><strong>Path:</strong></p>
            <pre id="path">-</pre>

        </div>
    </div>

    <a href="student-list.jsp" class="btn btn-primary mt-3">Back to Home</a>

    <script>
        const params = new URLSearchParams(window.location.search);
        const status = params.get("status") || 500;
        const message = params.get("message") || "Unexpected error occurred";
        const path = params.get("path") || "N/A";

        document.getElementById("status").innerText = status;
        document.getElementById("message").innerText = message;
        document.getElementById("path").innerText = path;
    </script>

    </body>
</html>
