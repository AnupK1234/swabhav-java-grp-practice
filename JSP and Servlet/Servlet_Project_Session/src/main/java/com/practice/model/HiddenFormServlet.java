package com.practice.model;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HiddenFormServlet")
public class HiddenFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String hiddenUser = request.getParameter("hiddenUser");

        response.setContentType("text/html");
        response.getWriter().println("<h3>Hidden Form Field Example</h3>");
        response.getWriter().println("<p>User (from hidden field): " + hiddenUser + "</p>");
    }
}
