package com.aurionpro.session;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
        response.setContentType("text/html");
        response.getWriter().println("<h3>You have been logged out.</h3>");
        response.getWriter().println("<a href='login1.html'>Login Again</a>");
    }
}
