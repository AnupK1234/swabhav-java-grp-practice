-- Create the database
CREATE DATABASE IF NOT EXISTS sp_view_poc_db;
USE sp_view_poc_db;

-- 1. Create the 'customers' table
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    registration_date DATE
);

-- 2. Create the 'orders' table
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Insert some sample data into customers
INSERT INTO customers (first_name, last_name, email, registration_date) VALUES
('Alice', 'Johnson', 'alice.j@example.com', '2023-01-10'),
('Bob', 'Williams', 'bob.w@example.com', '2023-02-15'),
('Charlie', 'Brown', 'charlie.b@example.com', '2023-03-20'),
('Diana', 'Prince', 'diana.p@example.com', '2023-04-01');

-- Insert some sample data into orders
INSERT INTO orders (customer_id, order_date, total_amount, status) VALUES
(1, '2023-01-20', 150.75, 'Completed'),
(1, '2023-03-05', 29.99, 'Pending'),
(2, '2023-02-28', 500.00, 'Completed'),
(3, '2023-04-10', 75.50, 'Shipped'),
(1, '2023-05-01', 120.00, 'Completed'),
(2, '2023-06-15', 30.00, 'Pending'),
(4, '2023-04-05', 250.00, 'Completed');