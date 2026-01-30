package com.food.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Menu;

/**
 * MenuServlet ----------- Handles displaying menu items for a selected
 * restaurant.
 * 
 * Responsibilities: - Read restaurantId from the request - Fetch corresponding
 * menu items from the database - Forward menu data to menu.jsp for rendering
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	// DAO reference used to perform menu-related database operations
	private MenuDAO menuDAO;

	/**
	 * Initializes the MenuDAO implementation when the servlet is loaded. This
	 * ensures DAO object creation happens once per servlet lifecycle.
	 */
	@Override
	public void init() {
		menuDAO = new MenuDAOImpl();
	}

	/**
	 * Handles GET requests for the menu page.
	 * 
	 * Flow: 1. Read restaurantId from request parameters 2. Fetch menu items for
	 * the selected restaurant 3. Store menu list in request scope 4. Forward
	 * request to menu.jsp for display
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read restaurantId passed from the restaurant listing page
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

		// Fetch menu items belonging to the selected restaurant
		List<Menu> menuList = menuDAO.getMenuByRestaurantId(restaurantId);

		// Store menu list in request scope for JSP access
		request.setAttribute("menuList", menuList);

		// Forward request to menu.jsp to render menu items
		RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
		rd.forward(request, response);
	}
}
