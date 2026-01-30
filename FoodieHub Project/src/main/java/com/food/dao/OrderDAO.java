package com.food.dao;

import java.util.List;
import com.food.model.Order;

/**
 * DAO interface for managing Order persistence operations. Defines database
 * access methods for order creation, retrieval, modification, and deletion.
 */
public interface OrderDAO {

	/**
	 * Creates a new order record in the database. This operation is expected to
	 * handle order persistence as a single transactional unit.
	 *
	 * @param order the order entity to be created
	 * @return the generated order ID
	 */
	int createOrder(Order order);

	/**
	 * Retrieves an order using its unique identifier.
	 *
	 * @param orderId the order's unique identifier
	 * @return the matching Order object, or null if not found
	 */
	Order getOrder(int orderId);

	/**
	 * Updates an existing order's details in the database.
	 *
	 * @param order the order entity containing updated values
	 */
	void updateOrder(Order order);

	/**
	 * Deletes an order record from the database using its ID.
	 *
	 * @param orderId the order's unique identifier
	 */
	void deleteOrder(int orderId);

	/**
	 * Fetches all orders available in the system.
	 *
	 * @return a list of all orders
	 */
	List<Order> getAllOrders();
}
