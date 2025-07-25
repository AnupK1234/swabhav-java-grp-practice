CREATE DATABASE student_trigger_db;
USE student_trigger_db;

-- A trigger is a special procedure that automatically runs in response to an INSERT, UPDATE, or DELETE on a table.
-- Use cases: Logging, Auditing, Automatically updating other tables

-- Students Table
CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    city VARCHAR(100)
);

-- Log Table
CREATE TABLE StudentLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    message TEXT,
    log_time DATETIME DEFAULT NOW()
);

-- Trigger on Insert a row 
DELIMITER $$
CREATE TRIGGER after_student_insert --  defining a new trigger named 
AFTER INSERT ON Students
FOR EACH ROW
BEGIN
    INSERT INTO StudentLog (message)
    VALUES (CONCAT('New student added: ', NEW.name, ' from ', NEW.city));
END$$
DELIMITER ;

INSERT INTO Students (name, city)
VALUES ('Vivek Dhalkari', 'Mumbai');

SELECT * FROM StudentLog;

-- Trigger on Delete Operation
DELIMITER $$
CREATE TRIGGER after_student_delete
AFTER DELETE ON Students
FOR EACH ROW
BEGIN
    INSERT INTO StudentLog (message)
    VALUES (
        CONCAT('Student ', OLD.name, ' from ', OLD.city, ' was deleted.')
    );
END$$
DELIMITER ;

DELETE FROM Students WHERE student_id = 1;
SELECT * FROM StudentLog;

select * from students;

-- Trigger on Update
DELIMITER $$
CREATE TRIGGER after_name_update
AFTER UPDATE ON Students
FOR EACH ROW
BEGIN
    IF OLD.name <> NEW.name THEN
        INSERT INTO StudentLog (message)
        VALUES (
            CONCAT(
                'Student ID ', NEW.student_id,
                ' changed name from "', OLD.name,
                '" to "', NEW.name, '"'
            )
        );
    END IF;
END$$
DELIMITER ;

SELECT * FROM Students;

UPDATE Students
SET name = 'Raj Singh'
WHERE student_id = 2;

SELECT * FROM StudentLog;














