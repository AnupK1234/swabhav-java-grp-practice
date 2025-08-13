package com.practice.model;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.getWriter().println("<html><head><title>State Management</title></head><body>");
        response.getWriter().println("<h2>Welcome to Servlet State Management Demo</h2>");
        
        // Form to set state
        response.getWriter().println("<form action='StateServlet' method='post'>");
        response.getWriter().println("Enter Name: <input type='text' name='name'><br><br>");
        response.getWriter().println("<input type='submit' value='Set State'>");
        response.getWriter().println("</form>");

        // URL rewriting link
        response.getWriter().println("<br><a href='URLRewriteServlet?user=Vivek'>URL Rewriting Example</a><br><br>");

        // Hidden form
        response.getWriter().println("<form action='HiddenFormServlet' method='post'>");
        response.getWriter().println("<input type='hidden' name='hiddenUser' value='Id007'>");
        response.getWriter().println("<input type='submit' value='Go to Hidden Form Example'>");
        response.getWriter().println("</form>");

        response.getWriter().println("</body></html>");
    }
}
