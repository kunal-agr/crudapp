# MVC Student Manager

[![Java 17](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Jakarta Servlet API](https://img.shields.io/badge/Jakarta%20Servlet-API-lightgrey)](https://jakarta.ee/specifications/servlet/)
[![JSP](https://img.shields.io/badge/JSP-enabled-green)](https://jakarta.ee/specifications/pages/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue)](https://www.postgresql.org/)
[![Apache Tomcat 11](https://img.shields.io/badge/Tomcat-11-orange)](https://tomcat.apache.org/)
[![Bootstrap 5](https://img.shields.io/badge/Bootstrap-5-purple)](https://getbootstrap.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)


Welcome to the **MVC Student Manager**! 🎉
This project is a **CRUD application** developed following **industry standards**, implementing the **MVC architecture** with clean separation of responsibilities, validation, centralized error handling, and attractive UI/UX.

---

## Features ✨

* **Core CRUD Operations**

  * Add Student
  * View Student List
  * Edit Student
  * Delete Student
* **Validation**

  * Client-side validation using HTML5
  * Server-side validation in Servlet
* **UI / UX Enhancements**

  * Responsive layout using Bootstrap 5
  * Clean and consistent action buttons
  * User-friendly messages and alerts
* **Centralized Exception Handling**

  * Custom `DAOException`
  * Dedicated error page (`error.jsp`)
* **Clean MVC Separation**

  * Model → Data representation
  * DAO → Database access
  * Controller → Request handling
  * View → UI rendering

---

## Tech Stack 🛠️

| Layer                    | Technology                    |
| ------------------------ | ----------------------------- |
| Frontend (View)          | JSP, HTML5, CSS3, Bootstrap 5 |
| UI Icons                 | Font Awesome                  |
| Controller               | Jakarta Servlet API           |
| Backend (Business Logic) | Java                          |
| Database Access          | JDBC                          |
| Database                 | PostgreSQL                    |
| Application Server       | Apache Tomcat 11              |
| Build Tool               | Maven                         |
| Architecture Pattern     | MVC (Model–View–Controller)   |
| Version Control          | Git & GitHub                  |

---

## Project Structure 📂

```
java-crud-mvc-playground
│
├── src/main/java
│   └── com/nsgacademy/crudmvc
│       ├── model
│       │   └── Student.java
│       │
│       ├── dao
│       │   └── StudentDAO.java
│       │
│       ├── exception
│       │   └── DAOException.java
│       │
│       ├── utils
│       │   └── JDBCUtils.java
│       │
│       └── web
│           └── StudentServlet.java
│
├── src/main/webapp
│   ├── student-list.jsp
│   ├── student-form.jsp
│   └── error.jsp
│
└── README.md
```

---

## Architecture Overview 🏗️

<img width="1024" height="1024" alt="readme_flow_diagram" src="https://github.com/user-attachments/assets/8d423c80-2408-4469-8e8a-f4733a1b5680" />


The project follows a **Model–View–Controller (MVC)** pattern:

* **Model**: Student.java represents the student entity.
* **DAO**: Handles database CRUD operations.
* **Controller**: Servlets handle requests and responses.
* **View**: JSP pages render the UI.

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

## How to Run the Project ▶️

### Prerequisites

* JDK 17 or higher
* PostgreSQL installed and running
* Apache Tomcat 11
* IDE (IntelliJ IDEA / Eclipse)

### Clone the Repo

```bash
git clone https://github.com/kunal-agr/crudapp.git
```

### Create Database

```sql
CREATE DATABASE studentdb;
```

### Import Project

* Open IDE
* Import as Maven Project
* Configure Apache Tomcat 11 in IDE

### Run Application

* Deploy project on Tomcat
* Access application at: `http://localhost:8080/<project-context>`

---

## Screenshots 📸

<!-- Add screenshots here -->

<img width="1366" height="720" alt="student-list" src="https://github.com/user-attachments/assets/b9b07236-f327-48b8-af11-46f9e5d07e1e" />
<img width="1366" height="720" alt="add_form" src="https://github.com/user-attachments/assets/2008830c-0ae7-489c-b6c6-d554b7b43863" />
<img width="1366" height="720" alt="after_added" src="https://github.com/user-attachments/assets/d5270974-2c38-4e2c-9383-d3f7dbb653c1" />
<img width="1366" height="720" alt="edit_form" src="https://github.com/user-attachments/assets/d7add624-b170-4ebe-bd1e-717af8828c7c" />
<img width="1366" height="720" alt="after_edit" src="https://github.com/user-attachments/assets/45d0ed1b-c719-4471-a4c9-5d17c5dd8b09" />
<img width="1366" height="720" alt="delete_conform" src="https://github.com/user-attachments/assets/2bec6e6e-20ac-4a19-a463-6fb8fd6cf609" />
<img width="1366" height="720" alt="delete_message" src="https://github.com/user-attachments/assets/0f874879-54fd-4745-90c7-ebe7bc37174c" />
<img width="1366" height="720" alt="validation_image" src="https://github.com/user-attachments/assets/c34056a4-152e-4350-a22d-d87b8b3c7f87" />
<img width="1366" height="720" alt="error" src="https://github.com/user-attachments/assets/b5b8d381-ee3a-4001-9a66-769108b59020" />
---

## Purpose 🎯

This project was made to practice **MVC architecture**, **CRUD operations**, **validation**, **centralized error handling**, and **professional UI/UX design**. Learned how to separate responsibilities properly.

---

## Contribution 🤝

Feel free to **fork** this repo and contribute! 👐

---

## License 📄

This project is licensed under the MIT License.

---

## Acknowledgement 🙏

* Open source community for inspiration and guidance.
* Thank you f
