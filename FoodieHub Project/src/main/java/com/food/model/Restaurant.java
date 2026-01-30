package com.food.model;

/**
 * Represents a restaurant listed in the Online Food Delivery application.
 * Stores restaurant details, availability status, and ownership information.
 */
public class Restaurant {

	/** Unique identifier for the restaurant */
	private int restaurantId;

	/** Display name of the restaurant */
	private String restaurantName;

	/** Physical location or address of the restaurant */
	private String address;

	/** Average customer rating of the restaurant */
	private double rating;

	/** Type of cuisine offered (e.g., Indian, Chinese) */
	private String cuisine;

	/** Estimated delivery time shown to users */
	private String deliveryTime;

	/** Indicates whether the restaurant is currently active */
	private boolean isActive;

	/** Image URL used for restaurant display */
	private String imageURL;

	/** Admin user ID who manages this restaurant */
	private int adminUserId;

	/** Short description about the restaurant */
	private String description;

	/** Default constructor */
	public Restaurant() {
	}

	/**
	 * Constructor used when all restaurant details are available.
	 */
	public Restaurant(int restaurantId, String restaurantName, String address, double rating, String cuisine,
			String deliveryTime, boolean isActive, String imageURL, int adminUserId, String description) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.address = address;
		this.rating = rating;
		this.cuisine = cuisine;
		this.deliveryTime = deliveryTime;
		this.isActive = isActive;
		this.imageURL = imageURL;
		this.adminUserId = adminUserId;
		this.description = description;
	}

	/** Returns the restaurant ID */
	public int getRestaurantId() {
		return restaurantId;
	}

	/** Returns the restaurant name */
	public String getRestaurantName() {
		return restaurantName;
	}

	/** Returns the restaurant address */
	public String getAddress() {
		return address;
	}

	/** Returns the restaurant rating */
	public double getRating() {
		return rating;
	}

	/** Returns the cuisine type */
	public String getCuisine() {
		return cuisine;
	}

	/** Returns the estimated delivery time */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/** Returns whether the restaurant is active */
	public boolean isActive() {
		return isActive;
	}

	/** Returns the restaurant image URL */
	public String getImageURL() {
		return imageURL;
	}

	/** Returns the admin user ID */
	public int getAdminUserId() {
		return adminUserId;
	}

	/** Returns the restaurant description */
	public String getDescription() {
		return description;
	}

	/** Sets the restaurant ID */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	/** Sets the restaurant name */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	/** Sets the restaurant address */
	public void setAddress(String address) {
		this.address = address;
	}

	/** Sets the restaurant rating */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/** Sets the cuisine type */
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	/** Sets the delivery time */
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/** Sets the active status of the restaurant */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/** Sets the restaurant image URL */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/** Sets the admin user ID */
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	/** Sets the restaurant description */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", address="
				+ address + ", rating=" + rating + ", cuisine=" + cuisine + ", deliveryTime=" + deliveryTime
				+ ", isActive=" + isActive + ", imageURL=" + imageURL + ", adminUserId=" + adminUserId
				+ ", description=" + description + "]";
	}
}
