SET SQL_SAFE_UPDATES = 0;

use trigger_poc_db;
-- Insert a new employee (AFTER INSERT trigger will fire)
INSERT INTO employees (first_name, last_name, salary, hire_date)
VALUES ('Nilesh', 'Gawli', 160000.00, '2026-06-9');

-- Insert another employee
INSERT INTO employees (first_name, last_name, salary, hire_date)
VALUES ('Rohit', 'Sharma', 75000.00, '2022-05-20');

SELECT * FROM employees;
SELECT * FROM employee_audits; -- Check audit table after inserts

-- UPDATE EMPLOYEE SALARY

-- Update an employee's salary (AFTER UPDATE trigger will fire)
UPDATE employees
SET salary = 165000.00
WHERE first_name = 'Nilesh' AND last_name = 'Gawli';

-- Update another employee's salary
UPDATE employees
SET salary = 80000.00
WHERE first_name = 'Rohit' AND last_name = 'Sharma';

-- Update a field that is NOT salary (AFTER UPDATE trigger will NOT log if salary is same)
UPDATE employees
SET hire_date = '2023-01-20'
WHERE first_name = 'Nilesh' AND last_name = 'Gawli';


SELECT * FROM employees;
SELECT * FROM employee_audits; -- Check audit table after updates

--  DELETE EMPLOYEE

-- Delete an employee (AFTER DELETE trigger will fire)
DELETE FROM employees
WHERE first_name = 'Rohit' AND last_name = 'Sharma';

SELECT * FROM employees;
SELECT * FROM employee_audits; -- Check audit table after deletes

-- BEFORE INSERT
-- This insert should succeed
INSERT INTO products (product_name, price) VALUES ('Laptop', 1200.00);

-- This insert should fail due to the BEFORE INSERT trigger
INSERT INTO products (product_name, price) VALUES ('Headphones', -50.00);

SELECT * FROM products; -- Verify successful insert


-- RESETTING
USE student_db;
DROP DATABASE IF EXISTS trigger_poc_db;
 
