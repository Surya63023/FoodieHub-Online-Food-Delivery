package com.food.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Represents a customer order in the Online Food Delivery application. Stores
 * order details, payment information, and associated items.
 */
public class Order {

	/** Unique identifier for the order */
	private int orderId;

	/** Date and time when the order was placed */
	private Timestamp orderDate;

	/** Restaurant ID from which the order was placed */
	private int restaurantId;

	/** User ID who placed the order */
	private int userId;

	/** Total payable amount for the order */
	private double totalAmount;

	/** Payment method used for the order */
	private String paymentMethod;

	/** Current status of the order (e.g., PLACED, DELIVERED) */
	private String status;

	/** Delivery address for the order */
	private String address;

	/** List of items included in the order */
	private List<OrderItem> orderItems;

	/** Default constructor */
	public Order() {
	}

	/**
	 * Constructor used when core order details are available.
	 */
	public Order(int orderId, Timestamp orderDate, int restaurantId, int userId, double totalAmount,
			String paymentMethod, String status, String address) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.address = address;
	}

	/** Returns the order ID */
	public int getOrderId() {
		return orderId;
	}

	/** Sets the order ID */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/** Returns the order date */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/** Sets the order date */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/** Returns the restaurant ID */
	public int getRestaurantId() {
		return restaurantId;
	}

	/** Sets the restaurant ID */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	/** Returns the user ID */
	public int getUserId() {
		return userId;
	}

	/** Sets the user ID */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/** Returns the total order amount */
	public double getTotalAmount() {
		return totalAmount;
	}

	/** Sets the total order amount */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/** Returns the payment method */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/** Sets the payment method */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/** Returns the order status */
	public String getStatus() {
		return status;
	}

	/** Sets the order status */
	public void setStatus(String status) {
		this.status = status;
	}

	/** Returns the delivery address */
	public String getAddress() {
		return address;
	}

	/** Sets the delivery address */
	public void setAddress(String address) {
		this.address = address;
	}

	/** Returns the list of order items */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/** Sets the list of order items */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
