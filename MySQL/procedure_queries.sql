-- Call the stored procedure for customer ID 1 (Alice Johnson)
SET @total_spent = 0; -- Initialize the session variable for the OUT parameter
CALL GetCustomerOrderSummary(1, @total_spent);
SELECT @total_spent AS Alice_Total_Spent;

-- Call the stored procedure for customer ID 2 (Bob Williams)
SET @total_spent = 0;
CALL GetCustomerOrderSummary(2, @total_spent);
SELECT @total_spent AS Bob_Total_Spent;

-- Call for a customer with no orders (e.g., ID 4 - Diana Prince)
SET @total_spent = 0;
CALL GetCustomerOrderSummary(4, @total_spent);
SELECT @total_spent AS Diana_Total_Spent;

-- Call for a non-existent customer (will return 0 for total_spent and no orders)
SET @total_spent = 0;
CALL GetCustomerOrderSummary(99, @total_spent);
SELECT @total_spent AS NonExistent_Customer_Total_Spent;