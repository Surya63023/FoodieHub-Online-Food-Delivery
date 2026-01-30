package com.food.dao;

import java.util.List;
import com.food.model.Menu;

/**
 * DAO interface for managing Menu persistence operations. Provides database
 * access methods for menu items and restaurant-specific menu retrieval.
 */
public interface MenuDAO {

	/**
	 * Persists a new menu item in the database.
	 *
	 * @param menu the menu entity to be added
	 */
	void addMenu(Menu menu);

	/**
	 * Retrieves a menu item using its unique identifier.
	 *
	 * @param menuId the menu item's unique identifier
	 * @return the matching Menu object, or null if not found
	 */
	Menu getMenu(int menuId);

	/**
	 * Updates an existing menu item's details in the database.
	 *
	 * @param menu the menu entity containing updated values
	 * @return true if the update is successful, otherwise false
	 */
	boolean updateMenu(Menu menu);

	/**
	 * Deletes a menu item from the database using its ID.
	 *
	 * @param menuId the menu item's unique identifier
	 * @return true if deletion is successful, otherwise false
	 */
	boolean deleteMenu(int menuId);

	/**
	 * Fetches all menu items available in the system.
	 *
	 * @return a list of all menu items
	 */
	List<Menu> getAllMenu();

	/**
	 * Retrieves all menu items belonging to a specific restaurant.
	 *
	 * @param restaurantId the restaurant's unique identifier
	 * @return a list of menu items for the given restaurant
	 */
	List<Menu> getMenuByRestaurantId(int restaurantId);
}
