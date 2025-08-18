package com.aurionpro.session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if ("admin".equals(username) && "1234".equals(password)) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			resp.sendRedirect("DashboardServlet");
		} else {
			out.println("<h3>Invalid username or password</h3>");
			out.println("<a href='login1.html'>Try Again</a>");
		}
	}
}
