package com.aurionpro.controller;

import java.io.IOException;
import java.util.Random;

import com.aurionpro.model.User;
import com.aurionpro.service.EmailService;
import com.aurionpro.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		User user = userService.findByEmail(email);

		if (user == null) {
			req.setAttribute("error", "No account found with that email!");
			req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
			return;
		}

		// Generate OTP
		String otp = String.valueOf(new Random().nextInt(999999));
		HttpSession session = req.getSession();
		session.setAttribute("otp", otp);
		session.setAttribute("email", email);

		// Send OTP via email (Resend API or JavaMail)
		EmailService.sendOtp(email, otp);

		req.setAttribute("email", email);
		req.getRequestDispatcher("/views/verify_otp.jsp").forward(req, resp);
	}
}
