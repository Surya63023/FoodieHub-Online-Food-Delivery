package com.food.dao;

import java.util.List;
import com.food.model.OrderItem;

/**
 * DAO interface for managing OrderItem persistence operations. Defines database
 * access methods related to individual order item management.
 */
public interface OrderItemDAO {

	/**
	 * Persists a new order item record in the database.
	 *
	 * @param orderItem the order item entity to be added
	 */
	void addOrderItem(OrderItem orderItem);

	/**
	 * Retrieves an order item using its unique identifier.
	 *
	 * @param orderItemId the order item's unique identifier
	 * @return the matching OrderItem object, or null if not found
	 */
	OrderItem getOrderItem(int orderItemId);

	/**
	 * Updates an existing order item's details in the database.
	 *
	 * @param orderItem the order item entity containing updated values
	 */
	void updateOrderItem(OrderItem orderItem);

	/**
	 * Deletes an order item record from the database using its ID.
	 *
	 * @param orderItemId the order item's unique identifier
	 */
	void deleteOrderItem(int orderItemId);

	/**
	 * Fetches all order items available in the system.
	 *
	 * @return a list of all order items
	 */
	List<OrderItem> getAllOrderItems();
}
