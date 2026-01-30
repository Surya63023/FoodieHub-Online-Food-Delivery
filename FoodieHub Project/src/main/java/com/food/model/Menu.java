package com.food.model;

/**
 * Represents a menu item offered by a restaurant in the application. Stores
 * pricing, availability, and display-related details.
 */
public class Menu {

	/** Unique identifier for the menu item */
	private int menuId;

	/** Name of the food item */
	private String menuName;

	/** Price of the menu item */
	private double price;

	/** Indicates whether the item is currently available */
	private boolean isAvailable;

	/** Description of the menu item */
	private String description;

	/** Image URL for displaying the menu item */
	private String imageURL;

	/** Associated restaurant ID */
	private int restaurantId;

	/** Default constructor */
	public Menu() {
	}

	/**
	 * Constructor used when all menu details are available.
	 */
	public Menu(int menuId, String menuName, double price, boolean isAvailable, String description, String imageURL,
			int restaurantId) {

		this.menuId = menuId;
		this.menuName = menuName;
		this.price = price;
		this.isAvailable = isAvailable;
		this.description = description;
		this.imageURL = imageURL;
		this.restaurantId = restaurantId;
	}

	/** Returns the menu ID */
	public int getMenuId() {
		return menuId;
	}

	/** Sets the menu ID */
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	/** Returns the menu name */
	public String getMenuName() {
		return menuName;
	}

	/** Sets the menu name */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/** Returns the price */
	public double getPrice() {
		return price;
	}

	/** Sets the price */
	public void setPrice(double price) {
		this.price = price;
	}

	/** Returns availability status */
	public boolean isAvailable() {
		return isAvailable;
	}

	/** Sets availability status */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/** Returns the description */
	public String getDescription() {
		return description;
	}

	/** Sets the description */
	public void setDescription(String description) {
		this.description = description;
	}

	/** Returns the image URL */
	public String getImageURL() {
		return imageURL;
	}

	/** Sets the image URL */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/** Returns the associated restaurant ID */
	public int getRestaurantId() {
		return restaurantId;
	}

	/** Sets the associated restaurant ID */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", price=" + price + ", isAvailable=" + isAvailable
				+ ", description=" + description + ", imageURL=" + imageURL + ", restaurantId=" + restaurantId + "]";
	}
}
