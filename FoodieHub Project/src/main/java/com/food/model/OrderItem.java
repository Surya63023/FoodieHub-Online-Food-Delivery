package com.food.model;

/**
 * Represents an individual item within a customer order.
 * Stores menu item details, quantity, and pricing information.
 */
public class OrderItem {

    /** Unique identifier for the order item */
    private int orderItemId;

    /** Associated order ID */
    private int orderId;

    /** Name of the ordered item */
    private String itemName;

    /** Menu ID of the ordered item */
    private int menuId;

    /** Quantity ordered */
    private int quantity;

    /** Total price for this item (quantity × price) */
    private double totalPrice;

    /** Default constructor */
    public OrderItem() {
    }

    /**
     * Constructor used when all order item details are available.
     */
    public OrderItem(int orderItemId, int orderId, String itemName, int menuId, int quantity, double totalPrice) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemName = itemName;
        this.menuId = menuId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    /** Returns the order item ID */
    public int getOrderItemId() {
        return orderItemId;
    }

    /** Sets the order item ID */
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    /** Returns the order ID */
    public int getOrderId() {
        return orderId;
    }

    /** Sets the order ID */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /** Returns the item name */
    public String getItemName() {
        return itemName;
    }

    /** Sets the item name */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /** Returns the menu ID */
    public int getMenuId() {
        return menuId;
    }

    /** Sets the menu ID */
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    /** Returns the quantity */
    public int getQuantity() {
        return quantity;
    }

    /** Sets the quantity */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** Returns the total price */
    public double getTotalPrice() {
        return totalPrice;
    }

    /** Sets the total price */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
