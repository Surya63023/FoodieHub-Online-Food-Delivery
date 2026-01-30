package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RestaurantServlet ------------------ Handles the restaurant listing page.
 * 
 * Responsibilities: - Fetch all restaurants from the database - Pass restaurant
 * data to restaurant.jsp for display
 */
@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// DAO reference used to access restaurant-related database operations
	private RestaurantDAO restaurantDAO;

	/**
	 * Initializes the RestaurantDAO implementation when the servlet is loaded. This
	 * ensures DAO object creation happens only once.
	 */
	@Override
	public void init() throws ServletException {
		restaurantDAO = new RestaurantDAOImpl();
	}

	/**
	 * Handles GET requests for the restaurant listing page.
	 * 
	 * Flow: 1. Fetch all restaurants from the database 2. Store the list in request
	 * scope 3. Forward the request to restaurant.jsp for rendering
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve all restaurants from the database
		List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();

		// Store restaurant list in request scope for JSP access
		request.setAttribute("restaurantList", restaurantList);

		// Forward request to restaurant.jsp to display restaurants
		RequestDispatcher dispatcher = request.getRequestDispatcher("restaurant.jsp");
		dispatcher.forward(request, response);
	}
}
