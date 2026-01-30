<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--
================================================================================
Page Name   : login.jsp
Purpose     : Handles user authentication.
              Displays login form and error messages.
              Stores redirect target to navigate user back after successful login.
================================================================================
-->

<%
/*
  Capture redirect target from request parameter.
  This value is stored in session and used after successful login
  to redirect the user back to the intended page.
*/
String redirect = request.getParameter("redirect");
if (redirect != null && !redirect.isEmpty()) {
    session.setAttribute("redirectAfterLogin", redirect);
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>FoodieHub - Login</title>

<!-- Login Page Styles -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/login.css" />
</head>

<body>

<!-- ================= MAIN CONTAINER ================= -->
<div class="container">

    <!-- ================= LEFT PANEL ================= -->
    <!-- Branding and promotional content -->
    <div class="left-panel">
        <h1>FOODIEHUB</h1>
        <p>
            Discover flavors that excite your senses.<br />
            Fresh, fast, and crafted with passion.<br />
            Your next delicious bite starts here.<br />
            Welcome to the world of taste!
        </p>
    </div>

    <!-- ================= RIGHT PANEL ================= -->
    <!-- Login form and validation messages -->
    <div class="right-panel">
        <div class="login-card">

            <h2>USER LOGIN</h2>

            <!-- ================= LOGIN MESSAGE ================= -->
            <!-- Displays error or status message from LoginServlet -->
            <%
            String msg = (String) request.getAttribute("message");
            if (msg != null) {
            %>
                <p class="error-msg"><%=msg%></p>
            <%
            }
            %>

            <!-- ================= LOGIN FORM ================= -->
            <!-- Submits credentials to LoginServlet -->
            <form action="login" method="post">

                <div class="input-box">
                    <img
                        src="https://i.pinimg.com/736x/cc/32/7c/cc327c1a541af1a6b7903412e51aa264.jpg"
                        class="left" />
                    <input type="email" name="email"
                        placeholder="Enter Your Email" required />
                </div>

                <div class="input-box">
                    <input type="password" name="password"
                        placeholder="Enter Your Password" required />
                    <img
                        src="https://i.pinimg.com/1200x/ef/99/2f/ef992f5f3e2e3d3fccae1353afedf1e7.jpg"
                        class="right" />
                </div>

                <button type="submit" class="btn-login">LOGIN</button>

                <!-- Registration navigation -->
                <p class="signup">
                    Don’t have an account? <a href="register.jsp">Create one</a>
                </p>
            </form>

        </div>
    </div>

</div>

<!-- ================= UI ENHANCEMENT SCRIPT ================= -->
<!-- Adds focus glow effect to input fields -->
<script>
document.querySelectorAll("input").forEach((input) => {
    input.addEventListener("focus", () => {
        input.style.boxShadow = "0 0 12px rgba(255,180,0,0.9)";
    });
    input.addEventListener("blur", () => {
        input.style.boxShadow = "none";
    });
});
</script>

</body>
</html>
