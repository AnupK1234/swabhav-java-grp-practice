package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import com.aurionpro.dao.LeaveRequestDao;
import com.aurionpro.dao.UserDao;
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

@WebServlet("/manager/*")
public class ManagerController extends HttpServlet {
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
		case "manageEmployees":
			showManageEmployeesPage(request, response);
			break;
		case "showLeaveHistory":
			showLeaveHistoryPage(request, response);
			break;
		case "showApplyLeave":
			showApplyLeaveForm(request, response);
			break;
		case "showEditProfile":
			showEditProfileForm(request, response);
			break;
		case "dashboard":
		default:
			showDashboard(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "processLeave":
			processLeaveRequest(request, response);
			break;
		case "applyLeave":
			submitLeaveApplication(request, response);
			break;
		case "updateProfile":
			updateProfile(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	private void showEditProfileForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("view", "edit_profile");
		request.getRequestDispatcher("/views/manager/manager_home.jsp").forward(request, response);
	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
	    HttpSession session = request.getSession();
	    User currentUser = (User) session.getAttribute("user");

	    // Get the submitted form data
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String email = request.getParameter("email");
	    
	    // --- SERVER-SIDE VALIDATION ---
	    // 1. Check for empty or null fields
	    if (firstName == null || firstName.trim().isEmpty() || 
	        lastName == null || lastName.trim().isEmpty() || 
	        email == null || email.trim().isEmpty()) {
	        
	        session.setAttribute("error_toast", "All fields are required. Please fill them out.");
	        response.sendRedirect(request.getContextPath() + "/manager?action=showEditProfile");
	        return; // Stop processing immediately
	    }

	    // 2. Check for a valid email format using a simple regex
	    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	    if (!email.matches(emailRegex)) {
	        session.setAttribute("error_toast", "Please enter a valid email address.");
	        response.sendRedirect(request.getContextPath() + "/manager?action=showEditProfile");
	        return; // Stop processing immediately
	    }
	    // --- END OF SERVER-SIDE VALIDATION ---

	    String originalEmail = currentUser.getEmail();

	    // The data is valid, so we can now safely update the user object
	    currentUser.setFirstName(firstName.trim()); // Use trimmed values
	    currentUser.setLastName(lastName.trim());

	    // Pass the new email to the service layer, which will handle the uniqueness check
	    currentUser.setEmail(email.trim()); 

	    boolean success = userService.updateUserProfile(currentUser, originalEmail);

	    if (success) {
	        session.setAttribute("user", currentUser);
	        session.setAttribute("success_toast", "Profile updated successfully!");
	    } else {
	        // If it failed, the service layer determined the email was a duplicate.
	        // Revert the email on our object so the form displays the original, valid email.
	        currentUser.setEmail(originalEmail); 
	        session.setAttribute("error_toast", "Email is already registered by another user.");
	    }

	    response.sendRedirect(request.getContextPath() + "/manager?action=showEditProfile");
	}

	private void showDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User manager = (User) session.getAttribute("user");

		int pendingRequestsCount = leaveService.getPendingRequestsForManager(manager.getId()).size();
		int teamMembersCount = userService.getEmployeesByManager(manager.getId()).size();
		int myLeavesCount = leaveService.getLeaveHistoryForUser(manager.getId()).size();
		int myLeaveBalance = manager.getLeaveBalance();

		request.setAttribute("pendingRequestsCount", pendingRequestsCount);
		request.setAttribute("teamMembersCount", teamMembersCount);
		request.setAttribute("myLeavesCount", myLeavesCount);
		request.setAttribute("myLeaveBalance", myLeaveBalance);

		request.setAttribute("view", "dashboard");
		request.getRequestDispatcher("/views/manager/manager_home.jsp").forward(request, response);
	}

	private void showPendingRequestsPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User manager = (User) request.getSession().getAttribute("user");
		List<LeaveRequest> pendingRequests = leaveService.getPendingRequestsForManager(manager.getId()); // Use Service

		request.setAttribute("pendingRequests", pendingRequests);
		request.setAttribute("view", "pending_requests");
		request.getRequestDispatcher("/views/manager/manager_home.jsp").forward(request, response);
	}

	private void showManageEmployeesPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User manager = (User) request.getSession().getAttribute("user");
		List<User> employeeList = userService.getEmployeesByManager(manager.getId()); 

		request.setAttribute("employeeList", employeeList);
		request.setAttribute("view", "manage_employees");
		request.getRequestDispatcher("/views/manager/manager_home.jsp").forward(request, response);
	}

	private void showLeaveHistoryPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User manager = (User) request.getSession().getAttribute("user");
		List<LeaveRequest> leaveHistory = leaveService.getLeaveHistoryForUser(manager.getId()); 

		request.setAttribute("leaveHistory", leaveHistory);
		request.setAttribute("view", "leave_history");
		request.getRequestDispatcher("/views/manager/manager_home.jsp").forward(request, response);
	}

	private void showApplyLeaveForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("view", "apply_leave");
		request.getRequestDispatcher("/views/manager/manager_home.jsp").forward(request, response);
	}

	private void processLeaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int leaveId = Integer.parseInt(request.getParameter("leaveId"));
		String decision = request.getParameter("decision");
		HttpSession session = request.getSession();
		boolean success = false;

		if ("approve".equals(decision)) {
			success = leaveService.approveLeave(leaveId); 
			if (success)
				session.setAttribute("success_toast", "Leave request approved.");
		} else if ("reject".equals(decision)) {
			success = leaveService.rejectLeave(leaveId); 
			if (success)
				session.setAttribute("success_toast", "Leave request rejected.");
		}

		if (!success) {
			session.setAttribute("error_toast", "Failed to process leave request.");
		}

		response.sendRedirect(request.getContextPath() + "/manager?action=showPendingRequests");
	}

	private void submitLeaveApplication(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User manager = (User) session.getAttribute("user");

		LeaveRequest leave = new LeaveRequest();
		leave.setUserId(manager.getId());
		leave.setStartDate(Date.valueOf(request.getParameter("startDate")));
		leave.setEndDate(Date.valueOf(request.getParameter("endDate")));
		leave.setReason(request.getParameter("reason"));

		boolean success = leaveService.applyForLeave(leave); 
		if (success) {
			session.setAttribute("success_toast", "Leave application submitted!");
			response.sendRedirect(request.getContextPath() + "/manager?action=showLeaveHistory");
		} else {
			session.setAttribute("error_toast", "Failed to submit application.");
			response.sendRedirect(request.getContextPath() + "/manager?action=showApplyLeave");
		}
	}
}
