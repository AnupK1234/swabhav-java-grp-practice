-- Show student names and the courses they are enrolled in
SELECT s.name AS student_name, c.course_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id;

-- List all students along with their instructor’s name for each enrolled course
SELECT s.name AS student_name, c.course_name, i.instructor_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
JOIN Instructors i ON c.instructor_id = i.instructor_id;

-- Find students who are enrolled in “Java Programming”
SELECT s.name AS student_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
WHERE c.course_name = 'Java Programming';

-- List all students from “Mumbai” who are enrolled in any course
SELECT DISTINCT s.name AS student_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
WHERE s.city = 'Mumbai';

-- Show students who enrolled after “2025-07-02” with their course name
SELECT s.name AS student_name, c.course_name, e.enrollment_date
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
WHERE e.enrollment_date > '2025-07-02';

-- Show all students and their enrolled courses (include students who are not enrolled in any course)
SELECT s.name AS student_name, c.course_name
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
LEFT JOIN Courses c ON e.course_id = c.course_id;

-- Show all courses and their enrolled students (include courses with no students enrolled)
SELECT c.course_name, s.name AS student_name
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
LEFT JOIN Students s ON e.student_id = s.student_id;

-- List student names, course names, and instructor names (3-table join)
SELECT s.name AS student_name, c.course_name, i.instructor_name
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
JOIN Instructors i ON c.instructor_id = i.instructor_id;

-- Show all instructors and the students they teach (even if no student is enrolled in their course)
SELECT i.instructor_name, s.name AS student_name, c.course_name
FROM Instructors i
LEFT JOIN Courses c ON i.instructor_id = c.instructor_id
LEFT JOIN Enrollments e ON c.course_id = e.course_id
LEFT JOIN Students s ON e.student_id = s.student_id;

-- Count how many students are enrolled in each course
SELECT c.course_name, COUNT(e.student_id) AS total_students_enrolled
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_name;

-- Find the instructor who has the maximum students enrolled
SELECT i.instructor_name, COUNT(e.student_id) AS total_students_taught
FROM Instructors i
JOIN Courses c ON i.instructor_id = c.instructor_id
JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY i.instructor_name
ORDER BY total_students_taught DESC
LIMIT 1;

-- Show each city and the total students enrolled from that city
SELECT s.city, COUNT(s.student_id) AS total_students
FROM Students s
GROUP BY s.city;

-- List students who are enrolled in all courses taught by “Mr. Sharma” (Complex Join + Subquery)
SELECT s.name AS student_name
FROM Students s
WHERE NOT EXISTS (
    SELECT c.course_id
    FROM Courses c
    JOIN Instructors i ON c.instructor_id = i.instructor_id
    WHERE i.instructor_name = 'Mr. Sharma'
    AND c.course_id NOT IN (
        SELECT e.course_id
        FROM Enrollments e
        WHERE e.student_id = s.student_id
    )
);

-- Find students who are not enrolled in any course (using LEFT JOIN)
SELECT s.name AS student_name
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
WHERE e.enrollment_id IS NULL;