Create database ORGANIZATION;
USE ORGANIZATION;
CREATE TABLE DEPT (
DEPTNO              integer NOT NULL,
DNAME               varchar(14),
LOC                 varchar(13),
CONSTRAINT DEPT_PRIMARY_KEY PRIMARY KEY (DEPTNO));

CREATE TABLE EMP (
EMPNO               integer NOT NULL,
ENAME               varchar(10),
JOB                 varchar(9),
MGR                 integer,
HIREDATE            DATEtime,
SAL                 DECIMAL(10,2),
COMM                DECIMAL(10,2),
DEPTNO              integer NOT NULL,
CONSTRAINT EMP_FOREIGN_KEY FOREIGN KEY (DEPTNO) REFERENCES DEPT (DEPTNO),
CONSTRAINT EMP_PRIMARY_KEY PRIMARY KEY (EMPNO));
INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');
 
INSERT INTO EMP VALUES (7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10);
INSERT INTO EMP VALUES (7698,'BLAKE','MANAGER',7839,'1981-05-1',2850,NULL,30);
INSERT INTO EMP VALUES (7782,'CLARK','MANAGER',7839,'1981-06-9',2450,NULL,10);
INSERT INTO EMP VALUES (7566,'JONES','MANAGER',7839,'1981-4-2',2975,NULL,20);
INSERT INTO EMP VALUES (7654,'MARTIN','SALESMAN',7698,'1981-9-28',1250,1400,30);
INSERT INTO EMP VALUES (7499,'ALLEN','SALESMAN',7698,'1981-2-20',1600,300,30);
INSERT INTO EMP VALUES (7844,'TURNER','SALESMAN',7698,'1981-9-8',1500,0,30);
INSERT INTO EMP VALUES (7900,'JAMES','CLERK',7698,'1981-12-3',950,NULL,30);
INSERT INTO EMP VALUES (7521,'WARD','SALESMAN',7698,'1981-2-22',1250,500,30);
INSERT INTO EMP VALUES (7902,'FORD','ANALYST',7566,'1981-12-3',3000,NULL,20);
INSERT INTO EMP VALUES (7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20);
INSERT INTO EMP VALUES (7788,'SCOTT','ANALYST',7566,'1982-12-09',3000,NULL,20);
INSERT INTO EMP VALUES (7876,'ADAMS','CLERK',7788,'1983-1-12',1100,NULL,20);
INSERT INTO EMP VALUES (7934,'MILLER','CLERK',7782,'1982-1-23',1300,NULL,10);
 

CREATE TABLE REGIONS
( REGION_ID INTEGER NOT NULL,
REGION_NAME VARCHAR(20),
PRIMARY KEY(REGION_ID)
);
 
CREATE TABLE COUNTRIES 
    ( COUNTRY_ID      CHAR(2)
    , COUNTRY_NAME    VARCHAR(40) 
    , REGION_ID       INTEGER 
    , PRIMARY KEY (COUNTRY_ID),
	  CONSTRAINT COUNTR_REG_FK FOREIGN KEY (REGION_ID) REFERENCES REGIONS(REGION_ID)
    ) ;
    
CREATE TABLE LOCATIONS
    ( LOCATION_ID    INTEGER not null
    , STREET_ADDRESS VARCHAR(40)
    , POSTAL_CODE    VARCHAR(12)
    , CITY       VARCHAR(30) NOT NULL
    , STATE_PROVINCE VARCHAR(25)
    , COUNTRY_ID     CHAR(2),
    PRIMARY KEY (LOCATION_ID),
    FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRIES(COUNTRY_ID)
    ) ;
 
INSERT INTO regions VALUES( 1, 'Europe');
INSERT INTO regions VALUES( 2, 'Americas');
INSERT INTO regions VALUES( 3, 'Asia');
INSERT INTO regions VALUES( 4, 'Middle East and Asia');
 
