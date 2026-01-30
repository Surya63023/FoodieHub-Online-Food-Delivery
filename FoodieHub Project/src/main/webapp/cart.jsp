<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.model.Cart"%>
<%@ page import="com.food.model.CartItem"%>

<!--
================================================================================
Page Name   : cart.jsp
Purpose     : Displays the user's shopping cart.
              Allows quantity updates, item removal, and checkout navigation.
              Handles login-aware checkout redirection.
================================================================================
-->

<%
/*
  Cart and related data fetched from session.
  • cart         : Holds selected items
  • restaurantId : Used to navigate back to menu
  • user         : Used to control checkout behavior
*/
Cart cart = (Cart) session.getAttribute("cart");
Integer restaurantId = (Integer) session.getAttribute("restaurantId");
Object user = session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>FoodieHub | Cart</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Cart Page Styles -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/cart.css">
</head>

<body>

<!-- ================= HEADER ================= -->
<!-- Application header (authentication intentionally hidden on cart page) -->
<header>
    <h1>FoodieHub</h1>
</header>

<!-- ================= PAGE TITLE ================= -->
<!-- Intro section explaining cart purpose -->
<section class="page-title">
    <h2>Your Cart</h2>
    <p>
        Review your selected items, adjust quantities, and make sure
        everything looks perfect before proceeding to checkout.
    </p>
</section>

<!-- ================= CART SECTION ================= -->
<section class="cart">

    <!-- ================= CART ITEMS ================= -->
    <!-- Displays cart items if present, otherwise shows empty message -->
    <div>
        <%
        if (cart != null && !cart.isEmpty()) {
            for (CartItem item : cart.getItems()) {
        %>

        <div class="cart-item">

            <!-- Item image -->
            <div class="item-img">
                <img
                    src="<%=request.getContextPath()%>/images/<%=item.getImageURL()%>">
            </div>

            <!-- Item details and quantity controls -->
            <div class="item-info">
                <h3><%=item.getName()%></h3>
                <span class="price">&#8377;<%=item.getPrice()%></span>

                <div class="qty">

                    <!-- Quantity increase / decrease controls -->
                    <div class="qty-controls">

                        <!-- Decrease quantity -->
                        <form action="cart" method="post">
                            <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                            <input type="hidden" name="quantity"
                                   value="<%=item.getQuantity() - 1%>">
                            <input type="hidden" name="restaurantId"
                                   value="<%=restaurantId%>">
                            <input type="hidden" name="action" value="update">
                            <button <%=item.getQuantity() == 1 ? "disabled" : ""%>>−</button>
                        </form>

                        <span><%=item.getQuantity()%></span>

                        <!-- Increase quantity -->
                        <form action="cart" method="post">
                            <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                            <input type="hidden" name="quantity"
                                   value="<%=item.getQuantity() + 1%>">
                            <input type="hidden" name="restaurantId"
                                   value="<%=restaurantId%>">
                            <input type="hidden" name="action" value="update">
                            <button>+</button>
                        </form>
                    </div>

                    <!-- Remove item from cart -->
                    <form action="cart" method="post">
                        <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                        <input type="hidden" name="restaurantId"
                               value="<%=restaurantId%>">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit" class="remove-btn">
                            Remove Item
                        </button>
                    </form>

                </div>
            </div>
        </div>

        <%
            }
        } else {
        %>
        <!-- Empty cart message -->
        <p class="empty-cart">Your cart is empty 🛒</p>
        <%
        }
        %>
    </div>

    <!-- ================= ORDER SUMMARY ================= -->
    <!-- Displays pricing breakdown and checkout action -->
    <div class="summary">
        <h3>Order Summary</h3>

        <div class="line">
            <span>Subtotal</span>
            <span>&#8377;<%=cart != null ? cart.getTotalPrice() : 0%></span>
        </div>

        <div class="line">
            <span>Delivery</span>
            <span>&#8377;<%=cart != null && !cart.isEmpty() ? 40 : 0%></span>
        </div>

        <div class="line total">
            <span>Total</span>
            <span>&#8377;<%=cart != null && !cart.isEmpty()
                    ? cart.getTotalPrice() + 40 : 0%></span>
        </div>

        <%
        if (cart != null && !cart.isEmpty()) {
        %>

        <!-- ================= CHECKOUT NAVIGATION ================= -->
        <%
        if (user != null) {
        %>
            <!-- Logged-in user: direct checkout -->
            <a href="<%=request.getContextPath()%>/orderConfirm.jsp"
               class="checkout">
               Proceed to Checkout
            </a>
        <%
        } else {
        %>
            <!-- Guest user: redirected to login before checkout -->
            <a href="<%=request.getContextPath()%>/login?redirect=/orderConfirm.jsp"
               class="checkout">
               Proceed to Checkout
            </a>
        <%
        }
        %>

        <%
        }
        %>
    </div>

    <!-- ================= ADD MORE ITEMS ================= -->
    <!-- Allows user to return to menu if cart has items -->
    <%
    if (cart != null && !cart.isEmpty()) {
    %>
    <div class="add-more-items">
        <a href="menu?restaurantId=<%=restaurantId%>"
           class="add-more-items-btn">
           Add More Items
        </a>
    </div>
    <%
    }
    %>

</section>

</body>
</html>
