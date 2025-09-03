package com.aurionpro.controller;

import java.io.IOException;
import java.util.List;

import com.aurionpro.model.LeaveRequest;
import com.aurionpro.model.User;
import com.aurionpro.service.LeaveService;
import com.aurionpro.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeaveService leaveService;
	private UserService userService;

	@Override
	public void init() {
		this.leaveService = new LeaveService();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String pathInfo = request.getPathInfo();

		if (action == null) {
			if (pathInfo != null && !pathInfo.equals("/")) {
				action = pathInfo.substring(1);
			} else {
				action = "dashboard";
			}
		}
		
		switch (action) {
		case "showPendingRequests":
			showPendingRequestsPage(request, response);
			break;
		case "dashboard":
		default:
			showDashboard(request, response);
			break;
		}
	}

	private void showPendingRequestsPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<LeaveRequest> pendingRequests = leaveService.getPendingRequestsForAllManager();

		request.setAttribute("pendingRequests", pendingRequests);
		request.setAttribute("view", "pending_requests");
		request.getRequestDispatcher("/views/admin/dashboard.jsp").forward(request, response);
	}

	private void showDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User admin = (User) session.getAttribute("user");

//		int pendingRequestsCount = leaveService.getPendingRequestsForManager(manager.getId()).size();
//		int teamMembersCount = userService.getEmployeesByManager(manager.getId()).size();
//		int myLeavesCount = leaveService.getLeaveHistoryForUser(manager.getId()).size();
//		int myLeaveBalance = manager.getLeaveBalance();
//
//		request.setAttribute("pendingRequestsCount", pendingRequestsCount);
//		request.setAttribute("teamMembersCount", teamMembersCount);
//		request.setAttribute("myLeavesCount", myLeavesCount);
//		request.setAttribute("myLeaveBalance", myLeaveBalance);

		request.setAttribute("view", "dashboard");
		request.getRequestDispatcher("/views/admin/dashboard.jsp").forward(request, response);
	}
}
