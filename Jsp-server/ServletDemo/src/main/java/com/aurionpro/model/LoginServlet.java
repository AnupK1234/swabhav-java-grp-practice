package com.aurionpro.model;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		PrintWriter pw = arg1.getWriter();
		String name = "Rohan";
		String pass = "Rohan@123";
		String username = arg0.getParameter("username");
		String password = arg0.getParameter("password");
		arg1.setContentType("text/html");
		
		if(username.equalsIgnoreCase(name) && password.equalsIgnoreCase(pass)) {
			pw.println("Welcome "+username+" to this Page<br>");
			
			pw.println("Your password is "+password+"🤣");
		}else {
			arg1.sendRedirect("RedirectServlet");
		}
	
		
	}
}
