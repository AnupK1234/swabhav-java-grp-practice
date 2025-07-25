use student_1;

CREATE TABLE Student (
    studentid INT PRIMARY KEY,
    rollno INT UNIQUE,       	
    studentname VARCHAR(100),
    age INT,                 
    percentage DECIMAL(5, 2)
);


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
 
INSERT INTO Student VALUES
(1, 101, 'Vivek', 20, 85.50),
(2, 102, 'Mayur', 21, 78.20),
(3, 103, 'Nilesh', 19, 92.10),
(4, 104, 'Rohan', 22, 65.00),
(5, 105, 'Anup', 20, 89.90);
 
INSERT INTO Profile VALUES
( 1, 'Pune', '9876543210'),
( 2, 'Mumbai', '9823456780'),
( 3, 'Nagpur', '9988776655'),
( 4, 'Delhi', '9911223344'),
( 5, 'Pune', '9876501234');
 
 
 
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
 
 
--  1. List all students from ‘Pune’.

-- 2. Count how many students are in each city.

-- 3. Find students with percentage > 80.

-- 4. List students who are enrolled in more than one course.


-- 5. Show each student's name and their subjects.
--  
-- 6. Find students with no profile.
--  
-- 7. List students along with their mobile number and city.
--  
-- 8. List all subjects taken by students from Mumbai.
--  
-- 9. Get average percentage of students per city.
--  
-- 10. Find students who are enrolled in ‘BSc Mathematics’ and live in Pune.
--  
-- 11. Get names of students who have taken both ‘Physics’ and ‘Mathematics’.
--  
-- 12. Show students who are not enrolled in any course.
--  
-- 13. Display city-wise count of students enrolled in more than one subject.
--  
-- 14. For each student, show their name, city, all course names (comma separated if possible), and subjects. Number them.
--  
-- 15. Find the top 3 students with the highest percentage in each city.
--  
-- 16. List students who have taken exactly 3 subjects.
--  
-- 17. Show courses that no student has enrolled in.
--  
-- 18. List students who share the same percentage.
--  
-- 19. Display the number of courses and subjects each student is enrolled in.