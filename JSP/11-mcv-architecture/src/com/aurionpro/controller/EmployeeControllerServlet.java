package com.aurionpro.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.aurionpro.dao.EmployeeDbDAO;
import com.aurionpro.model.Employee;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDbDAO EmployeeDbDAO;
	@Resource(name = "jdbc/employee-source")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			EmployeeDbDAO = new EmployeeDbDAO(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			// if the command is missing, then default to listing Employees
			if (theCommand == null) {
				theCommand = "LIST";
			}
			// route to the appropriate method
			switch (theCommand) {
			case "LIST":
				listEmployees(request, response);
				break;
			case "ADD":
				addEmployee(request, response);
				break;
			case "LOAD":
				loadEmployee(request, response);
				break;

			case "DELETE":
				deleteEmployee(request, response);
				break;

			default:
				listEmployees(request, response);
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

		String theCommand = request.getParameter("command");
		if (theCommand == null || theCommand.isBlank()) {
			theCommand = "LIST";
		}

		try {
			switch (theCommand) {
			case "ADD":
				addEmployee(request, response); // PRG(Post request GET) on success
				break;
			case "UPDATE":
				updateEmployee(request, response); // PRG on success
				break;
//			case "DELETE":
//				deleteEmployee(request, response); // PRG on success
//				break;
			default:
				listEmployees(request, response);
			}
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get Employees from db util
		List<Employee> employees = EmployeeDbDAO.getEmployees();
//		System.out.println(employees);
		// add Employees to the request
		request.setAttribute("Employee_LIST", employees);
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_employees_new.jsp");
		dispatcher.forward(request, response);
	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read employee info from form
		String firstName = safeTrim(request.getParameter("firstName"));
		String lastName = request.getParameter("lastName");
		double salary = Double.parseDouble(request.getParameter("salary"));

		Map<String, String> errors = validateEmployee(firstName, lastName, salary);

		if (!errors.isEmpty()) {
			request.setAttribute("ERRORS", errors);
			request.setAttribute("FORM_DATA", Map.of("firstName", firstName, "lastName", lastName, "salary", salary));
			request.getRequestDispatcher("/add_Employee_form_new.jsp").forward(request, response);
			return;
		}

		// create a new Employee object
		Employee theEmployee = new Employee(firstName, lastName, salary);

		// add the employee to the database
		EmployeeDbDAO.addEmployee(theEmployee);

		// send back to main page (the list)
//		listEmployees(request, response);

		// TODO: To avoid resubmission OR Refresh
		response.sendRedirect(request.getContextPath() + "/EmployeeControllerServlet?command=LIST");
	}

	private Map<String, String> validateEmployee(String firstName, String lastName, Double salary) {
		Map<String, String> errors = new LinkedHashMap<>();

		if (isBlank(firstName)) {
			errors.put("firstName", "First name is required.");
		}
		if (isBlank(lastName)) {
			errors.put("lastName", "Last name is required.");
		}

		if (salary < 0) {
			errors.put("salary", "Please enter a valid salary.");
		}

		return errors;
	}

	// Helper method to safely trim strings
	private String safeTrim(String s) {
		return s == null ? null : s.trim();
	}

	// Helper method to check if a string is blank
	private boolean isBlank(String s) {
		return s == null || s.trim().isEmpty();
	}

	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String EmployeeId = request.getParameter("EmployeeId");
		Employee theEmployee = EmployeeDbDAO.getEmployee(EmployeeId);
		System.out.println("---------------------------------");
		System.out.println(theEmployee);
		System.out.println("---------------------------------");
		request.setAttribute("THE_Employee", theEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_employee_form_new.jsp");
		dispatcher.forward(request, response);
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String EmployeeId = safeTrim(request.getParameter("EmployeeId"));
		String firstName = safeTrim(request.getParameter("firstName"));
		String lastName = safeTrim(request.getParameter("lastName"));
		double salary = Double.parseDouble(request.getParameter("salary"));

		Map<String, String> errors = validateEmployee(firstName, lastName, salary);

		if (!errors.isEmpty()) {
			// Validation failed → forward back to edit form with errors and previously
			// entered values
			Employee invalidEmployee = new Employee(firstName, lastName, parseIntSafe(EmployeeId, -1), salary);
			request.setAttribute("ERRORS", errors);
			request.setAttribute("THE_Employee", invalidEmployee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update_employee_form_new.jsp");
			dispatcher.forward(request, response);
			return;
		}

		int id = Integer.parseInt(EmployeeId);
		Employee updatedEmployee = new Employee(firstName, lastName, id, salary);
		EmployeeDbDAO.updateEmployee(updatedEmployee);

		// Post request GET
		response.sendRedirect(request.getContextPath() + "/EmployeeControllerServlet?command=LIST");
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String EmployeeId = request.getParameter("EmployeeId");
		System.out.println(EmployeeId);
		EmployeeDbDAO.deleteEmployee(EmployeeId);
		response.sendRedirect("EmployeeControllerServlet?command=LIST");
	}

	private int parseIntSafe(String str, int defaultValue) {
		if (str == null || str.trim().isEmpty()) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
