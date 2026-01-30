package com.food.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.food.dao.UserDAO;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;

/**
 * LoginServlet ------------ Handles user authentication.
 *
 * Responsibilities: - Display login page - Validate user credentials - Create
 * user session on successful login - Redirect user based on previous requested
 * page or default page
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * Handles GET requests for displaying the login page.
	 *
	 * Flow: - Simply forwards the request to login.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	/**
	 * Handles POST requests for processing login.
	 *
	 * Flow: 1. Read login credentials from request 2. Validate user using DAO 3.
	 * Create session and store user on success 4. Redirect user to intended page or
	 * restaurant listing 5. Forward back to login page on failure
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Read login credentials from request
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// Validate user credentials against database
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.validateUser(email, password);

		if (user != null) {

			// Create a new session and store authenticated user
			HttpSession session = req.getSession(true);
			session.setAttribute("user", user);

			// Retrieve redirect path saved before login (if any)
			String redirectAfterLogin = (String) session.getAttribute("redirectAfterLogin");

			if (redirectAfterLogin != null && !redirectAfterLogin.isEmpty()) {
				session.removeAttribute("redirectAfterLogin");
				resp.sendRedirect(req.getContextPath() + redirectAfterLogin);
			} else {
				resp.sendRedirect(req.getContextPath() + "/restaurants");
			}

		} else {
			// Authentication failed, show error message
			req.setAttribute("message", "Invalid Email or Password");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
