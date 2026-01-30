<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.food.model.User"%>

<!--
================================================================================
Page Name   : orderSuccess.jsp
Purpose     : Displays order confirmation message after successful checkout.
              Ensures user authentication and provides navigation options
              to continue ordering or return to home.
================================================================================
-->

<%
/*
  Validate user session.
  Redirects unauthenticated users to login page
  and preserves intended destination.
*/
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect(
        request.getContextPath() + "/login?redirect=/orderSuccess.jsp"
    );
    return;
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Placed | FoodieHub</title>

<!-- Order Success Page Styles -->
<link rel="stylesheet"
    href="<%=request.getContextPath()%>/orderSuccess.css">

<!-- Inline styles for logout button positioning -->
<style>
.logout-container {
    position: absolute;
    top: 20px;
    right: 30px;
    z-index: 10;
}

.logout-btn {
    padding: 8px 18px;
    border-radius: 20px;
    border: none;
    background: #ff9800;
    color: #fff;
    font-weight: 600;
    cursor: pointer;
}

.logout-btn:hover {
    background: #ffb347;
}
</style>
</head>

<body>

<!-- ================= LOGOUT ACTION ================= -->
<!-- Allows user to explicitly end session from success page -->
<div class="logout-container">
    <form
        action="<%=request.getContextPath()%>/logout?redirect=/home"
        method="post">
        <button type="submit" class="logout-btn">Logout</button>
    </form>
</div>

<!-- ================= PAGE CONTENT ================= -->
<div class="page-wrapper">

    <!-- ================= SUCCESS CARD ================= -->
    <!-- Displays order confirmation and next actions -->
    <div class="success-card">

        <!-- Success illustration -->
        <div class="success-image">
            <img
                src="https://i.pinimg.com/736x/a4/c3/cd/a4c3cdfe8a0055a855bf1fad904271fc.jpg"
                alt="Order Success">
        </div>

        <h2>Order Confirmed! 🎉</h2>

        <!-- Personalized success message -->
        <p><strong>Awesome choice, <%=user.getUserName()%>! 🙌</strong></p>

        <p>
            Your order has been successfully placed and our kitchen is already on it 🔥<br>
            Fresh ingredients, expert chefs, and lightning-fast delivery — all for you 🍔🍕
        </p>

        <p>
            Sit back, relax, and get ready to enjoy your meal 😋<br>
            Your food will arrive hot, fresh, and right on time 🚀
        </p>

        <!-- ================= ACTION BUTTONS ================= -->
        <!-- Navigation options after successful order -->
        <div class="action-buttons">
            <a href="<%=request.getContextPath()%>/restaurants"
               class="btn order-more">
               🍽️ Order More
            </a>

            <a href="<%=request.getContextPath()%>/home"
               class="btn go-home">
               🏠 Go to Home
            </a>
        </div>

    </div>

</div>

</body>
</html>
