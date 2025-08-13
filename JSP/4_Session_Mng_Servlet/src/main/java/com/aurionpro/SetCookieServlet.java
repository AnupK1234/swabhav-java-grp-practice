package com.aurionpro;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/setcookie")
public class SetCookieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		Cookie cookie = new Cookie("user", "Anup");
		cookie.setMaxAge(60 * 60 * 24); // 1 day
		response.addCookie(cookie);

		response.getWriter().println("<h3>Cookie set: user = Anup</h3>");
		response.getWriter().println("<a href='getcookie'>Get Cookie Data</a>");
	}
}