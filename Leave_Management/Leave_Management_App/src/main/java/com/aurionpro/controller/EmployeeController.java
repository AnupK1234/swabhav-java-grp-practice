package com.aurionpro.controller;

import java.io.IOException;

import com.aurionpro.model.User;
import com.aurionpro.service.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/employee/*")
public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            pathInfo = "/dashboard"; // default
        }

        switch (pathInfo) {
            case "/dashboard":
                showEmployeeDashboard(request, response);
                break;
            case "/showProfile":
                showProfile(request, response);
                break;
            case "/showApplyLeave":
                showApplyLeavePage(request, response);
                break;
            case "/leaveHistory":
                showLeaveHistory(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            doGet(request, response);
            return;
        }

        switch (action) {
            case "updateProfile":
                updateProfile(request, response);
                break;
            case "applyLeave":
                applyForLeave(request, response);
                break;
            default:
                doGet(request, response); // fallback
        }
    }

    // --- GET handlers ---
    private void showEmployeeDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        int leaveBalance = 0;
        if (loggedInUser != null) {
            leaveBalance = employeeService.getLeaveBalance(loggedInUser.getId());
        }

        request.setAttribute("leaveBalance", leaveBalance);
        // TODO: Replace with actual service calls
        request.setAttribute("pendingCount", 1);
        request.setAttribute("approvedCount", 5);

        request.getRequestDispatcher("/views/employee/empDashboard.jsp").forward(request, response);
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/employee/editProfile.jsp").forward(request, response);
    }

    private void showApplyLeavePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // request.setAttribute("holidayList", employeeService.getEmpHoliday());
        request.getRequestDispatcher("/views/employee/applyLeave.jsp").forward(request, response);
    }

    private void showLeaveHistory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: fetch leave history list from service
        // request.setAttribute("leaveHistory", employeeService.getLeaveHistory(userId));
        request.getRequestDispatcher("/views/employee/leaveHistory.jsp").forward(request, response);
    }

    // --- POST handlers ---
    private void updateProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        User sessionUser = (User) session.getAttribute("user");
        boolean updated = employeeService.updateUserProfile(user,
                sessionUser != null ? sessionUser.getEmail() : "");

        if (updated) {
            session.setAttribute("success_toast", "Profile updated successfully!");
            if (sessionUser != null) {
                sessionUser.setFirstName(firstName);
                sessionUser.setLastName(lastName);
                sessionUser.setEmail(email);
            }
        } else {
            session.setAttribute("error_toast", "Email already taken!");
        }

        response.sendRedirect(request.getContextPath() + "/employee/showProfile");
    }

    private void applyForLeave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String reason = request.getParameter("reason");

        if (loggedInUser != null) {
            int userId = loggedInUser.getId();
            // TODO: employeeService.applyLeave(userId, startDate, endDate, reason);
        }

        session.setAttribute("success_toast", "Your leave application has been submitted!");
        response.sendRedirect(request.getContextPath() + "/employee/leaveHistory");
    }
}
