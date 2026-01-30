package com.food.dao;

import java.util.List;
import com.food.model.Restaurant;

/**
 * DAO interface for managing Restaurant persistence operations. Defines
 * database access methods related to restaurant management.
 */
public interface RestaurantDAO {

	/**
	 * Persists a new restaurant record in the database.
	 *
	 * @param restaurant the restaurant entity to be added
	 */
	void addRestaurant(Restaurant restaurant);

	/**
	 * Retrieves a restaurant using its unique identifier.
	 *
	 * @param restaurantId the restaurant's unique identifier
	 * @return the matching Restaurant object, or null if not found
	 */
	Restaurant getRestaurant(int restaurantId);

	/**
	 * Updates an existing restaurant's details in the database.
	 *
	 * @param restaurant the restaurant entity containing updated values
	 */
	void updateRestaurant(Restaurant restaurant);

	/**
	 * Deletes a restaurant record from the database using its ID.
	 *
	 * @param restaurantId the restaurant's unique identifier
	 */
	void deleteRestaurant(int restaurantId);

	/**
	 * Fetches all restaurant records from the database.
	 *
	 * @return a list of all restaurants
	 */
	List<Restaurant> getAllRestaurants();
}
