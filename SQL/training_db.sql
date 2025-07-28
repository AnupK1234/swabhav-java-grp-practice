CREATE DATABASE IF NOT EXISTS training_db;

USE training_db;
 
DROP TABLE IF EXISTS student;

CREATE TABLE student (

    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,

    name        VARCHAR(100)  NOT NULL,

    email       VARCHAR(100)  UNIQUE,

    percentage  DECIMAL(5,2)

);
 
INSERT INTO student (name,email,percentage)

VALUES
  --  ('Aju Palleri','aju@example.com',87.40),
   ('Dhiraj Parmar','dhiraj@example.com',76.10);
  --  ('Ravi verma', 'ravi@example.com', 79.60); 
 select * from student;