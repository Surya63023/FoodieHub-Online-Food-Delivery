<%@ page import="java.util.List"%>
<%@ page import="com.food.model.Menu"%>

<!--
================================================================================
Page Name   : menu.jsp
Purpose     : Displays the menu items of a selected restaurant.
              Allows users to add available food items to the cart.
              Handles login/logout visibility based on session state.
================================================================================
-->

<%
/*
  Menu list populated by MenuServlet.
  Contains all food items for the selected restaurant.
*/
List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>FoodieHub | Menu</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Menu Page Styles -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/menu.css">
</head>

<body>

<!-- ================= HEADER ================= -->
<!-- Displays application name and authentication controls -->
<header>
  <h1>FoodieHub</h1>

  <!--
    Authentication Navigation
    • Shows Logout button when user session exists
    • Shows Login link with redirect back to current menu page when not logged in
  -->
  <div class="auth-nav">
    <%
      Object user = session.getAttribute("user");
      if (user != null) {
    %>
        <form
          action="<%= request.getContextPath() %>/logout?redirect=/menu<%= request.getQueryString() != null ? "?" + request.getQueryString() : "" %>"
          method="post"
          style="display:inline;"
        >
          <button type="submit" class="auth-btn logout-btn">
            Logout
          </button>
        </form>
    <%
      } else {
    %>
        <a
          href="login.jsp?redirect=/menu<%= request.getQueryString() != null ? "?" + request.getQueryString() : "" %>"
          class="auth-btn"
        >
          Login
        </a>
    <%
      }
    %>
  </div>
</header>

<!-- ================= PAGE TITLE ================= -->
<!-- Static section introducing the menu -->
<section class="page-title">
  <h2>Our Menu</h2>
  <p class="tagline">Freshly prepared dishes, just for you</p>
  <p class="desc">
    Discover a curated selection of chef-crafted meals
    made with premium ingredients, authentic flavors,
    and hygienic preparation - delivered hot and fresh,
    every single time.
  </p>
</section>

<!-- ================= MENU LIST ================= -->
<!-- Dynamically renders food items for the selected restaurant -->
<section class="menu">

<%
if (menuList != null) {
  for (Menu menu : menuList) {
%>

  <div class="menu-card">

    <!-- Food image -->
    <div class="food-img">
      <img
        src="<%=request.getContextPath()%>/images/<%=menu.getImageURL()%>"
        alt="<%=menu.getMenuName()%>">
    </div>

    <!-- Food details -->
    <div class="menu-content">
      <h3><%=menu.getMenuName()%></h3>
      <p><%=menu.getDescription()%></p>
      <span class="price">&#8377;<%=menu.getPrice()%></span>
    </div>

    <!-- Add-to-cart or availability status -->
    <%
    if (menu.isAvailable()) {
    %>
      <form action="<%=request.getContextPath()%>/cart" method="post">
        <input type="hidden" name="menuId" value="<%=menu.getMenuId()%>">
        <input type="hidden" name="restaurantId" value="<%=menu.getRestaurantId()%>">
        <input type="hidden" name="quantity" value="1">
        <input type="hidden" name="action" value="add">
        <button type="submit" class="add-btn">Add to Cart</button>
      </form>
    <%
    } else {
    %>
      <button class="add-btn" disabled style="opacity: 0.5;">Unavailable</button>
    <%
    }
    %>

  </div>

<%
  }
}
%>

</section>

<!-- ================= ANIMATION SCRIPT ================= -->
<!-- Applies scroll-based reveal animation to menu cards -->
<script>
const observer = new IntersectionObserver(entries => {
  entries.forEach(entry => {
    if (entry.isIntersecting) entry.target.classList.add("show");
  });
},{ threshold: 0.25 });

document.querySelectorAll(".menu-card")
  .forEach(card => observer.observe(card));
</script>

</body>
</html>
