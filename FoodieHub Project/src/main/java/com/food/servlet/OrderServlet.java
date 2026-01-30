package com.food.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.food.daoimpl.OrderDAOImpl;
import com.food.daoimpl.OrderItemDAOImpl;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.model.OrderItem;
import com.food.model.User;

/**
 * OrderServlet ------------ Handles checkout and order placement.
 *
 * Responsibilities: - Validate user session and cart state - Create order and
 * order items - Persist order details in the database - Clear cart data after
 * successful order placement
 */
@WebServlet("/checkout")
public class OrderServlet extends HttpServlet {

	/**
	 * Handles POST requests for order checkout.
	 *
	 * Flow: 1. Validate user login and cart state 2. Read checkout form details 3.
	 * Create and save order 4. Save individual order items 5. Clear session cart
	 * and redirect to success page
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Retrieve active HTTP session
		HttpSession session = req.getSession();

		// Retrieve required session attributes
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		Integer restaurantIdObj = (Integer) session.getAttribute("restaurantId");

		// Ensure user is logged in before placing an order
		if (user == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		// Ensure cart exists and contains items
		if (cart == null || cart.isEmpty()) {
			resp.sendRedirect("cart.jsp");
			return;
		}

		// Ensure restaurant context is available
		if (restaurantIdObj == null) {
			resp.sendRedirect("cart.jsp");
			return;
		}

		int restaurantId = restaurantIdObj;

		// Read checkout form inputs
		String address = req.getParameter("address");
		String paymentMethod = req.getParameter("paymentMethod");

		// Create Order object using session and request data
		Order order = new Order();
		order.setUserId(user.getUserId());
		order.setRestaurantId(restaurantId);
		order.setOrderDate(new Timestamp(System.currentTimeMillis()));
		order.setAddress(address);
		order.setPaymentMethod(paymentMethod);
		order.setStatus("pending");

		// Calculate total order amount from cart items
		double totalAmount = 0;
		for (CartItem item : cart.getItems()) {
			totalAmount += item.getTotalPrice();
		}
		order.setTotalAmount(totalAmount);

		// Persist order and retrieve generated order ID
		OrderDAOImpl orderDAO = new OrderDAOImpl();
		int orderId = orderDAO.createOrder(order);

		// Persist each cart item as an order item
		OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();
		for (CartItem item : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orderId);
			orderItem.setMenuId(item.getItemId());
			orderItem.setItemName(item.getName());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setTotalPrice(item.getTotalPrice());
			orderItemDAO.addOrderItem(orderItem);
		}

		// Clear cart-related session data after successful checkout
		session.removeAttribute("cart");
		session.removeAttribute("oldRestaurantId");

		// Redirect user to order success page
		resp.sendRedirect("orderSuccess.jsp");
	}
}
