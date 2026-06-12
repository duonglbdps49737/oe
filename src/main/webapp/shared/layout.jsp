<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Online Entertainment</title>
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container">
	<header>
		<h1>Online Entertainment</h1>
	</header>
	<jsp:include page="menu.jsp"/>
	<main>
		<article>
			<jsp:include page="${view}"/>
		</article>
		<aside></aside>
	</main>
	<footer>
		<p>&copy; 2026 by FPT Polytechnic. All rights reserved.</p>
	</footer>
</body>
</html>