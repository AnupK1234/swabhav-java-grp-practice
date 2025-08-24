package com.aurionpro.filter;

import com.aurionpro.model.Role;
import com.aurionpro.model.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * A security filter to protect customer and admin pages. It intercepts requests
 * to check for authentication and authorization.
 */
@WebFilter("/*") // This filter will intercept ALL requests to the application.
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		// Get the requested URL path
		String path = request.getRequestURI().substring(request.getContextPath().length());

		// --- Allow access to public resources (login page, CSS, etc.) ---
		// The request is allowed to proceed without any checks.
		if (path.startsWith("/login") || path.startsWith("/public/") || path.equals("/")) {
			chain.doFilter(request, response);
			return;
		}

		// --- Check if a user is logged in ---
		User user = (session != null) ? (User) session.getAttribute("user") : null;

		if (user == null) {
			// No user is logged in. Redirect to the login page.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// --- Authorization Checks for Protected Areas ---

		// 1. Check for Admin pages
		if (path.startsWith("/admin/")) {
			if (user.getRole() == Role.ADMIN) {
				// User is an admin, allow access.
				chain.doFilter(request, response);
			} else {
				// User is not an admin, deny access.
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			}
			return;
		}

		// 2. Check for Customer pages
		if (path.startsWith("/customer/")) {
			if (user.getRole() == Role.CUSTOMER) {
				// User is a customer, allow access.
				chain.doFilter(request, response);
			} else {
				// User is not a customer (e.g., an admin trying to access a customer URL
				// directly), deny access.
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			}
			return;
		}

		// --- If the URL is not a protected one (like /logout), let it pass ---
		chain.doFilter(request, response);
	}
}
