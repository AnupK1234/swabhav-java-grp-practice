USE student_new_db;

CREATE TABLE Student (
    studentid INT PRIMARY KEY,
    rollnumber INT UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    percentage DECIMAL(5, 2) NOT NULL
);

INSERT INTO Student (studentid, rollnumber, name, age, percentage) VALUES
(1, 101, 'Anjali Sharma', 18, 92.50),
(2, 102, 'Ravi Verma', 19, 85.00),
(3, 103, 'Priya Deshmukh', 20, 74.00),
(4, 104, 'Amit Patel', 17, 38.00),
(5, 105, 'Sneha Joshi', 21, 64.50),
(6, 106, 'Arjun Mehta', 22, 49.00),
(7, 107, 'Akshay Kumar', 18, 55.50),
(8, 108, 'Anaya Nair', 19, 88.00),
(9, 109, 'Bhavna Iyer', 20, 91.00),
(10, 110, 'Chetan Salunkhe', 18, 33.00),
(11, 111, 'Deepak Rawat', 20, 85.00),
(12, 112, 'Asha Pawar', 19, 67.00),
(13, 113, 'Ganesh Jadhav', 22, 75.00),
(14, 114, 'Aditya Jain', 21, 92.50),
(15, 115, 'Raj Thakur', 17, 40.00);
 
-- 1. Display all columns for all students in the table.
SELECT * FROM Student;

-- 🔹 2. Show the name and roll number of students who scored more than 75%.
SELECT name, rollnumber FROM Student WHERE percentage > 75;

-- 🔹 3. List students who are older than 18 and have a percentage less than 50.
SELECT name FROM Student WHERE age > 18 AND percentage < 50;

-- 🔹 4. Display all students sorted by percentage in descending order.
SELECT * FROM Student ORDER BY percentage DESC;

-- 🔹 5. Count the total number of students in the table.
SELECT COUNT(*) FROM Student;

-- 🔹 6. Find the average percentage of students who are younger than 20.
SELECT AVG(percentage) AS avg_percentage FROM Student WHERE age < 20;

-- 🔹 7. Find the student(s) who scored the highest percentage.
-- SELECT * FROM Student ORDER BY percentage DESC LIMIT 2; -- brute force
SELECT * FROM Student WHERE percentage = (SELECT MAX(percentage) FROM Student);

-- 🔹 8. Display the number of students grouped by their age.
SELECT age, COUNT(studentid) AS countOfStudent FROM Student GROUP BY age;

-- 🔹 9. List all students whose name starts with the letter 'A'.
SELECT * FROM Student WHERE name LIKE 'a%';

-- 🔹 10. Show names and percentages of students who scored above the average percentage.
SELECT name, percentage FROM Student WHERE percentage > (SELECT AVG(percentage) FROM Student);

-- 11. Assign grades to students based on percentage:
-- A (>=90),
-- B (75–89),
-- C (60–74),
-- D (<60)

-- First Add grade column
ALTER TABLE Student
ADD COLUMN Grade VARCHAR(1);

UPDATE Student
SET Grade = CASE
    WHEN percentage >= 90 THEN 'A'
    WHEN percentage >= 75 AND percentage <= 89 THEN 'B'
    WHEN percentage >= 60 AND percentage <= 74 THEN 'C'
    ELSE 'D'
END;

-- 🔹 12. Find the second highest percentage scored by any student.
SELECT *
FROM Student
WHERE percentage = (
    SELECT DISTINCT percentage
    FROM Student
    ORDER BY percentage DESC
    LIMIT 1 OFFSET 1
);

-- 🔹 13. Create a view that contains details of all students who failed (percentage less than 40).
CREATE VIEW FailedStudentsView AS
SELECT *
FROM Student
WHERE percentage < 40;

-- To query the view:
SELECT * FROM FailedStudentsView;

-- 🔹 14.  Display the rank of each student based on their percentage using a window function.
SELECT
    studentid,
    name,
    percentage,
    DENSE_RANK() OVER (ORDER BY percentage DESC) AS StudentRank
FROM Student
ORDER BY StudentRank;

-- 🔹 15. Find the top 3 students with the highest percentage using a subquery and window function.
SELECT
    studentid,
    name,
    percentage,
    StudentRank
FROM (
    SELECT
        studentid,
        name,
        percentage,
        DENSE_RANK() OVER (ORDER BY percentage DESC) AS StudentRank
    FROM Student
) AS RankedStudents
WHERE StudentRank <= 3
ORDER BY StudentRank;