package com.food.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LogoutServlet ------------- Handles user logout functionality.
 *
 * Responsibilities: - Invalidate the current user session - Redirect the user
 * to a safe page after logout
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * Handles POST requests for user logout.
	 *
	 * Flow: 1. Read optional redirect path from request 2. Invalidate existing HTTP
	 * session 3. Redirect user to provided path or default page
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read optional redirect path after logout
		String redirectAfterLogout = request.getParameter("redirect");

		// Retrieve existing session without creating a new one
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// Redirect user after logout using context path
		if (redirectAfterLogout != null && !redirectAfterLogout.isEmpty()) {
			response.sendRedirect(request.getContextPath() + redirectAfterLogout);
		} else {
			response.sendRedirect(request.getContextPath() + "/restaurants");
		}
	}
}
