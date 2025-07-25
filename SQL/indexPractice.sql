CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(10,2),
    stock INT,
    INDEX (category)  -- Adding index on 'category'
);

CREATE TABLE products2 (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(10,2),
    stock INT,
    INDEX (category, stock)  -- Adding index on 'category'
);

INSERT INTO products2 (name, category, price, stock) VALUES
('iPhone', 'Electronics', 799.99, 10),
('T-shirt', 'Clothing', 19.99, 50),
('Laptop', 'Electronics', 1200.00, 5),
('Jeans', 'Clothing', 49.99, 30),
('Headphones', 'Electronics', 199.99, 25);

EXPLAIN SELECT * FROM products WHERE product_id = 1;
EXPLAIN SELECT * FROM products2 WHERE category = 'Electronics';

EXPLAIN SELECT name FROM products
WHERE price > (SELECT AVG(price) FROM products);

SHOW INDEX FROM products;

