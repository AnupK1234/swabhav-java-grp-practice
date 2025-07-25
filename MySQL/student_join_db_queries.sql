-- 1 List all students who have enrolled in at least one course.
SELECT DISTINCT s.first_name, s.last_name, e.course_id
FROM students s
JOIN enrollments e ON s.student_id = e.student_id;

-- 2 List all courses along with the department name.
SELECT c.title AS Courses, d.name AS DepartmentName
FROM courses c
JOIN departments d ON c.dept_id = d.dept_id;

-- 3 Find all students and their date of birth, even if no profile is available.
SELECT s.*, p.dob
FROM students s
LEFT JOIN student_profiles p ON s.student_id = p.student_id;

-- 4 List all teachers along with their department, including those not assigned.
SELECT t.*, d.name
FROM teachers t
LEFT JOIN departments d ON t.dept_id = d.dept_id;

-- 5 List all students and the courses they are enrolled in (even if not enrolled).
SELECT s.*, c.title
FROM students s
LEFT JOIN enrollments e ON s.student_id = e.student_id
LEFT JOIN courses c ON e.course_id = c.course_id;

-- 6 Display each department and the total number of courses offered
SELECT d.name AS department_name, COUNT(c.course_id) AS number_of_courses
FROM departments d
LEFT JOIN courses c ON d.dept_id = c.dept_id
GROUP BY d.name;

-- 7 List all students who have enrolled in the 'Algorithms' course.
SELECT s.first_name, c.title
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id AND c.title = 'Algorithms';

-- 8 Show all combinations of students and courses.
SELECT s.first_name, s.last_name, c.title AS course_title
FROM students s
CROSS JOIN courses c;

-- 9 List each student with total number of courses enrolled (0 if none).
SELECT s.first_name, s.last_name, COUNT(e.course_id) AS total_courses_enrolled
FROM students s
LEFT JOIN enrollments e ON s.student_id = e.student_id
GROUP BY s.student_id
ORDER BY s.student_id;

-- 10 List all courses that have never been taken by any student.
SELECT c.title
FROM courses c
LEFT JOIN enrollments e ON c.course_id = e.course_id
WHERE e.student_id IS NULL; 