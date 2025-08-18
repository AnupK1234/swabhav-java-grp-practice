package com.practice.model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MvcDemoServlet")
public class MvcDemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MvcDemoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    		String[] students = { "Harish", "Anil", "Mohamed", "Trupti" };
		
		
		request.setAttribute("student_list", students);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_students.jsp");
		
		
		dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	super.doPost(req, resp);
    }
}
