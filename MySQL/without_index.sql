use indexing_poc_db;
-- Analyze table for accurate statistics (helps EXPLAIN output)
ANALYZE TABLE users;

-- Query 1: Search by city (no index)
SELECT SQL_NO_CACHE * FROM users WHERE city = 'Mumbai';
-- Measure the time taken by this query. (e.g., in MySQL Workbench, or using `SET profiling = 1;` then `SHOW PROFILES;`)

-- Query 2: Search by age range (no index)
SELECT SQL_NO_CACHE COUNT(*) FROM users WHERE age BETWEEN 25 AND 35;
-- Measure the time taken by this query.

-- Explain the query plan for Query 1 (no index)
EXPLAIN SELECT * FROM users WHERE city = 'Mumbai';
-- Look for 'Type': 'ALL' (full table scan)