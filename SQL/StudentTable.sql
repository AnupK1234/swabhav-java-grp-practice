studentCREATE database Student_db;
use Student_db;
create table Student(
studentid int not null primary key,
rollnumber int not null,
name varchar(30),
age int,
percentage decimal(5,2));

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

select * from Student;

--  1. Display all columns for all students in the table.
select * from Student;

-- 🔹 2. Show the name and roll number of students who scored more than 75%.
select name,rollnumber from Student where percentage>75;

-- 🔹 3. List students who are older than 18 and have a percentage less than 50.
select * from student where age>18 and percentage<50;

-- 🔹 4. Display all students sorted by percentage in descending order.
select * from Student order by percentage desc;

-- 🔹 5. Count the total number of students in the table.
select count(*) from Student;

-- 🔹 6. Find the average percentage of students who are younger than 20.
select avg (percentage) AS avgPercentage from student where age<20;

-- 🔹 7. Find the student(s) who scored the highest percentage.
select * from Student where percentage=(select  max(percentage) from Student);

-- 🔹 8. Display the number of students grouped by their age.
select age, count(*) as count from Student group by age;

-- 🔹 9. List all students whose name starts with the letter 'A'.
select name from Student where name like "A%";

-- 🔹 10. Show names and percentages of students who scored above the average percentage.
select name,percentage from Student where percentage>(Select avg (percentage) from Student);

-- 11. Assign grades to students based on percentage:A (>=90), B (75–89), C (60–74), D (<60)
SELECT name, percentage,
    CASE
        WHEN percentage >= 90 THEN 'A'
        WHEN percentage >= 75 AND percentage < 90 THEN 'B'
        WHEN percentage >= 60 AND percentage < 75 THEN 'C'
        WHEN percentage < 60 THEN 'D'
    END AS grade
FROM Student;

-- 12. Find the second highest percentage scored by any student.
SELECT MAX(percentage) AS second_highest_percentage
FROM Student
WHERE percentage < (SELECT MAX(percentage) FROM Student);

-- 13. Create a view that contains details of all students who failed (percentage less than 40).
CREATE VIEW failed_students AS
SELECT name, percentage
FROM Student
WHERE percentage < 40;

-- 14. Display the rank of each student based on their percentage using a window function.
SELECT name,percentage, RANK() OVER (ORDER BY percentage DESC) AS rnk FROM Student;

-- 15. Find the top 3 students with the highest percentage using a subquery and window function.
SELECT name, percentage FROM (SELECT name,percentage, RANK() OVER (ORDER BY percentage DESC) AS rnk
    FROM Student) AS ranked_students WHERE rnk <= 3;

CREATE TABLE Profile (
   studentid INT PRIMARY KEY,
   city VARCHAR(50),
   mobileno VARCHAR(15),
   FOREIGN KEY (studentid) REFERENCES Student(studentid)
);

CREATE TABLE Subjects (
   subid INT PRIMARY KEY,
   studentid INT,
   subname VARCHAR(50),
   FOREIGN KEY (studentid) REFERENCES Student(studentid)
); 
 
CREATE TABLE Course (
   courseid INT PRIMARY KEY,
   coursename VARCHAR(100)
);
 
CREATE TABLE Student_Course (
   studentid INT,
   courseid INT,
   PRIMARY KEY (studentid, courseid),
   FOREIGN KEY (studentid) REFERENCES Student(studentid),
   FOREIGN KEY (courseid) REFERENCES Course(courseid)
);
  
INSERT INTO Profile VALUES
(1, 'Pune', '9876543210'),
(2, 'Mumbai', '9823456780'),
(3, 'Nagpur', '9988776655'),
(4, 'Delhi', '9911223344'),
(5, 'Pune', '9876501234'); 
 
INSERT INTO Subjects VALUES
(1, 1, 'Mathematics'),
(2, 1, 'Physics'),
(3, 2, 'Biology'),
(4, 3, 'Chemistry'),
(5, 3, 'Mathematics'),
(6, 4, 'History'),
(7, 5, 'Economics'); 
 
INSERT INTO Course VALUES
(1, 'BSc Computer Science'),
(2, 'BSc Mathematics'),
(3, 'BA History'),
(4, 'BCom Finance');
 
INSERT INTO Student_Course VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(3, 3),
(4, 3),
(5, 4),
(2, 4);

-- operations 
-- 1. List all students from ‘Pune’.
SELECT s.studentid, s.name
FROM Student s
JOIN Profile p ON s.studentid = p.studentid
WHERE p.city = 'Pune';

-- 2. Count how many students are in each city.
SELECT city, COUNT(*) AS total_students
FROM Profile
GROUP BY city;

-- 3. Find students with percentage > 80.
SELECT *
FROM Student
WHERE percentage > 80;