INSERT INTO countries VALUES( 'IT', 'Italy', 1);
INSERT INTO countries VALUES( 'JP', 'Japan', 3);
INSERT INTO countries VALUES( 'US', 'USA', 2);
INSERT INTO countries VALUES( 'CA', 'Canada', 2);
INSERT INTO countries VALUES( 'CN', 'China', 3);
INSERT INTO countries VALUES( 'IN', 'India', 3);
INSERT INTO countries VALUES( 'AU', 'Australia', 3);
INSERT INTO countries VALUES( 'ZW', 'Zimbabwe',4);
 
 
INSERT INTO countries VALUES( 'SG', 'Singapore', 3);
INSERT INTO countries VALUES( 'UK', 'United Kingdom', 1);
INSERT INTO countries VALUES( 'FR', 'France', 1);
INSERT INTO countries VALUES( 'DE', 'Germany', 1);
INSERT INTO countries VALUES( 'ZM', 'Zambia', 4);
INSERT INTO countries VALUES( 'EG', 'Egypt', 4);
INSERT INTO countries VALUES( 'BR', 'Brazil', 2);
INSERT INTO countries VALUES( 'CH', 'Switzerland', 1);
 
 
INSERT INTO countries VALUES( 'NL', 'Netherlands', 1);
INSERT INTO countries VALUES( 'MX', 'Mexico', 2);
INSERT INTO countries VALUES( 'KW', 'Kuwait', 4);
INSERT INTO countries VALUES( 'IL', 'Israel', 4);
INSERT INTO countries VALUES( 'DK', 'Denmark', 1);
INSERT INTO countries VALUES( 'HK', 'HongKong', 3);
INSERT INTO countries VALUES( 'NG', 'Nigeria', 4);
INSERT INTO countries VALUES( 'AR', 'Argentina', 2);
INSERT INTO countries VALUES( 'BE', 'Belgium', 1);
 
INSERT INTO locations VALUES( 1000, '1297 Via Cola di Rie', '00989', 'Roma', NULL, 'IT');
INSERT INTO locations VALUES( 1100, '93091 Calle della Testa', '10934', 'Venice', NULL, 'IT');
INSERT INTO locations VALUES( 1200, '2017 Shinjuku-ku', '1689', 'Tokyo', 'Tokyo Prefecture', 'JP');
INSERT INTO locations VALUES( 1300, '9450 Kamiya-cho', '6823', 'Hiroshima', NULL, 'JP');
INSERT INTO locations VALUES( 1400, '2014 Jabberwocky Rd', '26192', 'Southlake', 'Texas', 'US');
INSERT INTO locations VALUES( 1500, '2011 Interiors Blvd', '99236', 'South San Francisco', 'California', 'US');
INSERT INTO locations VALUES( 1600, '2007 Zagora St', '50090', 'South Brunswick', 'New Jersey', 'US');
INSERT INTO locations VALUES( 1700, '2004 Charade Rd', '98199', 'Seattle', 'Washington', 'US');
INSERT INTO locations VALUES( 1800, '147 Spadina Ave', 'M5V 2L7', 'Toronto', 'Ontario', 'CA');
INSERT INTO locations VALUES( 1900, '6092 Boxwood St', 'YSW 9T2', 'Whitehorse', 'Yukon', 'CA');

-- #0. show details of all employee
select * from EMP;

-- #1. Display all employee names in ascending order
select ENAME from EMP order by ENAME ASC;

-- #2. Display all employees(all columns) in department 20 and 30
select * from EMP where DEPTNO=20 or DEPTNO=30;

-- #3. Display all the employees who are managers
select * from EMP where job = "Manager";

-- #4. Display all the employees whose salary is between 2000 and 5000
select * from EMP where SAL between 2000 and 5000;

-- #5. Display all the employees whose commission is null

select * from EMP where COMM is null;

-- #6. Display emp_name,salary,comission,ctc(calculated column)
select ENAME, SAL, COMM, SAL*12 AS CTC FROM EMP;

-- #7. Display hire_date, current_date, tenure(calculated col) of the empl
SELECT HIREDATE, current_date() as CURDATE, timediff(YEAR,HIREDATE,CURDATE) AS TENURE FROM EMP;

-- #8. Display all the employees whose name starts with s
SELECT ENAME FROM EMP WHERE ENAME LIKE "S%";

-- #9. Display unique department numbers from the employee table
SELECT DISTINCT DEPTNO FROM EMP;

