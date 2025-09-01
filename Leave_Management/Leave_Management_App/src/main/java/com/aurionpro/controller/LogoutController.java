package com.aurionpro.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession oldSession = request.getSession(false);
        
        if (oldSession != null) {
            oldSession.invalidate();
        }
        
        HttpSession newSession = request.getSession(true); 
        newSession.setAttribute("success_toast", "You have been logged out successfully.");
        
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
