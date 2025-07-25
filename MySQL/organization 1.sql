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
 
INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date) 
VALUES
(1, 1, 101, '2025-07-01'),
(2, 1, 102, '2025-07-02'),
(3, 2, 101, '2025-07-03'),
(4, 3, 103, '2025-07-05'),
(5, 4, 104, '2025-07-06');

 