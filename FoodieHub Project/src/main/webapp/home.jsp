<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.food.model.Restaurant"%>
<%@ page import="com.food.model.Menu"%>
<%@ page import="com.food.model.User"%>

<!-- 
================================================================================
Page Name   : home.jsp
Purpose     : Landing page of FoodieHub application.
              Displays hero section, restaurant highlights, popular foods,
              and login/user information based on session state.
================================================================================
-->

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>FoodieHub | Bengaluru Food Delivery</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet" />

<!-- Home Page Styles -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/home.css">
</head>

<body>

	<%
	/* 
	   Fetch logged-in user object from session.
	   Used to conditionally display Login button or User name.
	*/
	User loggedInUser = (User) session.getAttribute("user");
	%>

	<!-- ================= HEADER / NAVBAR ================= -->
	<header>
		<div class="nav">
			<div class="logo">FoodieHub</div>

			<!-- Navigation links for single-page scrolling -->
			<div class="nav-links">
				<a href="#hero">Home</a>
				<a href="#restaurants">Restaurants</a>
				<a href="#foods">Foods</a>
				<a href="#why">Why Us</a>
				<a href="#footer">Contact</a>
			</div>

			<!-- 
				User Authentication Section
				• Shows Login button if user is not logged in
				• Shows logged-in user's name if session exists
			-->
			<div class="nav-auth">
				<%
				if (loggedInUser == null) {
				%>
					<a href="login.jsp" class="login-btn">Login</a>
				<%
				} else {
				%>
					<div class="user-box">
						<div class="user-icon">👤</div>
						<div class="user-name">
							<%= loggedInUser.getUserName() %>
						</div>
					</div>
				<%
				}
				%>
			</div>
		</div>
	</header>

	<!-- ================= HERO SECTION ================= -->
	<!-- Intro section with call-to-action to browse restaurants -->
	<section class="hero reveal" id="hero">
		<div>
			<h1>
				Order <span>Food Online</span> From Bengaluru’s Best
			</h1>
			<p>
				Experience restaurant-quality meals delivered hot and fresh to your
				doorstep from trusted kitchens.
			</p>
			<a href="restaurants"><button>Order Now</button></a>
		</div>

		<!-- Decorative hero image -->
		<div class="orbit-wrapper">
			<div class="orbit">
				<div class="hero-img">
					<img
						src="https://images.unsplash.com/photo-1600891964599-f61ba0e24092" />
				</div>
			</div>
		</div>
	</section>

	<!-- ================= RESTAURANT LIST ================= -->
	<!-- Displays top restaurants fetched from controller -->
	<section id="restaurants" class="reveal">
		<h2>Top Restaurants Near You</h2>

		<div class="scroll-wrapper">
			<div class="scroll-track">

				<%
				/* 
				   Restaurant list populated by servlet.
				   Each restaurant is rendered as a clickable card.
				*/
				List<Restaurant> restaurantList =
				    (List<Restaurant>) request.getAttribute("restaurantList");

				if (restaurantList != null && !restaurantList.isEmpty()) {
					for (Restaurant restaurant : restaurantList) {
				%>

				<a href="restaurants" style="text-decoration:none;color:inherit;">
					<div class="card">
						<img
							src="<%=request.getContextPath()%>/images/restaurants/<%=restaurant.getImageURL()%>" />
						<div class="card-body">
							<h4><%=restaurant.getRestaurantName()%></h4>
							<p><%=restaurant.getCuisine()%> • ⭐ <%=restaurant.getRating()%></p>
						</div>
					</div>
				</a>

				<%
					}
				}
				%>

			</div>
		</div>
	</section>

	<!-- ================= FOOD ITEMS ================= -->
	<!-- Displays popular menu items fetched from controller -->
	<section id="foods" class="reveal">
		<h2>Must-Try Dishes</h2>

		<div class="scroll-wrapper">
			<div class="scroll-track">

				<%
				/* 
				   Menu list populated by servlet.
				   Displays popular dishes across restaurants.
				*/
				List<Menu> menuList =
				    (List<Menu>) request.getAttribute("menuList");

				if (menuList != null && !menuList.isEmpty()) {
					for (Menu menu : menuList) {
				%>

				<div class="card">
					<img
						src="<%=request.getContextPath()%>/images/<%=menu.getImageURL()%>" />
					<div class="card-body">
						<h4><%=menu.getMenuName()%></h4>
						<p><%=menu.getDescription()%></p>
					</div>
				</div>

				<%
					}
				}
				%>

			</div>
		</div>
	</section>

	<!-- ================= WHY CHOOSE US ================= -->
	<!-- Static section explaining platform benefits -->
	<section class="why reveal" id="why">
		<h2>Why FoodieHub?</h2>

		<div class="why-grid">
			<div class="why-card">
				<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTT91Eoz5_3E4yQA2OIdEqQSDWQdAlDFafIzA&s" />
				<h4>Fast Delivery</h4>
				<p>Hot meals delivered quickly from nearby kitchens.</p>
			</div>

			<div class="why-card">
				<img src="https://cdn-icons-png.flaticon.com/512/1046/1046784.png" />
				<h4>Trusted Kitchens</h4>
				<p>Only verified and top-rated restaurants.</p>
			</div>

			<div class="why-card">
				<img src="https://cdn-icons-png.flaticon.com/512/684/684908.png" />
				<h4>Live Tracking</h4>
				<p>Track your order in real time.</p>
			</div>
		</div>
	</section>

	<!-- ================= FOOTER / CONTACT ================= -->
	<!-- Footer section with contact and navigation links -->
	<footer id="footer" class="reveal">
		<div class="footer-grid">
			<div>
				<h4>Contact</h4>
				<p>BTM Layout, Bengaluru</p>
				<p>+91 63023 79483</p>
				<p>support@foodiehub.in</p>
			</div>

			<div>
				<h4>Explore</h4>
				<a href="#">About Us</a><br />
				<a href="#">Team</a><br />
				<a href="#">Testimonials</a>
			</div>

			<div>
				<h4>Services</h4>
				<a href="#">Quick Delivery</a><br />
				<a href="#">Fresh Food</a><br />
				<a href="#">Top Restaurants</a>
			</div>

			<div>
				<h4>Help</h4>
				<a href="#">Support</a><br />
				<a href="#">Order Issues</a><br />
				<a href="#">FAQs</a>
			</div>
		</div>

		<div class="footer-bottom">
			© 2026 FoodieHub. All Rights Reserved.
		</div>
	</footer>

	<!-- ================= PAGE ANIMATION SCRIPT ================= -->
	<!-- Handles scroll-based reveal animations -->
	<script>
		const reveals = document.querySelectorAll(".reveal");

		function revealOnScroll() {
			reveals.forEach(el => {
				const top = el.getBoundingClientRect().top;
				if (top < window.innerHeight - 120) {
					el.classList.add("active");
				}
			});
		}

		window.addEventListener("scroll", revealOnScroll);
		window.addEventListener("load", revealOnScroll);
	</script>

</body>
</html>
