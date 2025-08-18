package com.aurionpro.session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			String user = (String) session.getAttribute("username");
			out.println("<h2>Welcome, " + user + "</h2>");
			out.println("<a href='logout'>Logout</a>");
		} else {
			out.println("<h3>No active session. Please login.</h3>");
			out.println("<a href='login1.html'>Login</a>");
		}
	}

}
