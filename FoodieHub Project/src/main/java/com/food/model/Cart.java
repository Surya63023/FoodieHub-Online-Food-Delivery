package com.food.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Represents a shopping cart for a user. Manages cart items and calculates
 * total price.
 */
public class Cart {

	/** Stores cart items mapped by item ID */
	private Map<Integer, CartItem> items;

	/** Initializes an empty cart */
	public Cart() {
		this.items = new HashMap<>();
	}

	/**
	 * Adds an item to the cart. If item already exists, quantity is increased.
	 */
	public void addItem(CartItem item) {
		int itemId = item.getItemId();

		if (items.containsKey(itemId)) {
			CartItem existing = items.get(itemId);
			existing.setQuantity(existing.getQuantity() + item.getQuantity());
		} else {
			items.put(itemId, item);
		}
	}

	/**
	 * Updates the quantity of a cart item. Removes the item if quantity is zero or
	 * less.
	 */
	public void updateItem(int itemId, int quantity) {
		if (items.containsKey(itemId)) {
			if (quantity <= 0) {
				items.remove(itemId);
			} else {
				items.get(itemId).setQuantity(quantity);
			}
		}
	}

	/** Removes an item from the cart */
	public void removeItem(int itemId) {
		items.remove(itemId);
	}

	/** Returns all cart items */
	public Collection<CartItem> getItems() {
		return items.values();
	}

	/** Checks whether the cart is empty */
	public boolean isEmpty() {
		return items.isEmpty();
	}

	/** Calculates and returns the total cart price */
	public double getTotalPrice() {
		double total = 0;
		for (CartItem item : items.values()) {
			total += item.getTotalPrice();
		}
		return total;
	}
}
