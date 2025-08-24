package com.aurionpro.controller;

import com.aurionpro.model.User;
import com.aurionpro.service.AuthService;
import com.aurionpro.Dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthService authService;

    @Override
    public void init() {
        UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
        authService = new AuthService(userDao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = authService.authenticate(username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(5 * 60);

                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("error", "Invalid credentials!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error during authentication", e);
        }
    }
}