-- 4. List students who are enrolled in more than one course.
SELECT s.studentid, s.name
FROM Student s
JOIN Student_Course sc ON s.studentid = sc.studentid
GROUP BY s.studentid, s.name
HAVING COUNT(sc.courseid) > 1;

-- 5. Show each student's name and their subjects.
SELECT s.name, sub.subname
FROM Student s
JOIN Subjects sub ON s.studentid = sub.studentid
ORDER BY s.name;

-- 6. Find students with no profile.
SELECT s.studentid, s.name
FROM Student s
LEFT JOIN Profile p ON s.studentid = p.studentid
WHERE p.studentid IS NULL;

-- 7. List students along with their mobile number and city.
SELECT s.name, p.city, p.mobileno
FROM Student s
JOIN Profile p ON s.studentid = p.studentid;

-- 8. List all subjects taken by students from Mumbai.
SELECT DISTINCT sub.subname
FROM Subjects sub
JOIN Student s ON s.studentid = sub.studentid
JOIN Profile p ON s.studentid = p.studentid
WHERE p.city = 'Mumbai';

-- 9. Get average percentage of students per city.
SELECT p.city, AVG(s.percentage) AS avg_percentage
FROM Student s
JOIN Profile p ON s.studentid = p.studentid
GROUP BY p.city;

-- 10. Find students enrolled in ‘BSc Mathematics’ and live in Pune.
SELECT DISTINCT s.name
FROM Student s
JOIN Student_Course sc ON s.studentid = sc.studentid
JOIN Course c ON sc.courseid = c.courseid
JOIN Profile p ON s.studentid = p.studentid
WHERE c.coursename = 'BSc Mathematics' AND p.city = 'Pune';

-- 11. Get names of students who have taken both ‘Physics’ and ‘Mathematics’.
SELECT s.name
FROM Student s
JOIN Subjects sub ON s.studentid = sub.studentid
WHERE sub.subname IN ('Physics', 'Mathematics')
GROUP BY s.studentid, s.name
HAVING COUNT(DISTINCT sub.subname) = 2;

-- 12. Show students who are not enrolled in any course.
SELECT s.name
FROM Student s
LEFT JOIN Student_Course sc ON s.studentid = sc.studentid
WHERE sc.courseid IS NULL;

-- 13. Display city-wise count of students enrolled in more than one subject.
SELECT city, COUNT(*) AS student_count
FROM (
    SELECT p.city, s.studentid
    FROM Student s
    JOIN Subjects sub ON s.studentid = sub.studentid
    JOIN Profile p ON s.studentid = p.studentid
    GROUP BY s.studentid, p.city
    HAVING COUNT(sub.subid) > 1
) AS temp
GROUP BY city;

-- 14. For each student, show their name, city, all course names (comma separated), and subjects.
SELECT s.studentid, s.name, p.city,
       GROUP_CONCAT(DISTINCT c.coursename) AS courses,
       GROUP_CONCAT(DISTINCT sub.subname) AS subjects
FROM Student s
LEFT JOIN Profile p ON s.studentid = p.studentid
LEFT JOIN Student_Course sc ON s.studentid = sc.studentid
LEFT JOIN Course c ON sc.courseid = c.courseid
LEFT JOIN Subjects sub ON s.studentid = sub.studentid
GROUP BY s.studentid, s.name, p.city;

-- 15. Find the top 3 students with the highest percentage in each city.
SELECT studentid, name, city, percentage
FROM (
   SELECT s.studentid, s.name, p.city, s.percentage,
          RANK() OVER (PARTITION BY p.city ORDER BY s.percentage DESC) AS rank_in_city
   FROM Student s
   JOIN Profile p ON s.studentid = p.studentid
) ranked
WHERE rank_in_city <= 3;

-- 16. List students who have taken exactly 3 subjects.
SELECT s.name
FROM Student s
JOIN Subjects sub ON s.studentid = sub.studentid
GROUP BY s.studentid, s.name
HAVING COUNT(sub.subid) = 3;

-- 17. Show courses that no student has enrolled in.
SELECT c.coursename
FROM Course c
LEFT JOIN Student_Course sc ON c.courseid = sc.courseid
WHERE sc.courseid IS NULL;

-- 18. List students who share the same percentage.
SELECT s1.name, s1.percentage
FROM Student s1
JOIN Student s2 ON s1.percentage = s2.percentage AND s1.studentid <> s2.studentid
ORDER BY s1.percentage;

-- 19. Display the number of courses and subjects each student is enrolled in.
SELECT s.name,
       COUNT(DISTINCT sc.courseid) AS total_courses,
       COUNT(DISTINCT sub.subid) AS total_subjects
FROM Student s
LEFT JOIN Student_Course sc ON s.studentid = sc.studentid
LEFT JOIN Subjects sub ON s.studentid = sub.studentid
GROUP BY s.studentid, s.name;
