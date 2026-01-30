<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.food.model.Restaurant" %>

<!--
================================================================================
Page Name   : restaurant.jsp
Purpose     : Displays the list of available restaurants.
              Allows users to browse restaurants and navigate to their menus.
              Shows Login or Logout option based on user session state.
================================================================================
-->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>FoodieHub | Restaurants</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- Google Font -->
  <link
    href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
    rel="stylesheet"
  />

  <!-- Restaurant Page Styles -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/restaurant.css">
</head>

<body>

<!-- ================= HEADER ================= -->
<!-- Displays application title and authentication controls -->
<header>
  <h1>FoodieHub</h1>

  <!--
    Authentication Navigation
    • Shows Logout button if user session exists
    • Shows Login link with redirect back to this page if not logged in
  -->
  <div class="auth-nav">
    <%
      Object user = session.getAttribute("user");
      if (user != null) {
    %>
        <form action="<%= request.getContextPath() %>/logout" method="post" style="display:inline;">
          <button type="submit" class="auth-btn logout-btn">
            Logout
          </button>
        </form>
    <%
      } else {
    %>
        <a href="login.jsp?redirect=/restaurants" class="auth-btn">
          Login
        </a>
    <%
      }
    %>
  </div>
</header>

<!-- ================= PAGE TITLE ================= -->
<!-- Introductory section describing restaurant listing -->
<section class="page-title">
  <h2>Restaurants Near You</h2>
  <p>
    Discover Bengaluru’s most loved restaurants, carefully curated for quality,
    taste, and reliability.<br>
    From local favorites to premium dining experiences, explore menus that
    suit every craving.<br>
    Freshly prepared meals, transparent ratings, and quick delivery —
    all in one place.
  </p>
</section>

<!-- ================= RESTAURANT LIST GRID ================= -->
<!-- Dynamically renders restaurants provided by the servlet -->
<section class="grid">

<%
  /*
    Restaurant list set by RestaurantServlet.
    Each restaurant is rendered as a clickable card
    that navigates to its corresponding menu page.
  */
  List<Restaurant> restaurantList =
      (List<Restaurant>) request.getAttribute("restaurantList");

  if (restaurantList != null) {
    for (Restaurant restaurant : restaurantList) {
%>

  <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="card">

    <!-- Restaurant image -->
    <div class="card-img">
      <img
        src="<%= request.getContextPath() %>/images/restaurants/<%= restaurant.getImageURL() %>"
        alt="<%= restaurant.getRestaurantName() %>"
      />
    </div>

    <!-- Restaurant details -->
    <div class="card-content">
      <h3><%= restaurant.getRestaurantName() %></h3>

      <p class="cuisine"><%= restaurant.getCuisine() %></p>

      <p class="address"><%= restaurant.getAddress() %></p>

      <p class="description"><%= restaurant.getDescription() %></p>

      <!-- Delivery time and rating -->
      <div class="card-footer">
        <span class="delivery">
          ⏱ <%= restaurant.getDeliveryTime() %>
        </span>

        <span class="rating">
          ⭐ <%= restaurant.getRating() %>
        </span>
      </div>
    </div>

  </a>

<%
    }
  }
%>

</section>

<!-- ================= ANIMATION SCRIPT ================= -->
<!-- Adds scroll-based reveal animation to restaurant cards -->
<script>
  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) entry.target.classList.add("show");
      });
    },
    { threshold: 0.25 }
  );

  document.querySelectorAll(".card").forEach((card) => {
    observer.observe(card);
  });
</script>

</body>
</html>
