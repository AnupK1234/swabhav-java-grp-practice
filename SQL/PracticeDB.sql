create database practice;
use practice;
CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    city VARCHAR(50)
);
 
INSERT INTO Students (student_id, name, age, city) VALUES
(1, 'Rohan', 22, 'Mumbai'),
(2, 'Priya', 21, 'Delhi'),
(3, 'Aman', 23, 'Bangalore'),
(4, 'Sneha', 22, 'Mumbai');

CREATE TABLE Instructors (
    instructor_id INT PRIMARY KEY,
    instructor_name VARCHAR(50) NOT NULL
);
 
INSERT INTO Instructors (instructor_id, instructor_name) VALUES
(1, 'Mr. Sharma'),
(2, 'Ms. Kapoor'),
(3, 'Mr. Khan');

CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    instructor_id INT,
    FOREIGN KEY (instructor_id) REFERENCES Instructors(instructor_id)
);
 
INSERT INTO Courses (course_id, course_name, instructor_id) VALUES
(101, 'Java Programming', 1),
(102, 'MySQL Basics', 2),
(103, 'Spring Boot', 1),
(104, 'Angular', 3);

CREATE TABLE Enrollments (
    enrollment_id INT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);
 
INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date) VALUES
(1, 1, 101, '2025-07-01'),
(2, 1, 102, '2025-07-02'),
(3, 2, 101, '2025-07-03'),
(4, 3, 103, '2025-07-05'),
(5, 4, 104, '2025-07-06');

# Show student names and the courses they are enrolled in.
SELECT s.name, c.course_name
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id;

# List all students along with their instructor’s name for each enrolled course.
SELECT s.name AS student_name, c.course_name, i.instructor_name
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id
JOIN instructors i ON c.instructor_id = i.instructor_id;

-- Find students who are enrolled in “Java Programming”.
SELECT s.name
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id
WHERE c.course_name = 'Java Programming';

-- List all students from “Mumbai” who are enrolled in any course.
SELECT s.name, s.city
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
WHERE s.city = 'Mumbai';

-- Show students who enrolled after “2025-07-02” with their course name.
SELECT s.name, c.course_name, e.enrollment_date
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id
WHERE e.enrollment_date > '2025-07-02';

-- Show all students and their enrolled courses (include students who are not enrolled in any course).
SELECT s.name AS student_name, c.course_name
FROM students s
LEFT JOIN enrollments e ON s.student_id = e.student_id
LEFT JOIN courses c ON e.course_id = c.course_id;

-- Show all courses and their enrolled students (include courses with no students enrolled).
SELECT c.course_name, s.name AS student_name
FROM courses c
LEFT JOIN enrollments e ON c.course_id = e.course_id
LEFT JOIN students s ON e.student_id = s.student_id;

-- List student names, course names, and instructor names (3-table join).
SELECT s.name AS student_name, c.course_name, i.instructor_name
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id
JOIN instructors i ON c.instructor_id = i.instructor_id;

-- Show all instructors and the students they teach (even if no student is enrolled in their course).
SELECT i.instructor_name, s.name AS student_name, c.course_name
FROM instructors i
LEFT JOIN courses c ON i.instructor_id = c.instructor_id
LEFT JOIN enrollments e ON c.course_id = e.course_id
LEFT JOIN students s ON e.student_id = s.student_id;