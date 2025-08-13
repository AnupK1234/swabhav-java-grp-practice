package com.practice.model;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/URLRewriteServlet")
public class URLRewriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");

        response.setContentType("text/html");
        response.getWriter().println("<h3>URL Rewriting Example</h3>");
        response.getWriter().println("<p>Hello " + user + ", this is data passed via URL.</p>");
    }
}

