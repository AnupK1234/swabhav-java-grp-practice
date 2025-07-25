use anup;
CREATE TABLE Cities (
    city_id INT PRIMARY KEY,
    city_name VARCHAR(50)
);

CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    city_id INT,
    FOREIGN KEY (city_id) REFERENCES Cities(city_id)
);

CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100)
);

CREATE TABLE Enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    percentage DECIMAL(5,2),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

INSERT INTO Cities VALUES
(1, 'Mumbai'),
(2, 'Pune'),
(3, 'Nagpur');

INSERT INTO Students VALUES
(101, 'Anjali Sharma', 1),
(102, 'Ravi Verma', 2),
(103, 'Sneha Kulkarni', 3),
(104, 'Amit Joshi', 1),
(105, 'Priya Deshmukh', 2);

INSERT INTO Courses VALUES
(205, 'EVS');

INSERT INTO Enrollments (student_id, course_id, percentage) VALUES
(101, 201, 85.5),
(101, 202, 78.0),
(102, 201, 90.0),
(102, 203, 88.5),
(103, 202, 91.2),	
(104, 204, 72.0),
(105, 201, 69.5),
(105, 202, 75.0),
(105, 203, 80.0);

# Show student names and the courses they are enrolled in.
SELECT * FROM STUDENTS S
INNER JOIN COURSES C;

-- Show all students along with the names of courses they are enrolled in.
SELECT S.STUDENT_NAME, C.COURSE_NAME FROM STUDENTS S
JOIN ENROLLMENTS E ON S.STUDENT_ID=E.STUDENT_ID
JOIN COURSES C ON C.COURSE_ID=E.COURSE_ID;


SELECT S.*, C.CITY_NAME FROM STUDENTS S
JOIN CITIES C
ON S.CITY_ID=C.CITY_ID;


-- Display the names of students who are not enrolled in any course.
SELECT S.STUDENT_NAME, C.COURSE_NAME FROM STUDENTS S
JOIN ENROLLMENTS E ON S.STUDENT_ID=E.STUDENT_ID
JOIN COURSES C ON C.COURSE_ID=E.COURSE_ID;

-- List all courses that have not been enrolled by any student.
SELECT * FROM COURSES C
LEFT JOIN ENROLLMENTS E ON C.COURSE_ID=E.COURSE_ID
WHERE E.ENROLLMENT_ID IS NULL;




