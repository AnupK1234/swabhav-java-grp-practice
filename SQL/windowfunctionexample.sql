create database windowexamples;
use windowexamples;

CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Department VARCHAR(50),
    Salary INT,
    HireDate DATE
);

INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary, HireDate) VALUES
(1, 'Alice', 'Smith', 'HR', 60000, '2015-03-01'),
(2, 'Bob', 'Johnson', 'Engineering', 95000, '2016-07-15'),
(3, 'Carol', 'Williams', 'Engineering', 87000, '2017-11-30'),
(4, 'David', 'Brown', 'Marketing', 70000, '2018-06-12'),
(5, 'Eva', 'Davis', 'HR', 63000, '2019-01-08'),
(6, 'Frank', 'Miller', 'Engineering', 99000, '2020-10-20'),
(7, 'Grace', 'Wilson', 'Marketing', 75000, '2021-04-22'),
(8, 'Hank', 'Moore', 'HR', 61000, '2022-09-03');

-- row number
SELECT 
    EmployeeID,
    FirstName,
    Department,
    Salary,
    row_number() OVER (PARTITION BY Department ORDER BY Salary DESC) AS DeptSalaryRank
FROM Employees;

-- Find the Previous Employee's Salary in Each Department (LAG)
SELECT 
    EmployeeID,
    FirstName,
    Department,
    Salary,
    LAG(Salary) OVER (PARTITION BY Department ORDER BY employeeID) AS PrevSalary
FROM Employees;


--  Rank Employees by Salary in Each Department
SELECT 
    EmployeeID,
    FirstName,
    Department,
    Salary,
    RANK() OVER (PARTITION BY Department ORDER BY Salary DESC) AS DeptSalaryRank
FROM Employees;


