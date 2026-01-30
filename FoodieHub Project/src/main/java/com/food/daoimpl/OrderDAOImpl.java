package com.food.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.model.Order;
import com.food.model.OrderItem;
import com.food.util.DBConnection;

/**
 * OrderDAOImpl ------------ JDBC-based DAO implementation for handling
 * order-related operations including order creation, retrieval, update, and
 * deletion.
 *
 * This class manages database transactions explicitly when creating orders
 * along with their associated order items.
 */
public class OrderDAOImpl implements OrderDAO {

	/* SQL queries related to orders and order items */
	private static final String INSERT_ORDER = "INSERT INTO orders (orderDate, restaurantId, userId, totalAmount, paymentMethod, status, address) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String INSERT_ORDER_ITEM = "INSERT INTO orderitem (orderId, menuId, itemName, quantity, totalPrice) VALUES (?, ?, ?, ?, ?)";

	private static final String GET_ORDER = "SELECT * FROM orders WHERE orderId = ?";

	private static final String UPDATE_ORDER = "UPDATE orders SET orderDate=?, restaurantId=?, userId=?, totalAmount=?, paymentMethod=?, status=?, address=? "
			+ "WHERE orderId=?";

	private static final String DELETE_ORDER = "DELETE FROM orders WHERE orderId = ?";

	private static final String GET_ALL_ORDERS = "SELECT * FROM orders";

	/**
	 * Creates a new order along with its associated order items. Both order and
	 * order items are inserted within a single database transaction.
	 *
	 * @param order Order object containing order and item details
	 * @return generated orderId after successful insertion
	 */
	@Override
	public int createOrder(Order order) {

		int orderId = 0;
		Connection con = null;

		try {
			// Obtain database connection
			con = DBConnection.getConnection();

			// Disable auto-commit to start manual transaction
			con.setAutoCommit(false);

			// Insert order record
			try (PreparedStatement ps = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

				// Use provided order date or current timestamp
				Timestamp ts = order.getOrderDate() != null ? order.getOrderDate()
						: new Timestamp(System.currentTimeMillis());

				ps.setTimestamp(1, ts);
				ps.setInt(2, order.getRestaurantId());
				ps.setInt(3, order.getUserId());
				ps.setDouble(4, order.getTotalAmount());
				ps.setString(5, order.getPaymentMethod());
				ps.setString(6, order.getStatus());
				ps.setString(7, order.getAddress());

				ps.executeUpdate();

				// Retrieve generated orderId
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						orderId = rs.getInt(1);
					}
				}
			}

			// Insert order items associated with the order
			if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {

				try (PreparedStatement psItem = con.prepareStatement(INSERT_ORDER_ITEM)) {

					for (OrderItem item : order.getOrderItems()) {

						psItem.setInt(1, orderId);
						psItem.setInt(2, item.getMenuId());
						psItem.setString(3, item.getItemName());
						psItem.setInt(4, item.getQuantity());
						psItem.setDouble(5, item.getTotalPrice());

						// Add item insert to batch
						psItem.addBatch();
					}

					// Execute batch insert for order items
					psItem.executeBatch();
				}
			}

			// Commit transaction after successful inserts
			con.commit();

		} catch (Exception e) {

			// Rollback transaction in case of failure
			try {
				if (con != null)
					con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			e.printStackTrace();

		} finally {

			// Restore auto-commit and close connection
			try {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return orderId;
	}

	/**
	 * Retrieves an order using its orderId.
	 *
	 * @param orderId unique identifier of the order
	 * @return Order object if found, otherwise null
	 */
	@Override
	public Order getOrder(int orderId) {

		Order order = null;

		// Prepare SELECT query to fetch order by ID
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ORDER)) {

			ps.setInt(1, orderId);

			// ResultSet contains order data
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				order = new Order(rs.getInt("orderId"), rs.getTimestamp("orderDate"), rs.getInt("restaurantId"),
						rs.getInt("userId"), rs.getDouble("totalAmount"), rs.getString("paymentMethod"),
						rs.getString("status"), rs.getString("address"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return order;
	}

	/**
	 * Updates an existing order record.
	 *
	 * @param order Order object containing updated details
	 */
	@Override
	public void updateOrder(Order order) {

		// Prepare UPDATE statement
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_ORDER)) {

			ps.setTimestamp(1, order.getOrderDate());
			ps.setInt(2, order.getRestaurantId());
			ps.setInt(3, order.getUserId());
			ps.setDouble(4, order.getTotalAmount());
			ps.setString(5, order.getPaymentMethod());
			ps.setString(6, order.getStatus());
			ps.setString(7, order.getAddress());
			ps.setInt(8, order.getOrderId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes an order using its orderId.
	 *
	 * @param orderId unique identifier of the order
	 */
	@Override
	public void deleteOrder(int orderId) {

		// Execute DELETE query
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_ORDER)) {

			ps.setInt(1, orderId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all orders from the database.
	 *
	 * @return list of Order objects
	 */
	@Override
	public List<Order> getAllOrders() {

		List<Order> list = new ArrayList<>();

		// Execute SELECT query to fetch all orders
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL_ORDERS);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				list.add(new Order(rs.getInt("orderId"), rs.getTimestamp("orderDate"), rs.getInt("restaurantId"),
						rs.getInt("userId"), rs.getDouble("totalAmount"), rs.getString("paymentMethod"),
						rs.getString("status"), rs.getString("address")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
