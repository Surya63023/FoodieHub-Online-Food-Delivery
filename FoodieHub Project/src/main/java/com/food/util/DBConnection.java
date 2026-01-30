package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class responsible for managing database connections. Loads the JDBC
 * driver and provides a method to obtain a database connection.
 */
public class DBConnection {

	/** Database connection URL */
	private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivery";

	/** Database username */
	private static final String USER = "root";

	/** Database password */
	private static final String PASSWORD = "Surya@63023";

	/**
	 * Static block to load the MySQL JDBC driver once when the class is loaded into
	 * memory.
	 */
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// Driver class not found in classpath
			e.printStackTrace();
		}
	}

	/**
	 * Creates and returns a new database connection.
	 *
	 * @return Connection object if successful, otherwise null
	 */
	public static Connection getConnection() {
		try {
			// Establish and return a new database connection
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// Handles database connection failure
			System.out.println("Database connection failed!");
			e.printStackTrace();
			return null;
		}
	}
}
