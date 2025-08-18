package com.practice.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import com.practice.dao.StudentDbUtil;
import com.practice.model.Student;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;

	@Resource(name = "jdbc/student-source")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private static final Pattern EMAIL_RX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		if (command == null) {
			command = "LIST";
		}

		try {
			switch (command) {
			case "LIST":
				listStudents(request, response);
				break;

			case "LOAD":
				loadStudent(request, response);
				break;

			default:
				listStudents(request, response);
			}
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Optional: ensure UTF-8 for incoming form data
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");

		try {
			switch (command) {
			case "ADD":
				addStudent(request, response);
				break;

			case "UPDATE":
				updateStudent(request, response);
				break;

			case "DELETE":
				deleteStudent(request, response);
				break;

			default:
				response.sendRedirect("StudentControllerServlet?command=LIST");
			}
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstName = safeTrim(request.getParameter("firstName"));
		String lastName = safeTrim(request.getParameter("lastName"));
		String email = safeTrim(request.getParameter("email"));

		Map<String, String> errors = validateStudent(firstName, lastName, email);

		if (!errors.isEmpty()) {
			// forward back to add form with errors + previously entered values
			request.setAttribute("ERRORS", errors);
			request.setAttribute("FORM_DATA", Map.of("firstName", firstName, "lastName", lastName, "email", email));

			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-student-form.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// save new student if validation is fine
		Student newStudent = new Student(firstName, lastName, email);
		studentDbUtil.addStudent(newStudent);

		// redirect to list
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
	}

	private Map<String, String> validateStudent(String firstName, String lastName, String email) {
		Map<String, String> errors = new LinkedHashMap<>();

		if (isBlank(firstName)) {
			errors.put("firstName", "First name is required.");
		}
		if (isBlank(lastName)) {
			errors.put("lastName", "Last name is required.");
		}
		if (isBlank(email)) {
			errors.put("email", "Email is required.");
		} else if (!EMAIL_RX.matcher(email).matches()) {
			errors.put("email", "Please enter a valid email address.");
		}
		return errors;
	}

	private static boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}

	private static String safeTrim(String s) {
		return s == null ? null : s.trim();
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theStudentId = request.getParameter("studentId");

		studentDbUtil.deleteStudent(theStudentId);

		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theStudentId = request.getParameter("studentId");

		Student theStudent = studentDbUtil.getStudent(theStudentId);

		// Place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);

		// Send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String studentId = safeTrim(request.getParameter("studentId"));
		String firstName = safeTrim(request.getParameter("firstName"));
		String lastName = safeTrim(request.getParameter("lastName"));
		String email = safeTrim(request.getParameter("email"));

		Map<String, String> errors = validateStudent(firstName, lastName, email);

		if (!errors.isEmpty()) {
			// Validation failed -> forward back to edit form with errors and previously
			// entered values
			Student invalidStudent = new Student(parseIntSafe(studentId, -1), firstName, lastName, email);
			request.setAttribute("ERRORS", errors);
			request.setAttribute("THE_STUDENT", invalidStudent);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
			dispatcher.forward(request, response);
			return;

		}

		int id = Integer.parseInt(studentId);
		Student updatedStudent = new Student(id, firstName, lastName, email);
		studentDbUtil.updateStudent(updatedStudent);

		// Post request GET
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");

	}

	private int parseIntSafe(String value, int defaultValue) {
		try {
			return Integer.parseInt(value.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get students from db util
		List<Student> students = studentDbUtil.getStudents();

		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}
}