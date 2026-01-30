package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.model.Menu;
import com.food.util.DBConnection;

/**
 * MenuDAOImpl ----------- JDBC-based DAO implementation responsible for
 * performing CRUD operations on the Menu entity.
 *
 * This class directly interacts with the database using Connection,
 * PreparedStatement, Statement, and ResultSet.
 */
public class MenuDAOImpl implements MenuDAO {

	/* SQL queries related to menu table */
	private static final String INSERT_MENU = "INSERT INTO menu (menuName, price, isAvailable, description, imageURL, restaurantId) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_MENU = "SELECT * FROM menu WHERE menuId = ?";
	private static final String UPDATE_MENU = "UPDATE menu SET menuName=?, price=?, isAvailable=?, description=?, imageURL=?, restaurantId=? WHERE menuId=?";
	private static final String DELETE_MENU = "DELETE FROM menu WHERE menuId = ?";
	private static final String GET_ALL_MENU = "SELECT * FROM menu";
	private static final String GET_MENU_BY_RESTAURANT = "SELECT * FROM menu WHERE restaurantId = ?";

	/**
	 * Inserts a new menu item into the database. Also retrieves and sets the
	 * generated menuId.
	 *
	 * @param menu Menu object containing menu details
	 */
	@Override
	public void addMenu(Menu menu) {

		// Create database connection and prepare INSERT statement
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_MENU,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			// Set parameters for INSERT query
			ps.setString(1, menu.getMenuName());
			ps.setDouble(2, menu.getPrice());
			ps.setBoolean(3, menu.isAvailable());
			ps.setString(4, menu.getDescription());
			ps.setString(5, menu.getImageURL());
			ps.setInt(6, menu.getRestaurantId());

			// Execute INSERT operation
			int rows = ps.executeUpdate();

			// Check whether insertion affected any rows
			if (rows == 0) {
				System.err.println("Inserting menu failed, no rows affected.");
			} else {
				// Retrieve auto-generated primary key (menuId)
				try (ResultSet keys = ps.getGeneratedKeys()) {
					if (keys.next()) {
						menu.setMenuId(keys.getInt(1));
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves a menu item using its menuId.
	 *
	 * @param menuId unique identifier of the menu
	 * @return Menu object if found, otherwise null
	 */
	@Override
	public Menu getMenu(int menuId) {

		Menu menu = null;

		// Prepare SELECT query to fetch menu by ID
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(GET_MENU)) {

			ps.setInt(1, menuId);

			// ResultSet holds the data returned from the database
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					// Extract column values from ResultSet
					String menuName = rs.getString("menuName");
					double price = rs.getDouble("price");
					boolean isAvailable = rs.getBoolean("isAvailable");
					String description = rs.getString("description");
					String imageURL = rs.getString("imageURL");
					int restaurantId = rs.getInt("restaurantId");

					// Map row data to Menu object
					menu = new Menu(menuId, menuName, price, isAvailable, description, imageURL, restaurantId);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menu;
	}

	/**
	 * Updates an existing menu item in the database.
	 *
	 * @param menu Menu object containing updated values
	 * @return true if update was successful, otherwise false
	 */
	@Override
	public boolean updateMenu(Menu menu) {

		// Prepare UPDATE statement
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_MENU)) {

			ps.setString(1, menu.getMenuName());
			ps.setDouble(2, menu.getPrice());
			ps.setBoolean(3, menu.isAvailable());
			ps.setString(4, menu.getDescription());
			ps.setString(5, menu.getImageURL());
			ps.setInt(6, menu.getRestaurantId());
			ps.setInt(7, menu.getMenuId());

			// Execute UPDATE operation
			int updated = ps.executeUpdate();
			return updated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a menu item using menuId.
	 *
	 * @param menuId unique identifier of the menu
	 * @return true if deletion was successful, otherwise false
	 */
	@Override
	public boolean deleteMenu(int menuId) {

		// Execute DELETE query
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_MENU)) {

			ps.setInt(1, menuId);
			int deleted = ps.executeUpdate();
			return deleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves all menu items from the database.
	 *
	 * @return list of Menu objects
	 */
	@Override
	public List<Menu> getAllMenu() {

		List<Menu> menuList = new ArrayList<>();

		// Use Statement to execute SELECT query for all menu records
		try (Connection connection = DBConnection.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(GET_ALL_MENU)) {

			while (rs.next()) {

				// Extract column values
				int menuId = rs.getInt("menuId");
				String menuName = rs.getString("menuName");
				double price = rs.getDouble("price");
				boolean isAvailable = rs.getBoolean("isAvailable");
				String description = rs.getString("description");
				String imageURL = rs.getString("imageURL");
				int restaurantId = rs.getInt("restaurantId");

				// Map each row to Menu object
				Menu menu = new Menu(menuId, menuName, price, isAvailable, description, imageURL, restaurantId);

				menuList.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}

	/**
	 * Retrieves all menu items belonging to a specific restaurant.
	 *
	 * @param restaurantId restaurant identifier
	 * @return list of Menu objects for the given restaurant
	 */
	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {

		List<Menu> menuList = new ArrayList<>();

		// Prepare SELECT query to fetch menus by restaurantId
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(GET_MENU_BY_RESTAURANT)) {

			ps.setInt(1, restaurantId);

			// Execute query and process ResultSet
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {

					int menuId = rs.getInt("menuId");
					String menuName = rs.getString("menuName");
					double price = rs.getDouble("price");
					boolean isAvailable = rs.getBoolean("isAvailable");
					String description = rs.getString("description");
					String imageURL = rs.getString("imageURL");
					int restId = rs.getInt("restaurantId");

					Menu menu = new Menu(menuId, menuName, price, isAvailable, description, imageURL, restId);

					menuList.add(menu);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}
}
