use student_db_3;
-- MySQL Demo Schema for Teaching Joins
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS student_profiles;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(100) NOT NULL
);

CREATE TABLE teachers (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    dept_id    INT,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    email      VARCHAR(100)
);

CREATE TABLE student_profiles (
    student_id INT PRIMARY KEY,
    dob        DATE,
    phone      VARCHAR(20),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
);

CREATE TABLE courses (
    course_id  INT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(100),
    dept_id    INT,
    teacher_id INT,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id)
);

CREATE TABLE enrollments (
    student_id INT,
    course_id  INT,
    enroll_date DATE,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id)  REFERENCES courses(course_id)
);

INSERT INTO departments (name) VALUES
('Computer Science'),
('Mathematics'),
('Physics'),
('History'),
('Literature');

INSERT INTO teachers (first_name, last_name, dept_id) VALUES
('John',  'Smith', 1),
('Anne',  'Clark', 2),
('Raj',   'Kumar', 3),
('Maria', 'Gomez', 4),
('Wei',   'Zhang', NULL);

INSERT INTO courses (title, dept_id, teacher_id) VALUES
('Database Systems', 1, 1),
('Algorithms',       1, 1),
('Calculus I',       2, 2),
('Linear Algebra',   2, NULL),
('Quantum Mechanics',3, 3),
('Classical Mechanics',3, 3),
('World History',    4, 4),
('Creative Writing', 5, NULL);

INSERT INTO students (first_name, last_name, email) VALUES
('Amit',   'Shah',   'amit@example.com'),
('Nina',   'Patel',  'nina@example.com'),
('Carlos', 'Diaz',   'carlos@example.com'),
('Sara',   'Lee',    'sara@example.com'),
('Tom',    'Brown',  'tom@example.com'),
('Liu',    'Yang',   'liu@example.com'),
('Emma',   'Jones',  'emma@example.com'),
('Raj',    'Singh',  'raj@example.com'),
('Fatima', 'Khan',   'fatima@example.com'),
('Oliver', 'Smith',  'oliver@example.com');

INSERT INTO student_profiles VALUES
(1, '2005-05-10', '9876543210'),
(2, '2004-10-15', '9876543211'),
(3, '2003-03-22', '9876543212'),
(4, '2005-12-05', '9876543213'),
(5, '2004-07-18', '9876543214'),
(6, '2003-11-09', '9876543215'),
(7, '2004-01-30', '9876543216'),
(8, '2005-09-14', '9876543217');

INSERT INTO enrollments VALUES
(1, 1, '2025-01-10'),
(1, 3, '2025-01-12'),
(2, 1, '2025-01-11'),
(3, 2, '2025-01-15'),
(3, 3, '2025-01-15'),
(4, 4, '2025-01-17'),
(5, 5, '2025-01-18'),
(6, 6, '2025-01-19'),
(7, 7, '2025-01-20');

-- 1  List all students who have enrolled in at least one course.
select distinct s.first_name from students s 
inner join enrollments e on s.student_id= e.student_id;
-- 2  List all courses along with the department name.
select c.title, d.name from courses c
left join departments d on c.dept_id= d.dept_id;
-- 3  Find all students and their date of birth, even if no profile is available.
select s.first_name , p.dob from students s
left join student_profiles p on s.student_id = p.student_id;
-- 4  List all teachers along with their department, including those not assigned.
select t.first_name , d.name from teachers t
left join departments d on t.dept_id= d.dept_id;
-- 5  List all students and the courses they are enrolled in (even if not enrolled).
select s.first_name, c.title from students s 
left join enrollments e on s.student_id = e.student_id
left join courses c on e.course_id= c.course_id;
-- 6  Display each department and the total number of courses offered.
	select d.name , count(c.title) as totalNoOfCourse from departments d 
	left join courses c on d.dept_id = c.dept_id
	group by d.name;
-- 7  List all students who have enrolled in the 'Algorithms' course.
select s.first_name from students s
inner join enrollments e on s.student_id = e.student_id
inner join courses c on e.course_id = c.course_id
where c.title= 'Algorithms';
-- 8  Show all combinations of students and courses.
select s.first_name,c.title from students s
cross join courses c;
-- 9  List each student with total number of courses enrolled (0 if none).
select s.first_name,count(e.course_id) as TotalCourses from students s
left join enrollments e on s.student_id = e.student_id
group by s.student_id;

-- 10  List all courses that have never been taken by any student.
select c.title from courses c 
left join enrollments e on c.course_id= e.course_id
left join students s on e.student_id= s.student_id
where e.student_id is null;