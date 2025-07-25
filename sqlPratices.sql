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


-- List all students along with their instructor’s name for each enrolled course.
select s.name , i.instructor_name,c.course_name from Students s
inner join enrollments e on e.student_id = s.student_id
inner join courses c on c.course_id = e.course_id
inner join Instructors i on i.instructor_id = c.instructor_id;

-- Find students who are enrolled in “Java Programming”.

select s.name, c.course_name from students s 
inner join Enrollments e on s.student_id = e.student_id
inner join courses c on c.course_id =  e.course_id
where c.course_name = 'Java Programming';

-- List all students from “Mumbai” who are enrolled in any course.
select s.name, c.course_name,s.city from students s 
inner join enrollments e on s.student_id = e.student_id
inner join courses c on c.course_id = e.course_id
where s.city = 'Mumbai';

-- Show students who enrolled after “2025-07-02” with their course name.
select s.name,c.course_name,e.enrollment_date from students s 
inner join enrollments e on s.student_id= e.student_id
inner join courses c on c.course_id = e.course_id
where enrollment_date='2025-07-02';

-- Show all students and their enrolled courses (include students who are not enrolled in any course).
select s.name,c.course_name from students s
left join enrollments e on s.student_id = e.student_id
left join courses c on c.course_id = e.course_id;

-- Show all courses and their enrolled students (include courses with no students enrolled).

select c.course_name,s.name from courses c
left join enrollments e on c.course_id = e.course_id
left join students s on s.student_id = e.student_id;

-- List student names, course names, and instructor names (3-table join).
select s.name,c.course_name,i.instructor_name from students s
inner join enrollments e on s.student_id = e.student_id
inner join courses c on c.course_id = e.course_id
inner join instructors i on c.instructor_id =  i.instructor_id;

-- Show all instructors and the students they teach (even if no student is enrolled in their course).
select i.instructor_name, s.name from instructors i
left join courses c on c.instructor_id = i.instructor_id
left join enrollments e on c.course_id = e.course_id
left join students s on s.student_id = e.student_id;

-- Count how many students are enrolled in each course.
select c.course_name ,count(*) as studentEnroll from courses c
inner join enrollments e on c.course_id = e.course_id
group by c.course_name;






CREATE TABLE users (
    user_id INT PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE user_audit (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    action_type VARCHAR(20),
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER $$
CREATE TRIGGER after_user_insert
AFTER INSERT ON users
FOR EACH ROW
BEGIN
    INSERT INTO user_audit(user_id, action_type)
    VALUES (NEW.user_id, 'INSERT');
END $$
DELIMITER ;

INSERT INTO users(user_id, name) VALUES (2, 'Anup');
select * from user_audit;
select * from users;

DELIMITER $$
CREATE TRIGGER after_user_update
AFTER UPDATE ON users
FOR EACH ROW
BEGIN
    INSERT INTO user_audit(user_id, action_type)
    VALUES (NEW.user_id, 'UPDATE');
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_user_delete
AFTER DELETE ON users
FOR EACH ROW
BEGIN
    INSERT INTO user_audit(user_id, action_type)
    VALUES (OLD.user_id, 'DELETE');
END $$
DELIMITER ;

UPDATE users SET name = 'Rohan Maurya' WHERE user_id = 1;
DELETE FROM users WHERE user_id = 1;
select * from user_audit;
select * from users;
delete users  from users where name ='ANUP' and user_id = 2;

DELIMITER %%
CREATE TRIGGER before_user_insert
BEFORE INSERT ON users
FOR EACH ROW 
BEGIN IF NEW.name = '' THEN
signal sqlstate '45000'
SET MESSAGE_TEXT = 'NAME CANNOT BE EMPTY!!!';
END IF;
END %%
DELIMITER ;
INSERT INTO users(user_id, name) VALUES (4, '');

DELIMITER $$
CREATE TRIGGER before_user_insert
BEFORE INSERT ON users
FOR EACH ROW 
BEGIN 
 SET NEW.name= upper(new.name);
 END$$
 DELIMITER ;
 
 DROP TRIGGER  before_user_insert ;
select * from users;