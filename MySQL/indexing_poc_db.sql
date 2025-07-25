-- Create the database
CREATE DATABASE IF NOT EXISTS indexing_poc_db;
USE indexing_poc_db;

-- Create a large 'users' table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    registration_date DATE,
    city VARCHAR(50),
    country VARCHAR(50),
    age INT
);