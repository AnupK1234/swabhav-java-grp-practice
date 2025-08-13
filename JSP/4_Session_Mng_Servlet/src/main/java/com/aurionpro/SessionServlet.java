package com.aurionpro;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/sessionservlet")
public class SessionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		HttpSession session = request.getSession(); // Create or get existing session
		session.setAttribute("username", "Anup"); // Store data

		response.getWriter().println("<h3>Session set: username = Anup</h3>");
		response.getWriter().println("<a href='getsessionservlet'>Get Session Data</a>");
	}
}
