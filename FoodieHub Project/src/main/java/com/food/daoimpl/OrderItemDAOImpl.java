package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderItemDAO;
import com.food.model.OrderItem;
import com.food.util.DBConnection;

/**
 * OrderItemDAOImpl ---------------- JDBC-based DAO implementation responsible
 * for performing CRUD operations on OrderItem entities.
 *
 * This class directly interacts with the orderitem table using standard JDBC
 * components.
 */
public class OrderItemDAOImpl implements OrderItemDAO {

	/* SQL queries related to orderitem table */
	private static final String INSERT_ORDER_ITEM = "INSERT INTO orderitem (orderId, itemName, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?, ?)";

	private static final String GET_ORDER_ITEM = "SELECT * FROM orderitem WHERE orderItemId = ?";

	private static final String UPDATE_ORDER_ITEM = "UPDATE orderitem SET orderId=?, itemName=?, menuId=?, quantity=?, totalPrice=? WHERE orderItemId=?";

	private static final String DELETE_ORDER_ITEM = "DELETE FROM orderitem WHERE orderItemId = ?";

	private static final String GET_ALL_ORDER_ITEMS = "SELECT * FROM orderitem";

	/**
	 * Inserts a new order item record into the database.
	 *
	 * @param orderItem OrderItem object containing item details
	 */
	@Override
	public void addOrderItem(OrderItem orderItem) {

		// Obtain database connection and prepare INSERT statement
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_ITEM)) {

			// Set values for INSERT query parameters
			ps.setInt(1, orderItem.getOrderId());
			ps.setString(2, orderItem.getItemName());
			ps.setInt(3, orderItem.getMenuId());
			ps.setInt(4, orderItem.getQuantity());
			ps.setDouble(5, orderItem.getTotalPrice());

			// Execute INSERT operation
			ps.executeUpdate();
			System.out.println("OrderItem inserted successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves an order item using its orderItemId.
	 *
	 * @param orderItemId unique identifier of the order item
	 * @return OrderItem object if found, otherwise null
	 */
	@Override
	public OrderItem getOrderItem(int orderItemId) {

		OrderItem orderItem = null;

		// Prepare SELECT query to fetch order item by ID
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(GET_ORDER_ITEM)) {

			ps.setInt(1, orderItemId);

			// ResultSet contains the data returned from the database
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					// Map database row to OrderItem object
					orderItem = new OrderItem(rs.getInt("orderItemId"), rs.getInt("orderId"), rs.getString("itemName"),
							rs.getInt("menuId"), rs.getInt("quantity"), rs.getDouble("totalPrice"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItem;
	}

	/**
	 * Updates an existing order item record.
	 *
	 * @param orderItem OrderItem object containing updated values
	 */
	@Override
	public void updateOrderItem(OrderItem orderItem) {

		// Prepare UPDATE statement
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_ITEM)) {

			ps.setInt(1, orderItem.getOrderId());
			ps.setString(2, orderItem.getItemName());
			ps.setInt(3, orderItem.getMenuId());
			ps.setInt(4, orderItem.getQuantity());
			ps.setDouble(5, orderItem.getTotalPrice());
			ps.setInt(6, orderItem.getOrderItemId());

			// Execute UPDATE operation
			ps.executeUpdate();
			System.out.println("OrderItem updated successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes an order item using its orderItemId.
	 *
	 * @param orderItemId unique identifier of the order item
	 */
	@Override
	public void deleteOrderItem(int orderItemId) {

		// Execute DELETE query
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_ORDER_ITEM)) {

			ps.setInt(1, orderItemId);
			ps.executeUpdate();
			System.out.println("OrderItem deleted successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all order items from the database.
	 *
	 * @return list of OrderItem objects
	 */
	@Override
	public List<OrderItem> getAllOrderItems() {

		List<OrderItem> orderItemList = new ArrayList<>();

		// Execute SELECT query to fetch all order item records
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDER_ITEMS);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				// Map each row to OrderItem object
				OrderItem orderItem = new OrderItem(rs.getInt("orderItemId"), rs.getInt("orderId"),
						rs.getString("itemName"), rs.getInt("menuId"), rs.getInt("quantity"),
						rs.getDouble("totalPrice"));

				orderItemList.add(orderItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItemList;
	}
}
