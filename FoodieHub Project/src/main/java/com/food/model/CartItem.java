package com.food.model;

/**
 * Represents an individual item stored in the shopping cart. Holds product
 * details, quantity, and pricing information.
 */
public class CartItem {

	/** Unique identifier for the cart item */
	private int itemId;

	/** Name of the item */
	private String name;

	/** Price per unit */
	private double price;

	/** Quantity added to the cart */
	private int quantity;

	/** Image URL for displaying the item */
	private String imageURL;

	/**
	 * Constructor used to create a cart item.
	 */
	public CartItem(int itemId, String name, double price, int quantity, String imageURL) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imageURL = imageURL;
	}

	/** Returns the item ID */
	public int getItemId() {
		return itemId;
	}

	/** Returns the item name */
	public String getName() {
		return name;
	}

	/** Returns the price per unit */
	public double getPrice() {
		return price;
	}

	/** Returns the quantity */
	public int getQuantity() {
		return quantity;
	}

	/** Returns the image URL */
	public String getImageURL() {
		return imageURL;
	}

	/** Updates the item quantity */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/** Calculates and returns total price for this item */
	public double getTotalPrice() {
		return price * quantity;
	}
}
