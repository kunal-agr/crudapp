DROP DATABASE IF EXISTS cruddb;
CREATE DATABASE cruddb;

\c cruddb

CREATE TABLE student(
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(20) UNIQUE NOT NULL,
    mobile VARCHAR(10) NOT NULL
);

INSERT INTO student(name,email,mobile) VALUES ('Sahil Agrawal','sahil@gamil.com','1234567890');
INSERT INTO student(name,email,mobile) VALUES ('Krishna Wable','krishwable@gamil.com','987654321');
INSERT INTO student(name,email,mobile) VALUES ('Gauravsingh bisht','gaurav@gamil.com ','345678934');

SELECT * FROM student;