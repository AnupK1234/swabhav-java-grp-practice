package com.aurionpro.model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MVCDemoServlet2")
public class MVCDemoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MVCDemoServlet2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> students = StudentDataUtil.getStudent();
		request.setAttribute("student_list", students);

		// Step 1: get the request dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_student2.jsp");

		// Step 2: forward the req to JSP
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
