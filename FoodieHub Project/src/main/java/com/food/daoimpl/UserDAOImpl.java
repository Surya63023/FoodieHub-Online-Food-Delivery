package com.food.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.UserDAO;
import com.food.model.User;
import com.food.util.DBConnection;

/**
 * UserDAOImpl ------------- JDBC-based DAO implementation for performing CRUD
 * and authentication operations on the User entity.
 *
 * This class communicates directly with the database using Connection,
 * PreparedStatement, and ResultSet.
 */
public class UserDAOImpl implements UserDAO {

	/* SQL Queries related to User table */
	private static final String INSERT_USER = "INSERT INTO user (userName, email, phoneNumber, password, address, role, createdDate, lastLoginDate) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_USER = "SELECT * FROM user WHERE userId = ?";

	private static final String UPDATE_USER = "UPDATE user SET userName=?, email=?, phoneNumber=?, password=?, address=?, role=? WHERE userId=?";

	private static final String DELETE_USER = "DELETE FROM user WHERE userId = ?";

	private static final String GET_ALL_USERS = "SELECT * FROM user";

	private static final String LOGIN_QUERY = "SELECT * FROM user WHERE email = ? AND password = ?";

	private static final String VALIDATE_USER = "SELECT * FROM user WHERE email = ? AND password = ?";

	private static final String CHECK_EMAIL = "SELECT userId FROM user WHERE email = ?";

	/**
	 * Inserts a new user record into the database.
	 *
	 * @param user User object containing registration details
	 */
	@Override
	public void addUser(User user) {

		// Establish database connection and prepare SQL statement
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT_USER)) {

			// Set values for prepared statement parameters
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhoneNumber());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getRole());
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			ps.setNull(8, Types.TIMESTAMP);

			// Execute INSERT operation
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches a user from the database using userId.
	 *
	 * @param userId unique user identifier
	 * @return User object if found, otherwise null
	 */
	@Override
	public User getUserById(int userId) {

		User user = null;

		// Obtain connection and execute SELECT query
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(GET_USER)) {

			ps.setInt(1, userId);

			// ResultSet holds the data returned from the database
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = extractUser(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Updates existing user details in the database.
	 *
	 * @param user User object containing updated values
	 */
	@Override
	public void updateUser(User user) {

		// Create connection and prepare UPDATE statement
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_USER)) {

			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhoneNumber());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getRole());
			ps.setInt(7, user.getUserId());

			// Execute UPDATE operation
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a user record based on userId.
	 *
	 * @param userId unique user identifier
	 */
	@Override
	public void deleteUser(int userId) {

		// Open connection and execute DELETE query
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_USER)) {

			ps.setInt(1, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all users from the database.
	 *
	 * @return List of User objects
	 */
	@Override
	public List<User> getAllUsers() {

		List<User> list = new ArrayList<>();

		// Execute SELECT query to fetch all user records
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL_USERS);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(extractUser(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Authenticates user using email and password.
	 *
	 * @param email    user email
	 * @param password user password
	 * @return User object if credentials match
	 */
	@Override
	public User getUserByEmailAndPassword(String email, String password) {

		User user = null;

		// Prepare login validation query
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(LOGIN_QUERY)) {

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Validates user credentials.
	 *
	 * @param email    user email
	 * @param password user password
	 * @return User object if valid, otherwise null
	 */
	@Override
	public User validateUser(String email, String password) {

		User user = null;

		// Execute credential validation query
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(VALIDATE_USER)) {

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Checks whether the given email already exists.
	 *
	 * @param email user email
	 * @return true if email exists, false otherwise
	 */
	@Override
	public boolean isEmailExists(String email) {

		// Query database to check email existence
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(CHECK_EMAIL)) {

			ps.setString(1, email);
			return ps.executeQuery().next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Registers a new user.
	 *
	 * @param user User object
	 * @return true after successful insertion
	 */
	@Override
	public boolean registerUser(User user) {
		addUser(user);
		return true;
	}

	/**
	 * Converts ResultSet row into User object.
	 *
	 * @param rs ResultSet containing user data
	 * @return populated User object
	 * @throws SQLException if column access fails
	 */
	private User extractUser(ResultSet rs) throws SQLException {

		return new User(rs.getInt("userId"), rs.getString("userName"), rs.getString("email"),
				rs.getString("phoneNumber"), rs.getString("password"), rs.getString("address"), rs.getString("role"),
				rs.getTimestamp("createdDate"), rs.getTimestamp("lastLoginDate"));
	}
}
