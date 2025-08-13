package com.practice.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/StateServlet")
public class StateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StateServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		
		//Session Scope- This value will persist as long as the user’s session is active
		request.getSession().setAttribute("sessionName", name);
		
		//Application Scope- This is shared across all users and all sessions.
		getServletContext().setAttribute("appMessage", "Welcome All Users!");
		
		//Page Scope
		request.setAttribute("pageNote", "Note: Shown only on this page.");
		
		//Request Scope
		 request.setAttribute("requestNote", "This is a request-scoped value.");
		 
		 //Cookie
		 Cookie userCookie= new Cookie("cookieName",name);
		 userCookie.setMaxAge(3600);
		 response.addCookie(userCookie);
		 
		 response.setContentType("text/html");
		 response.getWriter().println("<h3>State Set Successfully!</h3>");
		 response.getWriter().println("<a href='GetStateServlet'>Click here to see stored state</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
