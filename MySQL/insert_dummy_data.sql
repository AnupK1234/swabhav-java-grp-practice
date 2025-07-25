-- Clean up previous procedure if it exists
DROP PROCEDURE IF EXISTS insert_dummy_users;

DELIMITER $$

CREATE PROCEDURE insert_dummy_users(IN num_rows INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_first_name VARCHAR(50);
    DECLARE random_last_name VARCHAR(50);
    DECLARE random_email VARCHAR(100);
    DECLARE random_city VARCHAR(50);
    DECLARE random_country VARCHAR(50);
    DECLARE random_age INT;
    DECLARE random_date DATE;

    -- Using temporary tables or explicit arrays for better handling than ELT() with long strings
    -- Create temporary tables for name parts
    DROP TEMPORARY TABLE IF EXISTS temp_first_names;
    CREATE TEMPORARY TABLE temp_first_names (name_val VARCHAR(50));
    INSERT INTO temp_first_names (name_val) VALUES
    ('John'), ('Jane'), ('Peter'), ('Alice'), ('Bob'), ('Charlie'), ('David'), ('Eve'), ('Frank'), ('Grace'),
    ('Heidi'), ('Ivan'), ('Judy'), ('Kevin'), ('Liam'), ('Mia'), ('Noah'), ('Olivia'), ('Paul'), ('Quinn'); -- 20 names

    DROP TEMPORARY TABLE IF EXISTS temp_last_names;
    CREATE TEMPORARY TABLE temp_last_names (name_val VARCHAR(50));
    INSERT INTO temp_last_names (name_val) VALUES
    ('Doe'), ('Smith'), ('Johnson'), ('Williams'), ('Brown'), ('Jones'), ('Garcia'), ('Miller'), ('Davis'), ('Rodriguez'),
    ('Martinez'), ('Hernandez'), ('Lopez'), ('Gonzalez'), ('Wilson'), ('Anderson'), ('Thomas'), ('Taylor'), ('Moore'), ('Jackson'); -- 20 names

    DROP TEMPORARY TABLE IF EXISTS temp_cities;
    CREATE TEMPORARY TABLE temp_cities (city_val VARCHAR(50));
    INSERT INTO temp_cities (city_val) VALUES
    ('New York'), ('London'), ('Paris'), ('Tokyo'), ('Rome'), ('Berlin'), ('Sydney'), ('Dubai'), ('Mumbai'), ('Beijing'),
    ('Cairo'), ('Rio de Janeiro'), ('Moscow'), ('Madrid'), ('Toronto'), ('Mexico City'), ('Chicago'), ('San Francisco'), ('Amsterdam'), ('Seoul'); -- 20 cities

    DROP TEMPORARY TABLE IF EXISTS temp_countries;
    CREATE TEMPORARY TABLE temp_countries (country_val VARCHAR(50));
    INSERT INTO temp_countries (country_val) VALUES
    ('USA'), ('UK'), ('France'), ('Japan'), ('Italy'), ('Germany'), ('Australia'), ('UAE'), ('India'), ('China'),
    ('Egypt'), ('Brazil'), ('Russia'), ('Spain'), ('Canada'), ('Mexico'), ('Netherlands'), ('South Korea'), ('Argentina'), ('Sweden'); -- 20 countries

    -- Get counts of possible values
    SET @first_name_count = (SELECT COUNT(*) FROM temp_first_names);
    SET @last_name_count = (SELECT COUNT(*) FROM temp_last_names);
    SET @city_count = (SELECT COUNT(*) FROM temp_cities);
    SET @country_count = (SELECT COUNT(*) FROM temp_countries);


    START TRANSACTION;
    WHILE i < num_rows DO
        -- Generate random data by selecting from temporary tables
        SELECT name_val INTO random_first_name
        FROM temp_first_names
        ORDER BY RAND() LIMIT 1;

        SELECT name_val INTO random_last_name
        FROM temp_last_names
        ORDER BY RAND() LIMIT 1;

        SELECT city_val INTO random_city
        FROM temp_cities
        ORDER BY RAND() LIMIT 1;

        SELECT country_val INTO random_country
        FROM temp_countries
        ORDER BY RAND() LIMIT 1;

        -- Ensure email is unique by appending a counter
        SET random_email = CONCAT(
            LOWER(REPLACE(random_first_name, ' ', '')), '.',
            LOWER(REPLACE(random_last_name, ' ', '')), '_',
            LPAD(i, 7, '0'), '@example.com'
        );

        SET random_age = 18 + FLOOR(RAND() * 50); -- Age between 18 and 67
        SET random_date = DATE_SUB('2025-01-01', INTERVAL FLOOR(RAND() * 365 * 5) DAY); -- Dates over last 5 years

        INSERT INTO users (first_name, last_name, email, registration_date, city, country, age)
        VALUES (random_first_name, random_last_name, random_email, random_date, random_city, random_country, random_age);

        SET i = i + 1;
    END WHILE;
    COMMIT;

    -- Clean up temporary tables
    DROP TEMPORARY TABLE IF EXISTS temp_first_names;
    DROP TEMPORARY TABLE IF EXISTS temp_last_names;
    DROP TEMPORARY TABLE IF EXISTS temp_cities;
    DROP TEMPORARY TABLE IF EXISTS temp_countries;

END$$

DELIMITER ;

-- Make sure to run the database and table creation first
-- Then call the procedure to insert 1 million rows (adjust as needed, e.g., 1000000)
CALL insert_dummy_users(10000);	

-- Verify the number of rows
SELECT COUNT(*) FROM users;