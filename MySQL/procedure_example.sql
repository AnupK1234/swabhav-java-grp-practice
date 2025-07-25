DELIMITER $$

CREATE PROCEDURE GetCustomerOrderSummary(
    IN p_customer_id INT,
    OUT p_total_spent DECIMAL(10, 2)
)
BEGIN
    -- Declare a variable to hold the calculated total
    DECLARE v_total_spent DECIMAL(10, 2);

    -- Calculate the total amount spent by the customer
    SELECT SUM(total_amount)
    INTO v_total_spent
    FROM orders
    WHERE customer_id = p_customer_id;

    -- Assign the calculated total to the OUT parameter
    SET p_total_spent = COALESCE(v_total_spent, 0); -- Use COALESCE to handle cases where no orders exist

    -- Select all orders for the given customer (demonstrating data retrieval)
    SELECT
        o.order_id,
        o.order_date,
        o.total_amount,
        o.status,
        c.first_name,
        c.last_name
    FROM
        orders o
    JOIN
        customers c ON o.customer_id = c.customer_id
    WHERE
        o.customer_id = p_customer_id
    ORDER BY
        o.order_date DESC;

END$$

DELIMITER ;