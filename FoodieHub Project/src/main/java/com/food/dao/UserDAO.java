package com.food.dao;

import java.util.List;
import com.food.model.User;

/**
 * DAO interface for managing User persistence operations. Defines database
 * access methods related to user management, authentication, and registration.
 */
public interface UserDAO {

	/**
	 * Persists a new user record in the database.
	 *
	 * @param user the user entity to be added
	 */
	void addUser(User user);

	/**
	 * Retrieves a user based on the unique user ID.
	 *
	 * @param userId the user's unique identifier
	 * @return the matching User object, or null if not found
	 */
	User getUserById(int userId);

	/**
	 * Updates an existing user's details in the database.
	 *
	 * @param user the user entity containing updated values
	 */
	void updateUser(User user);

	/**
	 * Removes a user record from the database using user ID.
	 *
	 * @param userId the user's unique identifier
	 */
	void deleteUser(int userId);

	/**
	 * Fetches all user records from the database.
	 *
	 * @return a list of all users
	 */
	List<User> getAllUsers();

	/**
	 * Retrieves a user by matching email and password credentials.
	 *
	 * @param email    the user's email address
	 * @param password the user's password
	 * @return the authenticated User object, or null if credentials are invalid
	 */
	User getUserByEmailAndPassword(String email, String password);

	/**
	 * Validates user login credentials.
	 *
	 * @param email    the user's email address
	 * @param password the user's password
	 * @return the authenticated User object, or null if validation fails
	 */
	User validateUser(String email, String password);

	/**
	 * Checks whether an email address already exists in the system.
	 *
	 * @param email the email address to check
	 * @return true if email exists, otherwise false
	 */
	boolean isEmailExists(String email);

	/**
	 * Registers a new user in the system.
	 *
	 * @param user the user entity to register
	 * @return true if registration is successful, otherwise false
	 */
	boolean registerUser(User user);
}
