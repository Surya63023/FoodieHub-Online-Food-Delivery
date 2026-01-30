<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--
================================================================================
Page Name   : register.jsp
Purpose     : Handles new user registration.
              Collects user details and submits them to RegisterServlet.
              Performs client-side password confirmation validation.
================================================================================
-->

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>FoodieHub - Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- Registration Page Styles -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/register.css" />
</head>

<body>

<!-- ================= MAIN AUTH WRAPPER ================= -->
<div class="auth-wrapper">

	<!-- ================= LEFT IMAGE / INFO PANEL ================= -->
	<!-- Branding and platform highlights -->
	<div class="auth-image">
		<h1>Join <span>FoodieHub</span></h1>
		<div class="tagline">Order smarter. Eat better. Faster.</div>
		<div class="desc">
			Discover the best restaurants around you, enjoy exclusive deals,
			and experience lightning-fast delivery — all from one powerful platform.
		</div>
		<div class="stats">
			✔ 10,000+ restaurants &nbsp; • &nbsp; ✔ Trusted by millions &nbsp; • &nbsp; ✔ 24×7 delivery
		</div>
	</div>

	<!-- ================= REGISTRATION FORM PANEL ================= -->
	<!-- Collects user details for account creation -->
	<div class="auth-form">
		<h2>Create Account</h2>
		<small>Register once and start ordering instantly</small>

		<!-- ================= REGISTER FORM ================= -->
		<!-- Submits registration data to RegisterServlet -->
		<form id="registerForm" action="register" method="post">

			<div class="field">
				<label>Full Name</label>
				<input type="text" name="userName" placeholder="Enter your full name" required />
			</div>

			<div class="field">
				<label>Email</label>
				<input type="email" name="email" placeholder="Enter your email" required />
			</div>

			<div class="field">
				<label>Phone Number</label>
				<input type="tel" name="phoneNumber" placeholder="Enter phone number" required />
			</div>

			<div class="field">
				<label>Password</label>
				<input type="password" id="password" name="password"
					placeholder="Create password" required />
			</div>

			<div class="field">
				<label>Confirm Password</label>
				<input type="password" id="confirmPassword"
					placeholder="Re-enter password" required />
			</div>

			<div class="field">
				<label>Address</label>
				<textarea name="address"
					placeholder="Enter your delivery address" required></textarea>
			</div>

			<!-- Client-side validation message -->
			<div class="error" id="errorMsg">Passwords do not match</div>

			<button type="submit" class="btn-register">Create Account</button>
		</form>

		<!-- Login navigation for existing users -->
		<div class="login-text">
			Already have an account? <a href="login.jsp">Login</a>
		</div>
	</div>

</div>

<!-- ================= CLIENT-SIDE VALIDATION SCRIPT ================= -->
<!-- Validates password and confirm password before form submission -->
<script>
	const form = document.getElementById("registerForm");
	const password = document.getElementById("password");
	const confirmPassword = document.getElementById("confirmPassword");
	const errorMsg = document.getElementById("errorMsg");

	form.addEventListener("submit", function (e) {
		if (password.value !== confirmPassword.value) {
			e.preventDefault();
			errorMsg.style.display = "block";
		} else {
			errorMsg.style.display = "none";
			alert("Registration Successful!");
		}
	});
</script>

</body>
</html>
