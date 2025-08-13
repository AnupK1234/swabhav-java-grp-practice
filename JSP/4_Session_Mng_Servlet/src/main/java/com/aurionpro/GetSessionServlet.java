package com.aurionpro;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/getsessionservlet")
public class GetSessionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		HttpSession session = request.getSession(false); // Get existing session, don't create new
		if (session != null) {
			String username = (String) session.getAttribute("username");
			response.getWriter().println("<h3>Session value: " + username + "</h3>");
		} else {
			response.getWriter().println("<h3>No active session found!</h3>");
		}
	}
}
