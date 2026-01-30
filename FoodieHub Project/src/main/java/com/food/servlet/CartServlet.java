package com.food.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Menu;

/**
 * CartServlet ----------- Manages cart-related operations such as viewing the
 * cart, adding items, updating quantities, and removing items.
 *
 * Uses HTTP session to persist cart data across requests.
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	// DAO used to fetch menu details when adding items to the cart
	private MenuDAO menuDAO;

	/**
	 * Initializes MenuDAO when the servlet is loaded.
	 */
	@Override
	public void init() {
		menuDAO = new MenuDAOImpl();
	}

	/**
	 * Handles GET requests for displaying the cart page.
	 * 
	 * Flow: - Simply forwards the request to cart.jsp - Cart data is already
	 * available in session scope
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/cart.jsp").forward(req, resp);
	}

	/**
	 * Handles POST requests for cart actions.
	 * 
	 * Supported actions: - add : Add a new item to the cart - update : Update item
	 * quantity - delete : Remove item from cart
	 *
	 * Uses PRG (Post-Redirect-Get) pattern to prevent duplicate submissions.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Retrieve existing HTTP session or create one if not present
		HttpSession session = req.getSession();

		// Fetch cart and associated restaurant from session
		Cart cart = (Cart) session.getAttribute("cart");
		Integer oldRestaurantId = (Integer) session.getAttribute("restaurantId");

		// Read restaurantId from request
		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));

		// Reset cart if:
		// - Cart does not exist
		// - Restaurant is changed
		if (cart == null || oldRestaurantId == null || oldRestaurantId != newRestaurantId) {
			cart = new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}

		// Determine requested cart action
		String action = req.getParameter("action");

		if ("add".equals(action)) {
			addItem(req, cart);
		} else if ("update".equals(action)) {
			updateItem(req, cart);
		} else if ("delete".equals(action)) {
			deleteItem(req, cart);
		}

		// Redirect to cart page to avoid duplicate form submission
		resp.sendRedirect(req.getContextPath() + "/cart");
	}

	/**
	 * Adds a new item to the cart. Fetches menu details from the database before
	 * adding.
	 */
	private void addItem(HttpServletRequest req, Cart cart) {
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		// Fetch menu details from database
		Menu menu = menuDAO.getMenu(menuId);

		// Create cart item using menu details
		CartItem item = new CartItem(menu.getMenuId(), menu.getMenuName(), menu.getPrice(), quantity,
				menu.getImageURL());

		cart.addItem(item);
	}

	/**
	 * Updates the quantity of an existing cart item.
	 */
	private void updateItem(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		cart.updateItem(itemId, quantity);
	}

	/**
	 * Removes an item from the cart.
	 */
	private void deleteItem(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId);
	}
}
