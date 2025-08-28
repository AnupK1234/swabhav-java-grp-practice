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


@WebFilter("/*") 
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		// Get the requested URL path
		String path = request.getRequestURI().substring(request.getContextPath().length());

		
		if (path.startsWith("/login") || path.startsWith("/public/") || path.equals("/")) {
			chain.doFilter(request, response);
			return;
		}

		
		User user = (session != null) ? (User) session.getAttribute("user") : null;

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}


		if (path.startsWith("/admin/")) {
			if (user.getRole() == Role.ADMIN) {
				chain.doFilter(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			}
			return;
		}

		if (path.startsWith("/customer/")) {
			if (user.getRole() == Role.CUSTOMER) {
				chain.doFilter(request, response);
			} else {
				
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
			}
			return;
		}

		chain.doFilter(request, response);
	}
}
