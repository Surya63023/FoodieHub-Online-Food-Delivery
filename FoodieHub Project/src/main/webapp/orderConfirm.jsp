<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.food.model.Cart"%>
<%@ page import="com.food.model.CartItem"%>
<%@ page import="com.food.model.User"%>
<%@ page import="java.util.Collection"%>

<!--
================================================================================
Page Name   : orderConfirm.jsp
Purpose     : Allows authenticated users to review cart items,
              confirm delivery details, select payment method,
              and place the final order.
================================================================================
-->

<%
/*
  Fetch required session data.
  • user : Logged-in user details
  • cart : Cart containing selected items
*/
User user = (User) session.getAttribute("user");
Cart cart = (Cart) session.getAttribute("cart");

/*
  Enforce authentication.
  Redirects unauthenticated users to login page
  and preserves intended destination.
*/
if (user == null) {
    response.sendRedirect(
        request.getContextPath() + "/login?redirect=/orderConfirm.jsp"
    );
    return;
}

/*
  Validate cart before allowing order confirmation.
  Redirects user back to cart page if cart is empty.
*/
if (cart == null || cart.isEmpty()) {
    response.sendRedirect("cart.jsp");
    return;
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Place Order | FoodieHub</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Order Confirmation Page Styles -->
<link rel="stylesheet"
    href="<%=request.getContextPath()%>/orderConfirm.css">
</head>

<body>

<!-- ================= MAIN CONTAINER ================= -->
<div class="container">

    <!-- ================= LEFT PANEL ================= -->
    <!-- Displays list of items being ordered -->
    <div class="card left">
        <h2>Review Your Order</h2>

        <%
        /*
          Calculate subtotal dynamically based on cart items.
        */
        double subtotal = 0;
        Collection<CartItem> items = cart.getItems();

        for (CartItem item : items) {
            double itemTotal = item.getTotalPrice();
            subtotal += itemTotal;
        %>

        <!-- Individual order item -->
        <div class="item-card">
            <div class="item-info">
                <h4><%=item.getName()%></h4>
                <span>Qty: <%=item.getQuantity()%></span>
            </div>
            <div class="item-price">₹ <%=itemTotal%></div>
        </div>

        <%
        }
        double delivery = 40;
        double grandTotal = subtotal + delivery;
        %>
    </div>

    <!-- ================= RIGHT PANEL ================= -->
    <!-- Displays order summary and checkout form -->
    <div class="card right">

        <h3>Order Summary</h3>

        <div class="row">
            <span>Subtotal</span>
            <span>₹ <%=subtotal%></span>
        </div>

        <div class="row">
            <span>Delivery</span>
            <span>₹ <%=delivery%></span>
        </div>

        <div class="row total">
            <span>Total</span>
            <span>₹ <%=grandTotal%></span>
        </div>

        <!-- ================= CHECKOUT FORM ================= -->
        <!-- Submits order details to CheckoutServlet -->
        <form action="checkout" method="post">

            <!-- Delivery address -->
            <h3 style="margin-top:20px;">Delivery Address</h3>
            <textarea name="address" class="address-input" required>
<%=user.getAddress() != null ? user.getAddress() : ""%>
            </textarea>

            <!-- Payment method selection -->
            <h3 style="margin-top:20px;">Payment Method</h3>

            <div class="payment">
                <label>
                    <input type="radio" name="paymentMethod"
                           value="Cash on Delivery" required>
                    <span>Cash on Delivery</span>
                </label>

                <label>
                    <input type="radio" name="paymentMethod"
                           value="UPI Payment">
                    <span>UPI Payment</span>
                </label>

                <label>
                    <input type="radio" name="paymentMethod"
                           value="Credit / Debit Card">
                    <span>Credit / Debit Card</span>
                </label>
            </div>

            <!-- Place order button -->
            <button type="submit" class="btn">Place Order</button>
        </form>

    </div>
</div>

</body>
</html>
