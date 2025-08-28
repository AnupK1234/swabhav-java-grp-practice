	package com.aurionpro.controller.admin;
	
	import com.aurionpro.model.Role;
	import com.aurionpro.model.User;
	import com.aurionpro.service.AdminService;
	import com.aurionpro.Dao.TransactionDao;
	import com.aurionpro.Dao.UserDao; // Assuming AdminService needs this
	
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import jakarta.servlet.http.HttpSession;
	
	import java.io.IOException;
	import java.sql.SQLException;
	import java.util.Random;
	
	@WebServlet("/admin/add-user")
	public class AddCustomerController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private AdminService adminService;
	
		@Override
		public void init() {
			UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
			TransactionDao transactionDao = (TransactionDao) getServletContext().getAttribute("transactionDao");
			this.adminService = new AdminService(userDao, transactionDao);
		}
	
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession(false);
	
			if (session == null || session.getAttribute("user") == null
					|| ((User) session.getAttribute("user")).getRole() != Role.ADMIN) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
				return;
			}
	        request.setAttribute("activePage", "addUser");

			request.getRequestDispatcher("/admin/add_user.jsp").forward(request, response);
		}
	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession(false);
	
			if (session == null || session.getAttribute("user") == null
					|| ((User) session.getAttribute("user")).getRole() != Role.ADMIN) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied.");
				return;
			}
	
			String username = request.getParameter("username");
			String plainPassword = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String balanceStr = request.getParameter("balance");
			String roleParam = request.getParameter("role");
			int accountNumber = new Random().nextInt(90000000) + 10000000; // 8-digit random
	
			try {
				double balance = Double.parseDouble(balanceStr);
				Role role = Role.valueOf(roleParam.toUpperCase());
	
				String resultMessage = adminService.createUser(username, plainPassword, firstName, lastName, accountNumber,
						balance, role);
	
				 if (resultMessage.startsWith("Success")) {
		                session.setAttribute("message", resultMessage);
		            } else {
		                session.setAttribute("error", resultMessage);
		            }
			} catch (NumberFormatException e) {
				session.setAttribute("error", "Error: Invalid initial balance.");
			} catch (IllegalArgumentException | SQLException e) {
				session.setAttribute("error", "Error: " + e.getMessage());
			}
			
	
			response.sendRedirect(request.getContextPath() + "/admin/AdminDashboard");
		}
	}
