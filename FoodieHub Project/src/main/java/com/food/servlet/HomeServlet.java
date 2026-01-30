package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.dao.RestaurantDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Menu;
import com.food.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HomeServlet ------------ Handles the home page of the application.
 * 
 * Responsibilities: - Fetch all restaurants from the database - Fetch all
 * available food/menu items - Forward the data to home.jsp for display
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// DAO references for accessing restaurant and menu data
	private RestaurantDAO restaurantDAO;
	private MenuDAO menuDAO;

	/**
	 * Initializes DAO implementations once when the servlet is loaded. This avoids
	 * creating DAO objects repeatedly for every request.
	 */
	@Override
	public void init() throws ServletException {
		restaurantDAO = new RestaurantDAOImpl();
		menuDAO = new MenuDAOImpl();
	}

	/**
	 * Handles GET requests for the home page.
	 * 
	 * Flow: 1. Fetch all restaurants 2. Fetch all menu items 3. Store data in
	 * request scope 4. Forward request to home.jsp for rendering
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve list of all restaurants from the database
		List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();
		request.setAttribute("restaurantList", restaurantList);

		// Retrieve list of all menu items (across all restaurants)
		List<Menu> menuList = menuDAO.getAllMenu();
		request.setAttribute("menuList", menuList);

		// Forward request to home.jsp with restaurant and menu data
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
}
