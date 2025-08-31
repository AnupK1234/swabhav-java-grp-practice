-- Create database and tables for Employee Leave Management System
CREATE DATABASE IF NOT EXISTS leave_ms;
USE leave_ms;
show tables;

-- NEW SCHEME STARTS HERE

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  first_name VARCHAR(150) NOT NULL,      
  last_name VARCHAR(150) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,      
  dob DATE,
  role ENUM('ADMIN','MANAGER','EMPLOYEE') NOT NULL,
  manager_id INT NULL,                     -- manager for EMPLOYEE, NULL for Admin/Manager
  annual_leave_quota INT NOT NULL DEFAULT 12,
  leave_balance INT NOT NULL DEFAULT 12,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (manager_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Holidays
CREATE TABLE IF NOT EXISTS holidays (
  id INT AUTO_INCREMENT PRIMARY KEY,
  holiday_date DATE NOT NULL UNIQUE,
  title VARCHAR(150) NOT NULL
);

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    date DATE NOT NULL,
    status ENUM('PRESENT','LEAVE','ABSENT') NOT NULL,
    marked_by ENUM('SELF','ADMIN','MANAGER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, date),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE leave_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason VARCHAR(255),
    status ENUM('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);



-- NEW SCHEME ENDS HERE


-- ***** OLD ONE NOT IN USE CURRENTLY ******

-- Users table (includes all roles)
-- CREATE TABLE IF NOT EXISTS users (
--   id INT AUTO_INCREMENT PRIMARY KEY,
--   username VARCHAR(100) UNIQUE NOT NULL,
--   password_hash VARCHAR(100) NOT NULL,
--   full_name VARCHAR(150) NOT NULL,
--   email VARCHAR(150) NOT NULL,
--   role ENUM('ADMIN','MANAGER','EMPLOYEE') NOT NULL,
--   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- Employee specific data (links to users)
-- CREATE TABLE IF NOT EXISTS employees (
--   id INT PRIMARY KEY, -- FK to users.id
--   manager_id INT NULL, -- FK to users.id with role MANAGER
--   annual_leave_quota INT NOT NULL DEFAULT 10,
--   leave_balance INT NOT NULL DEFAULT 10,
--   department VARCHAR(100),
--   FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE,
--   FOREIGN KEY (manager_id) REFERENCES users(id) ON DELETE SET NULL
-- );

-- Holidays
-- CREATE TABLE IF NOT EXISTS holidays (
--   id INT AUTO_INCREMENT PRIMARY KEY,
--   holiday_date DATE NOT NULL UNIQUE,
--   title VARCHAR(150) NOT NULL
-- );

-- Leave Requests
-- CREATE TABLE IF NOT EXISTS leave_requests (
--   id INT AUTO_INCREMENT PRIMARY KEY,
--   employee_id INT NOT NULL, -- FK to users.id (employee)
--   manager_id INT NULL, -- approver manager (users.id with role MANAGER)
--   start_date DATE NOT NULL,
--   end_date DATE NOT NULL,
--   days INT NOT NULL,
--   type ENUM('CASUAL','SICK','EARNED','UNPAID') NOT NULL DEFAULT 'CASUAL',
--   reason VARCHAR(500),
--   status ENUM('PENDING','APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
--   decided_by INT NULL, -- approver user id (manager or admin)
--   decided_at TIMESTAMP NULL,
--   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--   FOREIGN KEY (employee_id) REFERENCES users(id) ON DELETE CASCADE,
--   FOREIGN KEY (manager_id) REFERENCES users(id) ON DELETE SET NULL,
--   FOREIGN KEY (decided_by) REFERENCES users(id) ON DELETE SET NULL
-- );

-- Seed users (passwords are 'admin123', 'manager123', 'emp123' hashed with BCrypt - will be created by a Java bootstrap, or insert directly later)
-- For convenience, you may insert plaintext and let app update on first login, but secure approach is to insert BCrypt.

-- Sample holidays
INSERT INTO holidays(holiday_date, title) VALUES
  ('2025-01-01','New Year'), ('2025-08-15','Independence Day')
ON DUPLICATE KEY UPDATE title=VALUES(title);

SELECT * FROM EMPLOYEES;
SELECT * FROM HOLIDAYS;
SELECT * FROM LEAVE_REQUESTS;
SELECT * FROM USERS;

INSERT INTO users (username,password_hash,full_name,email,role)
VALUES ('admin', '$2a$10$7QO0u7z5G2iN8oT.XN2Y6u9G2u3J6KfSg5h8YV6G2XWbI9WJv8VbG', 'Site Admin', 'admin@example.com', 'ADMIN');
-- password = admin123
