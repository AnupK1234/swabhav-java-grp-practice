-- Drop if exists
DROP TABLE IF EXISTS audit_log;
DROP TABLE IF EXISTS employees;
use practice2;
-- Create employees table
CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    salary DECIMAL(10, 2)
);

-- Create audit_log table
CREATE TABLE audit_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_id INT,
    action_type VARCHAR(20),
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    old_salary DECIMAL(10, 2),
    new_salary DECIMAL(10, 2)
);

-- trigger before insert
DELIMITER //
CREATE TRIGGER before_insert_employee
BEFORE INSERT ON employees
FOR EACH ROW
BEGIN
    IF NEW.salary < 0 THEN
        SET NEW.salary = 0;
    END IF;
END;
//
DELIMITER ;
select * from employees;
INSERT INTO employees (emp_id, name, department, salary)
VALUES (1, 'Shyam', 'HR', -5000); 


-- trigger after insert
DELIMITER //
CREATE TRIGGER after_insert_employee
AFTER INSERT ON employees
FOR EACH ROW
BEGIN
    INSERT INTO audit_log(emp_id, action_type, new_salary)
    VALUES (NEW.emp_id, 'INSERT', NEW.salary);
END //
DELIMITER ;
INSERT INTO employees (emp_id, name, department, salary)
VALUES (2, 'Ram', 'Finance', 7000);

SELECT * FROM audit_log;

-- trigger before update 
DELIMITER //
CREATE TRIGGER before_update_employee
BEFORE UPDATE ON employees
FOR EACH ROW
BEGIN
    IF NEW.salary < OLD.salary * 0.5 THEN
        SET NEW.salary = OLD.salary; 
    END IF;
END //
DELIMITER ;
UPDATE employees
SET salary = 1000
WHERE emp_id = 2;

SELECT * FROM employees where emp_id = 2;

-- trigger after update
DELIMITER //
CREATE TRIGGER after_update_employee
AFTER UPDATE ON employees
FOR EACH ROW
BEGIN
    INSERT INTO audit_log(emp_id, action_type, old_salary, new_salary)
    VALUES (OLD.emp_id, 'UPDATE', OLD.salary, NEW.salary);
END // 
DELIMITER ;
UPDATE employees
SET salary = 8000
WHERE emp_id = 2;

select * from audit_log;
SELECT * FROM audit_log WHERE action_type = 'UPDATE';

-- trigger before delete 
DELIMITER //
CREATE TRIGGER before_delete_employee
BEFORE DELETE ON employees
FOR EACH ROW
BEGIN
    INSERT INTO audit_log(emp_id, action_type, old_salary)
    VALUES (OLD.emp_id, 'BEFORE DELETE', OLD.salary);
END//
DELIMITER ;

select * from employees;
select * from audit_log;
DELETE FROM employees WHERE emp_id = 1;
SELECT * FROM audit_log WHERE action_type = 'BEFORE DELETE';

DELIMITER //
CREATE TRIGGER after_delete_employee
AFTER DELETE ON employees
FOR EACH ROW
BEGIN
    INSERT INTO audit_log(emp_id, action_type, old_salary)
    VALUES (OLD.emp_id, 'DELETE', OLD.salary);
END //
DELIMITER ;

SELECT * FROM employees;
DELETE FROM employees WHERE emp_id = 2;
SELECT * FROM audit_log WHERE emp_id = 2 AND action_type = 'DELETE';

-- view example
CREATE VIEW active_employees AS
SELECT emp_id, name, department, salary
FROM employees;

select * from active_employees;

-- store procedure example
show tables;
DELIMITER $$
CREATE PROCEDURE getsomedata()
BEGIN
	SELECT * FROM employees;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE getsomedata(IN ID INT)
BEGIN
	SELECT * FROM employees WHERE emp_id=ID;
	SELECT * FROM audit_log;
END $$
DELIMITER ;

call getsomedata();






