-- Use the 'student_db' database.
USE student_db;

-- To Create the 'Student' table.
CREATE TABLE Student(
	id int auto_increment primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(60) unique,
    percentage decimal(5,2)
);

-- Select all data from the 'Student' table.
SELECT * FROM Student;

-- Insert multiple records into the 'Student' table.
INSERT INTO Student(first_name, last_name, email, percentage)
VALUES
("Nilesh", "Gawli", "nbgawli@gmail.com", 90.9),
("Rohit", "Sharma", "rohit@gmail.com", 70.9),
("Shikhar", "Dhawan", "dhawan@gmail.com", 66.9),
("Mahindra Singh", "Dhoni", "msd@gmail.com", 98.9),
("Virat", "Kohli", "vk@gmail.com", 69.90);

-- Add a new column to the 'Student' table.
ALTER TABLE Student
ADD COLUMN dob date null;

-- Update the column for the table with 'id'.
UPDATE Student SET dob = '2003-8-30' WHERE id = 1;
UPDATE Student SET dob = '2005-11-12' WHERE id = 2;
UPDATE Student SET dob = '2008-2-28' WHERE id = 3;
UPDATE Student SET dob = '2007-6-15' WHERE id = 4;
UPDATE Student SET dob = '2007-7-1' WHERE id = 5;

-- Count the total number of students.
SELECT COUNT(*) AS total_count
FROM Student;

-- Count students with percentage greater than 70
SELECT COUNT(*) 
FROM Student
WHERE percentage > 70;

-- Count students with age greater than 18
SELECT COUNT(*) AS student_age_over_18
FROM Student
WHERE TIMESTAMPDIFF(YEAR, dob, CURDATE()) >= 18;

-- Students with age greater than 18
SELECT *
FROM Student
WHERE TIMESTAMPDIFF(YEAR, dob, CURDATE()) >= 18;

-- Add a CHECK constraint to ensure percentage is between 0 and 100
ALTER TABLE Student
ADD CONSTRAINT chk_percentage_range CHECK (percentage >= 0 AND percentage <= 100);

-- Let's try adding a student with percentage 150
INSERT INTO Student(first_name, last_name, email, percentage, dob)
VALUES("Yuvraj", "Singh", "yuvi6@gmail.com", "150", "2006-06-6");

-- Add a CHECK constraint to ensure age is between 5 and 30 
-- won't work because expression isn't allowed in CHECK
ALTER TABLE Student
ADD CONSTRAINT chk_valid_age CHECK (
    TIMESTAMPDIFF(YEAR, dob, CURDATE()) > 5 AND
    TIMESTAMPDIFF(YEAR, dob, CURDATE()) < 30
);

-- Solution: 
ALTER TABLE student
  ADD COLUMN record_date DATE
      NOT NULL
      DEFAULT (CURDATE());
ALTER TABLE student
  ADD CONSTRAINT chk_age_10_30
    CHECK (
      dob BETWEEN
        DATE_SUB(record_date, INTERVAL 30 YEAR)   -- ≤ 30 yrs old
        AND
        DATE_SUB(record_date, INTERVAL 10 YEAR)   -- ≥ 10 yrs old
    );
    
    