-- Create the database
CREATE DATABASE IF NOT EXISTS trigger_poc_db;
USE trigger_poc_db;

-- 1. Create the 'employees' table
CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2),
    hire_date DATE
);

-- 2. Create an 'employee_audits' table to log changes
CREATE TABLE employee_audits (
    audit_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    change_type VARCHAR(10), -- e.g., 'INSERT', 'UPDATE', 'DELETE'
    old_salary DECIMAL(10, 2),
    new_salary DECIMAL(10, 2),
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    changed_by VARCHAR(255)
);

-- Optional: Create a table for demonstrating BEFORE triggers and validation
CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

