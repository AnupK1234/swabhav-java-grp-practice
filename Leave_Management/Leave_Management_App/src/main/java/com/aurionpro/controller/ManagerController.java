package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aurionpro.model.Attendance;
import com.aurionpro.model.Holiday;
import com.aurionpro.model.LeaveRequest;
import com.aurionpro.model.User;
import com.aurionpro.service.AttendanceService;
import com.aurionpro.service.HolidayService;
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
	private HolidayService holidayService;
	private AttendanceService attendanceService;

	@Override
	public void init() {
		this.leaveService = new LeaveService();
		this.userService = new UserService();
		this.holidayService = new HolidayService();
		this.attendanceService = new AttendanceService();
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
		case "showAttendanceLeave":
			showAttendanceLeavePage(request, response);
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
		case "markAttendance":
			markAttendance(request, response);
			break;
		case "updateProfile":
			updateProfile(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	private void showAttendanceLeavePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User currentUser = (User) request.getSession().getAttribute("user");

		List<Holiday> allHolidays = holidayService.getAllHolidays();
		List<Attendance> attendanceRecords = attendanceService.getAttendanceForUser(currentUser.getId());
		List<LeaveRequest> leaveHistory = leaveService.getLeaveHistoryForUser(currentUser.getId());
		List<Holiday> upcomingHolidays = holidayService.getUpcomingHolidays();
		LocalDate today = LocalDate.now();
		DayOfWeek todayDayOfWeek = today.getDayOfWeek();
		boolean isAttendanceMarkedToday = attendanceService.isAttendanceMarkedToday(attendanceRecords);

		// Check if attendance can be marked today
		boolean canMarkAttendance = true;
		String disableReason = "";

		if (isAttendanceMarkedToday) {
			canMarkAttendance = false;
			disableReason = "Attendance Marked Today";
		} else if (todayDayOfWeek == DayOfWeek.SATURDAY || todayDayOfWeek == DayOfWeek.SUNDAY) {
			canMarkAttendance = false;
			disableReason = "Cannot Mark on Weekend";
		} else {
			// Check if today is a holiday
			for (Holiday holiday : allHolidays) {
				if (holiday.getHolidayDate().equals(today)) {
					canMarkAttendance = false;
					disableReason = "Today is a Holiday";
					break;
				}
			}
			// If it's not a holiday, check if the user is on approved leave today
			if (canMarkAttendance) {
				for (LeaveRequest lr : leaveHistory) {
					if ("APPROVED".equalsIgnoreCase(lr.getStatus())) {
						LocalDate start = lr.getStartDate().toLocalDate();
						LocalDate end = lr.getEndDate().toLocalDate();
						if (!today.isBefore(start) && !today.isAfter(end)) {
							canMarkAttendance = false;
							disableReason = "You are on Approved Leave";
							break;
						}
					}
				}
			}
		}

		List<String> missedDates = attendanceService.calculateMissedAttendanceDates(allHolidays, attendanceRecords,
				leaveHistory);

		int presentCount = attendanceService.getPresentDaysCountForCurrentMonth(currentUser.getId());
		int absentCount = attendanceService.getAbsentDaysCountForCurrentMonth(missedDates);
		int leaveBalance = currentUser.getLeaveBalance();
		request.setAttribute("holidays", allHolidays);
		request.setAttribute("upcomingHolidays", upcomingHolidays);
		request.setAttribute("attendanceRecords", attendanceRecords);
		request.setAttribute("leaveHistory", leaveHistory);
		request.setAttribute("isAttendanceMarkedToday", isAttendanceMarkedToday);
		request.setAttribute("missedDates", missedDates);
		request.setAttribute("presentCount", presentCount);
		request.setAttribute("absentCount", absentCount);
		request.setAttribute("leaveBalance", leaveBalance);
		request.setAttribute("canMarkAttendance", canMarkAttendance);
		request.setAttribute("disableReason", disableReason);

		request.setAttribute("view", "attendance_leave");
		request.getRequestDispatcher("/views/manager/manager_home.jsp").forward(request, response);
	}

	private void markAttendance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");

		boolean success = attendanceService.markUserAttendance(currentUser);

		if (success) {
			session.setAttribute("success_toast", "Attendance marked for today!");
		} else {
			session.setAttribute("error_toast", "Attendance already marked for today or an error occurred.");
		}

		response.sendRedirect(request.getContextPath() + "/manager?action=showAttendanceLeave");
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

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty()
				|| email == null || email.trim().isEmpty()) {

			session.setAttribute("error_toast", "All fields are required. Please fill them out.");
			response.sendRedirect(request.getContextPath() + "/manager?action=showEditProfile");
			return;
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		if (!email.matches(emailRegex)) {
			session.setAttribute("error_toast", "Please enter a valid email address.");
			response.sendRedirect(request.getContextPath() + "/manager?action=showEditProfile");
			return;
		}

		String originalEmail = currentUser.getEmail();

		currentUser.setFirstName(firstName.trim());
		currentUser.setLastName(lastName.trim());

		currentUser.setEmail(email.trim());

		boolean success = userService.updateUserProfile(currentUser, originalEmail);

		if (success) {
			session.setAttribute("user", currentUser);
			session.setAttribute("success_toast", "Profile updated successfully!");
		} else {
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

	    List<Holiday> upcomingHolidays = holidayService.getUpcomingHolidays();
	    List<LeaveRequest> recentPendingRequests = leaveService.getRecentPendingRequestsForManager(manager.getId());
	    List<Holiday> allHolidays = holidayService.getAllHolidays();
	    List<Attendance> attendanceRecords = attendanceService.getAttendanceForUser(manager.getId());
	    List<LeaveRequest> leaveHistory = leaveService.getLeaveHistoryForUser(manager.getId());

	    LocalDate today = LocalDate.now();
	    DayOfWeek todayDayOfWeek = today.getDayOfWeek();
	    boolean isAttendanceMarkedToday = attendanceService.isAttendanceMarkedToday(attendanceRecords);
	    
		// Check if attendance can be marked today

	    boolean canMarkAttendance = true;
	    String disableReason = "";

	    if (isAttendanceMarkedToday) {
	        canMarkAttendance = false;
	        disableReason = "Attendance Marked";
	    } else if (todayDayOfWeek == DayOfWeek.SATURDAY || todayDayOfWeek == DayOfWeek.SUNDAY) {
	        canMarkAttendance = false;
	        disableReason = "Weekend";
	    } else {
			// Check if today is a holiday

	        for (Holiday holiday : allHolidays) {
	            if (holiday.getHolidayDate().equals(today)) {
	                canMarkAttendance = false;
	                disableReason = "Holiday";
	                break;
	            }
	        }
			// If it's not a holiday, check if the user is on approved leave today

	        if (canMarkAttendance) {
	            for (LeaveRequest lr : leaveHistory) {
	                if ("APPROVED".equalsIgnoreCase(lr.getStatus())) {
	                    LocalDate start = lr.getStartDate().toLocalDate();
	                    LocalDate end = lr.getEndDate().toLocalDate();
	                    if (!today.isBefore(start) && !today.isAfter(end)) {
	                        canMarkAttendance = false;
	                        disableReason = "On Leave";
	                        break;
	                    }
	                }
	            }
	        }
	    }

	    request.setAttribute("pendingRequestsCount", pendingRequestsCount);
	    request.setAttribute("teamMembersCount", teamMembersCount);
	    request.setAttribute("myLeavesCount", myLeavesCount);
	    request.setAttribute("myLeaveBalance", myLeaveBalance);
	    request.setAttribute("upcomingHolidays", upcomingHolidays);
	    request.setAttribute("recentPendingRequests", recentPendingRequests);

	    request.setAttribute("isAttendanceMarkedToday", isAttendanceMarkedToday);
	    request.setAttribute("canMarkAttendance", canMarkAttendance);
	    request.setAttribute("disableReason", disableReason);

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

	private void processLeaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int leaveId = Integer.parseInt(request.getParameter("leaveId"));
		String decision = request.getParameter("decision");
		HttpSession session = request.getSession();
		boolean success = false;

		if ("approve".equals(decision)) {
			success = leaveService.approveLeave(leaveId);
			if (success) {
				session.setAttribute("success_toast", "Leave request approved.");
			}
		} else if ("reject".equals(decision)) {
			String reason = request.getParameter("rejectionReason");
			success = leaveService.rejectLeave(leaveId, reason); // Pass the reason to the service
			if (success) {
				session.setAttribute("success_toast", "Leave request rejected.");
			}
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

		try {
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
			String reason = request.getParameter("reason");

			if (startDate.isAfter(endDate)) {
				session.setAttribute("error_toast", "Start date cannot be after the end date.");
				response.sendRedirect(request.getContextPath() + "/manager?action=showAttendanceLeave");
				return;
			}

			List<Holiday> holidays = holidayService.getAllHolidays();
			Set<LocalDate> holidayDates = new HashSet<>();
			holidays.forEach(h -> holidayDates.add(h.getHolidayDate()));

			LocalDate currentDate = startDate;
			while (!currentDate.isAfter(endDate)) {
				DayOfWeek day = currentDate.getDayOfWeek();
				if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
					session.setAttribute("error_toast", "Leave cannot be applied for on weekends.");
					response.sendRedirect(request.getContextPath() + "/manager?action=showAttendanceLeave");
					return;
				}
				if (holidayDates.contains(currentDate)) {
					session.setAttribute("error_toast", "Leave cannot be applied for on a public holiday.");
					response.sendRedirect(request.getContextPath() + "/manager?action=showAttendanceLeave");
					return;
				}
				currentDate = currentDate.plusDays(1);
			}
			if (!leaveService.canApplyForLeaveThisMonth(manager.getId(), startDate, endDate)) {
				session.setAttribute("error_toast", "This leave would exceed the monthly limit of 2 total days.");
				response.sendRedirect(request.getContextPath() + "/manager?action=showAttendanceLeave");
				return;
			}

			LeaveRequest leave = new LeaveRequest();
			leave.setUserId(manager.getId());
			leave.setStartDate(Date.valueOf(startDate));
			leave.setEndDate(Date.valueOf(endDate));
			leave.setReason(reason);

			boolean success = leaveService.applyForLeave(leave);
			if (success) {
				session.setAttribute("success_toast", "Leave application submitted!");
				response.sendRedirect(request.getContextPath() + "/manager?action=showLeaveHistory");
			} else {
				session.setAttribute("error_toast", "Failed to submit application.");
				response.sendRedirect(request.getContextPath() + "/manager?action=showAttendanceLeave");
			}
		} catch (Exception e) {
			session.setAttribute("error_toast", "Invalid data provided. Please select dates from the calendar.");
			response.sendRedirect(request.getContextPath() + "/manager?action=showAttendanceLeave");
		}
	}
}
