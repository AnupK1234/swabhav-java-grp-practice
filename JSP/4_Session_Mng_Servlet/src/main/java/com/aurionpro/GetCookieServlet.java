package com.aurionpro;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getcookie")
public class GetCookieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					response.getWriter().println("<h3>Cookie value: " + cookie.getValue() + "</h3>");
				}
			}
		} else {
			response.getWriter().println("<h3>No cookies found!</h3>");
		}
	}
}