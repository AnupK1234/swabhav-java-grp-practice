package com.aurionpro.servlet;

import java.io.IOException;
import java.util.List;

import com.aurionpro.Employee.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/MvcEmployee")
public class MvcEmployee extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<Employee> emps = EmplyeeUtil.getEmp();
		
		req.setAttribute("EmployeeList", emps);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/Employee.jsp");
		dispatcher.forward(req, resp);
	}

}
