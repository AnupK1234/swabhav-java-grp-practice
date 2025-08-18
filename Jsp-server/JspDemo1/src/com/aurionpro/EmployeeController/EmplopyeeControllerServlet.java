package com.aurionpro.EmployeeController;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.aurionpro.Employee.Employee;
import com.aurionpro.EmployeeDbUtil.EmployeeDbUtil;
import com.aurionpro.student.Student;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/EmplopyeeControllerServlet")
public class EmplopyeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeDbUtil employeeDbUtil;

	@Resource(name = "jdbc/student-source")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}

			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				listEmployee(request, response);
				break;

	      case "ADD":
	        addStudent(request, response);
	        break;
	      case "SHOW_ADD_FORM":
	    	    RequestDispatcher dispatcher = request.getRequestDispatcher("/addEmployee.jsp");
	    	    dispatcher.forward(request, response);
	    	    break;

	        
//	      case "LOAD":
//	        loadStudent(request, response);
//	        break;
//	        
//	      case "UPDATE":
//	        updateStudent(request, response);
//	        break;
//	      
//	      case "DELETE":
//	        deleteStudent(request, response);
//	        break;
//	        
			default:
				listEmployee(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get students from db util
		List<Employee> emps = employeeDbUtil.getEmp();

		// add students to the request
		request.setAttribute("Employee_LIST", emps);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String firstName= request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Employee emp = new Employee(firstName, lastName, email);
		
		employeeDbUtil.addEmployee(emp);
		
		listEmployee(request, response);
	}
}