-- #10. Display emp_name and job in lower case
SELECT LOWER(ENAME),lower(JOB) FROM EMP;

-- #11. Select top 3 salary earning employee
SELECT * FROM EMP ORDER BY SAL DESC LIMIT 3;

-- #12. Select clerks and Managers in department 10 student
SELECT * FROM EMP WHERE JOB IN ('CLERK','MANAGER') AND DEPTNO IN(10);

-- #13. Display all clerks in asscending order of the department number 
SELECT * FROM EMP WHERE JOB='CLERK' ORDER BY DEPTNO;

-- #16. Display All employees in the same dept of 'SCOTT' 
SELECT * FROM EMP WHERE DEPTNO=(SELECT DEPTNO FROM EMP WHERE ENAME='SCOTT');

-- #17. Employees having same designation of SMITH
SELECT * FROM EMP WHERE JOB=(SELECT JOB FROM EMP WHERE ENAME='SMITH');

-- #18. Employee who are reproting under KING
SELECT * FROM EMP WHERE MGR=(SELECT EMPNO FROM EMP WHERE ENAME='KING');

-- #19. Employees who have same salary of BLAKE
SELECT * FROM EMP WHERE SAL=(SELECT SAL FROM EMP WHERE ENAME='BLAKE');

-- #20. Display departmentwise number of employees
SELECT DEPTNO,COUNT(*) FROM EMP GROUP BY DEPTNO;

-- #21. Display jobwise number of employees
SELECT JOB, COUNT(*) FROM EMP GROUP BY JOB;

-- #22. Display deptwise jobwise number of employees
SELECT JOB,DEPTNO, COUNT(*) FROM EMP GROUP BY DEPTNO,JOB ORDER BY DEPTNO;

-- #23. Display deptwise  employees greater than  3 
SELECT DEPTNO,COUNT(*) FROM EMP GROUP BY DEPTNO HAVING COUNT(*)>3;

-- #24. Display designation wise employees count greater than 3 
SELECT JOB,COUNT(*) FROM EMP GROUP BY JOB HAVING COUNT(*)>3;

-- #25. Display Employee name,deptname and location
SELECT e.ENAME, d.DNAME,d.LOC from EMP e join dept d on e.DEPTNO=d.DEPTNO ORDER BY e.ENAME;

-- 26. Show every department and the employees in it 
select d.DEPTNO, e.ENAME from EMP e JOIN dept d on e.DEPTNO=d.DEPTNO order by d.DEPTNO;

-- 27. Show only the departments that have no employees
select d.DEPTNO,d.DNAME from DEPT d left join EMP e on d.DEPTNO = e.DEPTNO where e.EMPNO is null;

-- 28 . Show each department’s employee count > 3, sorted by department name (Z→A)
SELECT d.DEPTNO, d.DNAME, COUNT(e.EMPNO) AS employee_count
FROM DEPT d
JOIN EMP e ON d.DEPTNO = e.DEPTNO
GROUP BY d.DEPTNO, d.DNAME
HAVING COUNT(e.EMPNO) > 3
ORDER BY d.DNAME DESC;

-- 29. Show every employee and their manager’s name
select e.ENAME,m.ENAME from EMP e join EMP m on e.MGR = m.EMPNO order by e.ENAME;

-- 30. Show employee name, department name, and manager name (alias as bossname), ordered by bossname
select e.ENAME,d.DNAME,m.ENAME AS bossname
FROM EMP e
left JOIN EMP m ON e.MGR = m.EMPNO 
join DEPT d on m.DEPTNO = d.DEPTNO
order by bossname;

-- 31. Show department name, employee name, and the employee’s manager name
 SELECT 
    d.DNAME, e.ENAME,m.ENAME as MNAME
FROM EMP e
LEFT JOIN EMP m ON e.MGR = m.EMPNO
JOIN DEPT d ON e.DEPTNO = d.DEPTNO
order by MNAME;

-- 32 Show every location, its city, country and region
SELECT l.LOCATION_ID, l.CITY, c.COUNTRY_NAME, r.REGION_NAME
FROM LOCATIONS l
JOIN COUNTRIES c ON l.COUNTRY_ID = c.COUNTRY_ID
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID;
