-- Create an index on the 'city' column
CREATE INDEX idx_city ON users (city);

-- Create an index on the 'age' column
CREATE INDEX idx_age ON users (age);

-- Create a composite index on (city, country) for specific queries
CREATE INDEX idx_city_country ON users (city, country);

-- Optional: Create a prefix index on email if emails are very long
-- CREATE INDEX idx_email_prefix ON users (email(10));

-- Query 1: Search by city (with index)
SELECT SQL_NO_CACHE * FROM users WHERE city = 'Mumbai';
-- Measure the new time taken. It should be significantly faster.
s
-- Query 2: Search by age range (with index)
SELECT SQL_NO_CACHE COUNT(*) FROM users WHERE age BETWEEN 25 AND 35;
-- Measure the new time taken. It should be significantly faster.

-- Query 3: Search using composite index (city and country)
SELECT SQL_NO_CACHE * FROM users WHERE city = 'London' AND country = 'UK';
-- Measure the time taken.

-- Explain the query plan for Query 1 (with index)
EXPLAIN SELECT * FROM users WHERE city = 'Mumbai';
-- Look for 'Type': 'ref', 'range', or 'eq_ref' (indicating index usage)
-- Look for 'Key': 'idx_city' (the index being used)
-- Look for 'Rows': A much smaller number than total rows.