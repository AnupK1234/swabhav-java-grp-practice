package com.aurionpro.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/EmployeeControllerServlet", "/list_employees_new.jsp", "/update_employee_form_new.jsp",
		"/add_employee_form_new.jsp" })
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// Get the current session
		HttpSession session = httpRequest.getSession(false);

		// Check if the user is logged in (session attribute exists)
		boolean isLoggedIn = (session != null && session.getAttribute("loggedInUser") != null);

		// Get the requested URI
		String requestURI = httpRequest.getRequestURI();

		// Allow access to login, logout, and static resources
		boolean isLoginOrLogout = requestURI.endsWith("LoginServlet") || requestURI.endsWith("LogoutServlet")
				|| requestURI.endsWith("login.jsp");

		if (isLoggedIn || isLoginOrLogout) {
			// User is authenticated or requesting a public resource, allow the request to
			// proceed
			chain.doFilter(request, response);
		} else {
			// User is not authenticated, redirect to login page
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
