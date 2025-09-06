package com.aurionpro.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verify-otp")
public class VerifyOtpServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String otp = req.getParameter("otp");

		HttpSession session = req.getSession();
		String sessionOtp = (String) session.getAttribute("otp");

		if (sessionOtp != null && sessionOtp.equals(otp)) {
			req.setAttribute("email", email);
			req.getRequestDispatcher("/views/reset_password.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Invalid OTP!");
			req.setAttribute("email", email);
			req.getRequestDispatcher("/views/verify_otp.jsp").forward(req, resp);
		}	
	}
}
