# MVC Student Manager

[![Java 17](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Jakarta Servlet API](https://img.shields.io/badge/Jakarta%20Servlet-API-lightgrey)](https://jakarta.ee/specifications/servlet/)
[![JSP](https://img.shields.io/badge/JSP-enabled-green)](https://jakarta.ee/specifications/pages/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue)](https://www.postgresql.org/)
[![Apache Tomcat 11](https://img.shields.io/badge/Tomcat-11-orange)](https://tomcat.apache.org/)
[![Bootstrap 5](https://img.shields.io/badge/Bootstrap-5-purple)](https://getbootstrap.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A **Student Management CRUD application** built using **Java MVC architecture**, following clean separation of concerns, validation, centralized exception handling, and scalable UI design.

---

## Features ✨

### v1.0 – Core CRUD

* Add Student
* View Student List
* Edit Student
* Delete Student
* Client-side validation (HTML5)
* Server-side validation (Servlet)
* Centralized error handling (`DAOException`)
* Clean Bootstrap-based UI

### v1.1 – Enhanced UX & Scalability

* Pagination for student listing
* Reusable navigation bar
* Improved UI consistency
* Better user navigation flow
* Pagination model abstraction

---

## New Features 🆕

* Pagination support using a dedicated `Pagination` model
* Common navigation bar across all pages
* Cleaner page transitions and navigation flow
* Improved scalability for large datasets

---

## Git Hygiene 🧹

This project follows **clean Git practices** to maintain readability and collaboration quality:

* Meaningful commit messages (`feat`, `fix`, `docs`, `refactor`)
* Logical commits for each feature or fix
* No unnecessary files committed
* Clear version progression (`v1.0` → `v1.1`)
* Organized project and resource structure

---

## Tech Stack 🛠️

| Layer           | Technology                    |
| --------------- | ----------------------------- |
| Frontend (View) | JSP, HTML5, CSS3, Bootstrap 5 |
| Controller      | Jakarta Servlet API           |
| Backend         | Java 17                       |
| Database Access | JDBC                          |
| Database        | PostgreSQL                    |
| Server          | Apache Tomcat 11              |
| Build Tool      | Maven                         |
| Architecture    | MVC                           |
| Version Control | Git & GitHub                  |

---

## Project Structure 📂

```
java-crud-mvc-playground
│
├── screenshots
│   ├── v1.0
│   └── v1.1
│
├── src
│   └── main
│       ├── java
│       │   └── com.kagrawal.crudapp
│       │       ├── dao
│       │       │   ├── StudentDAO.java
│       │       │   └── StudentDAOImpl.java
│       │       │
│       │       ├── Exception
│       │       │   └── DAOException.java
│       │       │
│       │       ├── model
│       │       │   ├── Student.java
│       │       │   └── Pagination.java
│       │       │
│       │       ├── utils
│       │       │   └── JDBCUtils.java
│       │       │
│       │       └── web
│       │           └── StudentServlet.java
│       │
│       └── resources
│
├── src/main/webapp
│   ├── student-list.jsp
│   ├── student-form.jsp
│   ├── error.jsp
│
└── README.md
```

---

## Architecture Overview 🏗️

The application follows the **Model–View–Controller (MVC)** pattern:

* **Model** → `Student`, `Pagination`
* **DAO** → Database operations using JDBC
* **Controller** → `StudentServlet`
* **View** → JSP pages with Bootstrap UI

---

## Database Schema 🗄️

```sql
CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile VARCHAR(10) NOT NULL
);
```

---

## Configuration ⚙️

Update database credentials in `JDBCUtils.java`:

```java
private static final String URL  = "jdbc:postgresql://localhost:5432/studentdb";
private static final String USER = "postgres";
private static final String PASS = "password";
```

---

## Screenshots 📸

### Version v1.0 – Core CRUD

| Feature          | Preview                                                    |
| ---------------- | ---------------------------------------------------------- |
| Student List     | <img src="screenshots/v1.0/student-list.png" width="400"/> |
| Add Student      | <img src="screenshots/v1.0/add-student.png" width="400"/>  |
| Edit Student     | <img src="screenshots/v1.0/edit-student.png" width="400"/> |
| Validation Error | <img src="screenshots/v1.0/validation.png" width="400"/>   |
| Error Page       | <img src="screenshots/v1.0/error.png" width="400"/>        |

---

### Version v1.1 – Pagination & Navigation

| Feature        | Preview                                                      |
| -------------- | ------------------------------------------------------------ |
| Pagination     | <img src="screenshots/v1.1/pagination.png" width="400"/>     |
| Navigation Bar | <img src="screenshots/v1.1/navbar.png" width="400"/>         |
| Paginated List | <img src="screenshots/v1.1/paginated-list.png" width="400"/> |

---

## How to Run ▶️

1. Clone the repository

```bash
git clone https://github.com/kunal-agr/crudapp.git
```

2. Create database

```sql
CREATE DATABASE studentdb;
```

3. Import as Maven project in IDE
4. Configure Apache Tomcat 11
5. Run and access:

```
http://localhost:8080/<project-context>
```

---

## Purpose 🎯

This project was built to practice **MVC architecture**, **CRUD operations**, **pagination**, **validation**, and **professional UI structuring** following industry-level standards.

---

## License 📄

This project is licensed under the MIT License.

---

## Contribution 🤝

Fork the repository and feel free to improve or extend the project.
