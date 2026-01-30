package com.food.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.food.dao.UserDAO;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;

/**
 * RegisterServlet ---------------- Handles user registration.
 *
 * Responsibilities: - Read user registration details from request - Validate
 * email uniqueness - Create and persist new user record - Redirect user to
 * login page after successful registration
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * Handles POST requests for user registration.
	 *
	 * Flow: 1. Read registration form data 2. Check if email already exists 3.
	 * Create User object with default role 4. Persist user data in database 5.
	 * Redirect user to login page
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Read registration form inputs
		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		String address = req.getParameter("address");

		// DAO used for user-related database operations
		UserDAO userDAO = new UserDAOImpl();

		// Check whether the email is already registered
		if (userDAO.isEmailExists(email)) {
			req.setAttribute("message", "Email already registered. Please login.");
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
			return;
		}

		// Assign default role for newly registered users
		String role = "USER";

		// Create User object using request data
		User user = new User(userName, email, phoneNumber, password, address, role);

		// Persist new user in the database
		userDAO.addUser(user);

		// Redirect user to login page after successful registration
		resp.sendRedirect("login.jsp");
	}
}
