<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>InventorySystem - Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
section {
	padding: 60px 0;
}

.navbar {
	margin-bottom: 0;
}
</style>
</head>
<body>

	<!-- Header / Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">InventorySystem</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link" href="#home">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#about">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#contact">Contact</a></li>
					<li class="nav-item"><a class="nav-link" href="#profile">Company
							Profile</a></li>
					<li class="nav-item"><a class="nav-link" href="#login">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Home Section -->
	<section id="home" class="bg-light text-center">
		<div class="container">
			<h1>Welcome to InventorySystem</h1>
			<p class="lead">Your one-stop solution for inventory and billing
				management</p>
		</div>
	</section>

	<!-- About Section -->
	<section id="about">
		<div class="container">
			<h2>About Us</h2>
			<section id="about">
				<div class="container">

					<p>InventorySystem is a smart, user-friendly inventory and
						billing management platform designed to streamline operations for
						businesses of all sizes. Our mission is to digitize and automate
						traditional stock handling and invoicing systems with a modern,
						intuitive solution.</p>
					<p>We believe in empowering businesses with real-time
						visibility, accuracy, and control over their inventory, purchases,
						and sales. Our system is built with the latest technology and best
						practices to ensure security, scalability, and seamless
						experience.</p>
					<p>From small shops to large enterprises, our goal is to
						eliminate manual errors and provide tools that save time, reduce
						cost, and improve decision-making.</p>
				</div>
			</section>
		</div>
	</section>



	<section id="profile">
		<div class="container">
			<h2>Company Profile</h2>
			<p>
				<strong>Company Name:</strong> InventorySystem Pvt. Ltd.
			</p>
			<p>
				<strong>Founded:</strong> 2020
			</p>
			<p>
				<strong>Industry:</strong> Software Solutions & Inventory Automation
			</p>
			<p>
				<strong>Overview:</strong> InventorySystem is an Indian technology
				company committed to solving the challenges faced by businesses in
				managing stock, billing, and procurement efficiently. With our
				web-based platform, we aim to bring transparency and speed to every
				business transaction.
			</p>
			<p>
				<strong>Our Services:</strong>
			</p>
			<ul>
				<li>Inventory Management System</li>
				<li>GST/Tax-enabled Billing</li>
				<li>Purchase & Stock Tracking</li>
				<li>Multi-user Access with Roles (Admin/Staff)</li>
				<li>Real-time Reports & Analytics</li>
			</ul>
			<p>Our commitment is to deliver quality software solutions that
				are reliable, customizable, and easy to use.</p>
		</div>
	</section>





	<!-- Login Section -->
	<section id="login" class="bg-light">
		<div class="container">
			<h2>Login</h2>
			<form action="LoginServlet" method="post" class="w-50 mx-auto">
				<div class="mb-3">
					<label for="username" class="form-label">Username:</label> <input
						type="text" name="username" class="form-control" required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password:</label> <input
						type="password" name="password" class="form-control" required>
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
			</form>
		</div>
	</section>
	<!-- Contact Section -->
	<section id="contact" class="bg-light">
		<div class="container">
			<h2>Contact Us</h2>
			<p>If you have any questions, need support, or want a demo, feel
				free to reach out to us.</p>
			<ul class="list-unstyled">
				<li><strong>Email:</strong> support@inventorysystem.com</li>
				<li><strong>Phone:</strong> +91 98765 43210</li>
				<li><strong>Address:</strong> InventorySystem Pvt. Ltd., 3rd
					Floor, Tech Tower, Sector 62, Noida, Uttar Pradesh, India</li>
				<li><strong>Working Hours:</strong> Monday - Friday, 10:00 AM -
					6:00 PM</li>
			</ul>
		</div>
	</section>
	<!-- Footer -->
	<footer class="bg-dark text-white text-center py-3">
		<p>&copy; 2025 InventorySystem. All rights reserved.</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
