package com.aurionpro;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/appcontextservlet")
public class AppContextServlet extends HttpServlet {
	public void init() {
		// Called only once when servlet is first loaded
		getServletContext().setAttribute("appName", "My Java Web App");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		ServletContext context = getServletContext();
		String appName = (String) context.getAttribute("appName");

		response.getWriter().println("<h3>Application Name: " + appName + "</h3>");
	}
}