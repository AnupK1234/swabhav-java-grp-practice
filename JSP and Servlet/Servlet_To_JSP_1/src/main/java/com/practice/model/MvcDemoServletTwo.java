package com.practice.model;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MvcDemoServletTwo")
public class MvcDemoServletTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MvcDemoServletTwo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Student> students = StudentDataUtil.getStudents();

		request.setAttribute("student_list", students);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_students_two.jsp");

		dispatcher.forward(request, response);
	}
}