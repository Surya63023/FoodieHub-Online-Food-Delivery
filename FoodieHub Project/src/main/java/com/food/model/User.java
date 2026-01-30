package com.food.model;

import java.sql.Timestamp;

/**
 * Represents a user of the Online Food Delivery application. Stores
 * authentication, contact, and account-related details.
 */
public class User {

	/** Unique identifier for the user */
	private int userId;

	/** Display name of the user */
	private String userName;

	/** Email used for login and communication */
	private String email;

	/** User's contact phone number */
	private String phoneNumber;

	/** Encrypted or plain password for authentication */
	private String password;

	/** Delivery address of the user */
	private String address;

	/** Role of the user (e.g., USER, ADMIN) */
	private String role;

	/** Account creation timestamp */
	private Timestamp createdDate;

	/** Last successful login timestamp */
	private Timestamp lastLoginDate;

	/** Default constructor */
	public User() {
	}

	/**
	 * Constructor used when all user details are available.
	 */
	public User(int userId, String userName, String email, String phoneNumber, String password, String address,
			String role, Timestamp createdDate, Timestamp lastLoginDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * Constructor used during user registration.
	 */
	public User(String userName, String email, String phoneNumber, String password, String address, String role) {
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	/** Returns the user ID */
	public int getUserId() {
		return userId;
	}

	/** Returns the user name */
	public String getUserName() {
		return userName;
	}

	/** Returns the email address */
	public String getEmail() {
		return email;
	}

	/** Returns the phone number */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/** Returns the password */
	public String getPassword() {
		return password;
	}

	/** Returns the delivery address */
	public String getAddress() {
		return address;
	}

	/** Returns the user role */
	public String getRole() {
		return role;
	}

	/** Returns the account creation date */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/** Returns the last login date */
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	/** Sets the user ID */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/** Sets the user name */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** Sets the email address */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Sets the phone number */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/** Sets the password */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Sets the delivery address */
	public void setAddress(String address) {
		this.address = address;
	}

	/** Sets the user role */
	public void setRole(String role) {
		this.role = role;
	}

	/** Sets the account creation date */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/** Sets the last login date */
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", role=" + role + ", createdDate=" + createdDate + ", lastLoginDate="
				+ lastLoginDate + "]";
	}
}
