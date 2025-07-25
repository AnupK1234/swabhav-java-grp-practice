-- #Queries
-- #0. show details of all employee
SELECT * FROM EMP;

-- #1. Display all employee names in ascending order
SELECT ENAME FROM EMP ORDER BY ENAME ASC;

-- #2. Display all employees(all columns) in department 20 and 30
SELECT * FROM EMP WHERE DEPTNO = 20 OR DEPTNO = 30;
--  SELECT * FROM EMP WHERE DEPTNO IN(20,30);

-- #3. Display all the employees who are managers
SELECT * FROM EMP WHERE JOB = "MANAGER";
-- SELECT * FROM EMP WHERE JOB IN ("MANAGER");

-- #4. Display all the employees whose salary is between 2000 and 5000
-- SELECT * FROM EMP WHERE SAL >=2000 AND SAL <= 5000;
SELECT * FROM EMP WHERE SAL BETWEEN 2000 AND 5000;


-- #5. Display all the employees whose commission is null
SELECT * FROM EMP WHERE COMM IS NULL;

-- #6. Display emp_name,salary,comission,ctc(calculated column)
SELECT ENAME, SAL, COMM, (SAL + COALESCE(COMM, 0)) AS CTC
FROM EMP;

-- #7. Display hire_date, current_date, tenure(calculated col) of the empl
SELECT HIREDATE, current_date() AS CURRENTDATE, DATEDIFF(CURRENT_DATE, HIREDATE) / 365 AS TENURE
FROM EMP;

-- SELECT HIREDATE, current_date() AS CURRENTDATE, TIMESTAMPDIFF(YEAR, CURRENT_DATE, HIREDATE) /365 AS TENURE;

-- #8. Display all the employees whose name starts with s
SELECT * FROM EMP WHERE ENAME LIKE('S%');

-- #9. Display unique department numbers from the employee table
SELECT DISTINCT DEPTNO FROM EMP;

-- #10. Display emp_name and job in lower case
SELECT LOWER(ENAME), LOWER(JOB) FROM EMP;

#11. Select top 3 salary earning employee
SELECT * FROM EMP ORDER BY SAL DESC LIMIT 3;

#12. Select clerks and Managers in department 10
SELECT ENAME, JOB, DEPTNO 
FROM EMP
WHERE DEPTNO = 10 AND JOB IN ('CLERK', 'MANAGER');

#13. Display all clerks in asscending order of the department number 
SELECT * FROM EMP WHERE JOB IN ('CLERK') ORDER BY DEPTNO;

#16. Display All employees in the same dept of 'SCOTT' 


SELECT * FROM EMP WHERE DEPTNO = (SELECT DEPTNO FROM EMP WHERE ENAME = 'SCOTT');

SELECT E2.*
FROM EMP E1
JOIN EMP E2 ON E1.DEPTNO = E2.DEPTNO
WHERE E1.ENAME = 'SCOTT' AND E1.ENAME <> 'SCOTT';

#17. Employees having same designation of SMITH
SELECT * FROM  EMP WHERE JOB = (SELECT JOB FROM EMP WHERE ENAME = 'SMITH');

SELECT E2.ENAME, E2.DEPTNO
FROM EMP E1
JOIN EMP E2 ON E1.DEPTNO = E2.DEPTNO
WHERE E1.ENAME = 'SCOTT';

#18. Employee who are reporting under KING
SELECT * FROM EMP WHERE MGR = (SELECT EMPNO FROM EMP WHERE ENAME = 'KING');

SELECT E.ENAME AS EmployeeName
FROM EMP E
JOIN EMP M ON E.MGR = M.EMPNO
WHERE M.ENAME = 'KING';

#19. Employees who have same salary of BLAKE

SELECT * FROM EMP WHERE SAL = (SELECT SAL FROM EMP WHERE ENAME = 'BLAKE') AND ENAME <> 'BLAKE';

SELECT E2.ENAME, E2.SAL
FROM EMP E1
JOIN EMP E2 ON E1.SAL = E2.SAL
WHERE E1.ENAME = 'BLAKE' AND E2.ENAME <> 'BLAKE';

#20. Display departmentwise number of employees
-- without join
SELECT DEPTNO, COUNT(*) AS NumberOfEmployees 
FROM EMP
GROUP BY DEPTNO
ORDER BY DEPTNO;

-- with join
SELECT D.DNAME, COUNT(E.EMPNO) AS NumberOfEmployees
FROM DEPT D
LEFT JOIN EMP E ON D.DEPTNO = E.DEPTNO
GROUP BY D.DNAME, D.DEPTNO
ORDER BY D.DEPTNO;

#21. Display jobwise number of employees
-- without join
SELECT JOB, COUNT(EMPNO) AS NumberOfEmployees
FROM EMP
GROUP BY JOB
ORDER BY JOB;

#22. Display deptwise jobwise number of employees
SELECT D.DNAME, E.JOB, COUNT(E.EMPNO) AS NumberOfEmployees
FROM DEPT D
JOIN EMP E ON D.DEPTNO = E.DEPTNO
GROUP BY D.DNAME, E.JOB
ORDER BY D.DNAME, E.JOB;

#23. Display deptwise  employees greater than  3 
SELECT D.DNAME, COUNT(E.EMPNO) AS NumberOfEmployees
FROM DEPT D
JOIN EMP E ON D.DEPTNO = E.DEPTNO
GROUP BY D.DNAME
HAVING COUNT(E.EMPNO) > 3
ORDER BY D.DNAME;

#24. Display designation wise employees count greater than 3 
SELECT JOB, COUNT(EMPNO) AS NumberOfEmployees
FROM EMP
GROUP BY JOB
HAVING COUNT(EMPNO) > 3
ORDER BY JOB;

#25. Display Employee name,deptname and location
 SELECT E.ENAME, D.DNAME, D.LOC
FROM EMP E
JOIN DEPT D ON E.DEPTNO = D.DEPTNO
ORDER BY E.ENAME;