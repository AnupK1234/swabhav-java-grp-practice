package com.practice.model;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetStateServlet")
public class GetStateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sessionName = (String) request.getSession().getAttribute("sessionName");
        String appMessage = (String) getServletContext().getAttribute("appMessage");

        String cookieName = "Not Found";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("cookieName".equals(ck.getName())) {
                    cookieName = ck.getValue();
                    break;
                }
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<h2>State Retrieved:</h2>");
        response.getWriter().println("<ul>");
        response.getWriter().println("<li><strong>Session Name:</strong> " + sessionName + "</li>");
        response.getWriter().println("<li><strong>Application Message:</strong> " + appMessage + "</li>");
        response.getWriter().println("<li><strong>Page Note:</strong> null (Different request)</li>");
        response.getWriter().println("<li><strong>Request Note:</strong> null (Not forwarded)</li>");
        response.getWriter().println("<li><strong>Cookie Name:</strong> " + cookieName + "</li>");
        response.getWriter().println("</ul>");
    }
}
