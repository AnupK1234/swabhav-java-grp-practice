CREATE DATABASE university_db;
USE university_db;

-- Department Table
CREATE TABLE departments (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL
);

-- Students Table
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);

-- Courses Table
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100),
    credits INT
);

-- Enrollments Table
CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

-- Departments
INSERT INTO departments (dept_name) VALUES
('Computer Science'), ('Electrical'), ('Mechanical');

-- Students
INSERT INTO students (name, age, dept_id) VALUES
('Rohan', 21, 1),
('Priya', 22, 2),
('Aman', 23, 1),
('Sneha', 20, 3);

-- Courses
INSERT INTO courses (course_name, credits) VALUES
('Data Structures', 4),
('Circuits', 3),
('Thermodynamics', 4),
('Operating Systems', 3);

-- Enrollments
INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES
(1, 1, '2025-07-10'),
(1, 4, '2025-07-12'),
(2, 2, '2025-07-11'),
(3, 1, '2025-07-14'),
(4, 3, '2025-07-13');

-- Use cases are
-- 1. Simplify complex queries
-- 2. limit access to sensitive columns
-- 3. Present customized data to users

-- View showing students with their department and enrolled course
CREATE VIEW student_course_view AS
SELECT s.name AS student_name, d.dept_name, c.course_name, e.enrollment_date
FROM students s
JOIN departments d ON s.dept_id = d.dept_id
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id;

SELECT * FROM student_course_view;

-- A stored procedure is a set of SQL statements that you can save and reuse. 
-- You can call it like a function to perform tasks.

-- A procedure to enroll a student in a course:
DELIMITER $$

CREATE PROCEDURE EnrollStudent(
    IN s_id INT,
    IN c_id INT,
    IN e_date DATE
)
BEGIN
    INSERT INTO enrollments (student_id, course_id, enrollment_date)
    VALUES (s_id, c_id, e_date);
END $$

DELIMITER ;
CALL EnrollStudent(2, 4, '2025-07-24');

select * from enrollments;

-- A function is like a stored procedure, but it returns a value and is usually used in SELECT queries
-- A function to get the number of courses a student is enrolled in
DELIMITER $$

CREATE FUNCTION get_course_count(sid INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM enrollments
    WHERE student_id = sid;
    RETURN total;
END $$

DELIMITER ;

SELECT name, get_course_count(student_id) AS total_courses
FROM students;

-- Indexing is like creating a "quick search" feature in your database.
-- Instead of scanning the entire table, MySQL uses the index to  jump directly to the relevant rows 

-- Creates an index named idx_enroll_date on the enrollment_date column in the enrollments table.
CREATE INDEX idx_enroll_date ON enrollments(enrollment_date);

SELECT * FROM enrollments
WHERE enrollment_date = '2025-07-12';

EXPLAIN SELECT * 
FROM enrollments 
WHERE enrollment_date = '2025-07-12';

-- Composite Index (Multi-column)
CREATE INDEX idx_student_course_composite ON enrollments(student_id, course_id);
SHOW INDEX FROM enrollments;

EXPLAIN SELECT * 
FROM enrollments 
WHERE student_id = 1 AND course_id = 2;

EXPLAIN SELECT * 
FROM enrollments 
WHERE course_id = 2 AND student_id = 1;











