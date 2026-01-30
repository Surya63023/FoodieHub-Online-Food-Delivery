package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;
import com.food.util.DBConnection;

/**
 * RestaurantDAOImpl ------------------ JDBC-based DAO implementation for
 * handling CRUD operations related to Restaurant entities.
 *
 * This class uses plain JDBC (Connection, PreparedStatement, ResultSet) to
 * interact with the restaurant table.
 */
public class RestaurantDAOImpl implements RestaurantDAO {

	/* SQL queries related to restaurant table */
	private static final String INSERT_RESTAURANT = "INSERT INTO restaurant (restaurantName, address, rating, cuisine, deliveryTime, isActive, imageURL, adminUserId, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_RESTAURANT = "SELECT * FROM restaurant WHERE restaurantId = ?";

	private static final String UPDATE_RESTAURANT = "UPDATE restaurant SET restaurantName=?, address=?, rating=?, cuisine=?, "
			+ "deliveryTime=?, isActive=?, imageURL=?, adminUserId=?, description=? " + "WHERE restaurantId=?";

	private static final String DELETE_RESTAURANT = "DELETE FROM restaurant WHERE restaurantId = ?";

	private static final String GET_ALL_RESTAURANT = "SELECT * FROM restaurant";

	/**
	 * Inserts a new restaurant record into the database.
	 *
	 * @param restaurant Restaurant object containing details to be stored
	 */
	@Override
	public void addRestaurant(Restaurant restaurant) {

		// Obtain database connection and prepare INSERT statement
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT)) {

			// Set values for INSERT query parameters
			preparedStatement.setString(1, restaurant.getRestaurantName());
			preparedStatement.setString(2, restaurant.getAddress());
			preparedStatement.setDouble(3, restaurant.getRating());
			preparedStatement.setString(4, restaurant.getCuisine());
			preparedStatement.setString(5, restaurant.getDeliveryTime());
			preparedStatement.setBoolean(6, restaurant.isActive());
			preparedStatement.setString(7, restaurant.getImageURL());
			preparedStatement.setInt(8, restaurant.getAdminUserId());
			preparedStatement.setString(9, restaurant.getDescription());

			// Execute INSERT operation
			preparedStatement.executeUpdate();
			System.out.println("Restaurant inserted successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches a restaurant using its unique restaurantId.
	 *
	 * @param restaurantId unique identifier of the restaurant
	 * @return Restaurant object if found, otherwise null
	 */
	@Override
	public Restaurant getRestaurant(int restaurantId) {

		Restaurant restaurant = null;

		// Prepare SELECT query to fetch restaurant by ID
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT)) {

			preparedStatement.setInt(1, restaurantId);

			// ResultSet contains the data returned from the database
			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) {

				// Extract column values from ResultSet
				String restaurantName = res.getString("restaurantName");
				String address = res.getString("address");
				double rating = res.getDouble("rating");
				String cuisine = res.getString("cuisine");
				String deliveryTime = res.getString("deliveryTime");
				boolean isActive = res.getBoolean("isActive");
				String imageURL = res.getString("imageURL");
				int adminUserId = res.getInt("adminUserId");
				String description = res.getString("description");

				// Map database row to Restaurant object
				restaurant = new Restaurant(restaurantId, restaurantName, address, rating, cuisine, deliveryTime,
						isActive, imageURL, adminUserId, description);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurant;
	}

	/**
	 * Updates an existing restaurant record in the database.
	 *
	 * @param restaurant Restaurant object containing updated values
	 */
	@Override
	public void updateRestaurant(Restaurant restaurant) {

		// Create connection and prepare UPDATE statement
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT)) {

			preparedStatement.setString(1, restaurant.getRestaurantName());
			preparedStatement.setString(2, restaurant.getAddress());
			preparedStatement.setDouble(3, restaurant.getRating());
			preparedStatement.setString(4, restaurant.getCuisine());
			preparedStatement.setString(5, restaurant.getDeliveryTime());
			preparedStatement.setBoolean(6, restaurant.isActive());
			preparedStatement.setString(7, restaurant.getImageURL());
			preparedStatement.setInt(8, restaurant.getAdminUserId());
			preparedStatement.setString(9, restaurant.getDescription());
			preparedStatement.setInt(10, restaurant.getRestaurantId());

			// Execute UPDATE operation
			preparedStatement.executeUpdate();
			System.out.println("Restaurant updated successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a restaurant record using restaurantId.
	 *
	 * @param restaurantId unique identifier of the restaurant
	 */
	@Override
	public void deleteRestaurant(int restaurantId) {

		// Execute DELETE query
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT)) {

			preparedStatement.setInt(1, restaurantId);
			preparedStatement.executeUpdate();
			System.out.println("Restaurant deleted successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all restaurants from the database.
	 *
	 * @return list of Restaurant objects
	 */
	@Override
	public List<Restaurant> getAllRestaurants() {

		List<Restaurant> restaurantList = new ArrayList<>();

		// Execute SELECT query to fetch all restaurant records
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(GET_ALL_RESTAURANT);
				ResultSet res = ps.executeQuery()) {

			while (res.next()) {

				// Read column values from ResultSet
				int restaurantId = res.getInt("restaurantId");
				String restaurantName = res.getString("restaurantName");
				String address = res.getString("address");
				double rating = res.getDouble("rating");
				String cuisine = res.getString("cuisine");
				String deliveryTime = res.getString("deliveryTime");
				boolean isActive = res.getBoolean("isActive");
				String imageURL = res.getString("imageURL");
				int adminUserId = res.getInt("adminUserId");
				String description = res.getString("description");

				// Map each row to Restaurant object
				Restaurant restaurant = new Restaurant(restaurantId, restaurantName, address, rating, cuisine,
						deliveryTime, isActive, imageURL, adminUserId, description);

				restaurantList.add(restaurant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantList;
	}
}
