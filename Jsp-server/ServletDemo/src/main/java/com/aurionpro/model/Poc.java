package com.aurionpro.model;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Poc")
public class Poc extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		Cookie cook = new Cookie("username", "Rohan");
		cook.setMaxAge(60 * 60 * 24);
		resp.addCookie(cook);
		out.println("Cookie was Cooked!!");

		Cookie[] cooks = req.getCookies();
		if (cooks != null) {
			for (Cookie c : cooks) {
				if (c.getName().equals("username")) {
					out.println("Username: " + c.getValue());
				}
			}

		}
	}

}
