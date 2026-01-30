<!DOCTYPE html>
<html>
<head>
    <title>MVC CRUD FORM</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>

<body class="container mt-4">

<h2 class="text-center mb-3">MVC CRUD APPLICATION</h2>
<h2 class="text-center mb-3">SERVLET + JSP + JDBC</h2>

<h4 class="text-danger mb-4" id="title">Add Student</h4>

<div class="mb-3">
    <label>Name</label>
    <input class="form-control" type="text" id="name"
           placeholder="Enter Name"
           required
           pattern="[A-Za-z ]{3,50}">
    <p class="text-danger" id="nameError"></p>
</div>

<div class="mb-3">
    <label>Email</label>
    <input class="form-control" type="email" id="email"
           placeholder="Enter Email"
           required>
    <p class="text-danger" id="emailError"></p>
</div>

<div class="mb-3">
    <label>Mobile</label>
    <input class="form-control" type="text" id="mobile"
           placeholder="Enter Mobile"
           required
           pattern="[0-9]{10}">
    <p class="text-danger" id="mobileError"></p>
</div>

<button class="btn btn-success" onclick="save()">Save</button>
<a href="student-list.jsp" class="btn btn-secondary">Cancel</a>

<script>
const api = '<%=request.getContextPath()%>/api/students';
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

if (id) {
    document.getElementById("title").innerText = "Edit Student";
    loadStudent(id);
}

function loadStudent(id) {
    fetch(`${api}/${id}`)
        .then(res => res.json())
        .then(s => {
            document.getElementById("name").value = s.name;
            document.getElementById("email").value = s.email;
            document.getElementById("mobile").value = s.mobile;
        });
}

function save() {
    clearErrors();

    const student = {
        name: document.getElementById("name").value.trim(),
        email: document.getElementById("email").value.trim(),
        mobile: document.getElementById("mobile").value.trim()
    };

    if (!validate(student)) return;

    fetch(id ? `${api}/${id}` : api, {
        method: id ? "PUT" : "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student)
    })
    .then(res => {
        if (!res.ok) throw new Error("Failed");
        window.location.href = "student-list.jsp";
    })
    .catch(() => alert("Something went wrong"));
}

function validate(s) {
    let ok = true;

    if (s.name.length < 3) {
        showError("nameError", "Name must be at least 3 characters");
        ok = false;
    }

    if (!s.email.includes("@")) {
        showError("emailError", "Invalid email");
        ok = false;
    }

    if (!/^[0-9]{10}$/.test(s.mobile)) {
        showError("mobileError", "Mobile must be 10 digits");
        ok = false;
    }

    return ok;
}

function showError(id, msg) {
    document.getElementById(id).innerText = msg;
}

function clearErrors() {
    ["nameError","emailError","mobileError"].forEach(e => {
        document.getElementById(e).innerText = "";
    });
}
</script>

</body>
</html>
