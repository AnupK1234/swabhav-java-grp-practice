package com.aurionpro.controller;

import java.io.IOException;

import com.aurionpro.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
	private UserService userService = new UserService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String newPassword = req.getParameter("password");

		userService.updatePassword(email, newPassword);

		req.getSession().setAttribute("success_toast", "Password updated successfully! Please login.");
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}
