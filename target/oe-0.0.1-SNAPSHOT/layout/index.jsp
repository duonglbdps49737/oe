<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Online Entertainment</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
		  rel="stylesheet">

	<style>
		body{
			background:#f5f5f5;
		}

		header{
			background:#198754;
			color:white;
			padding:15px;
			text-align:center;
		}

		footer{
			background:#212529;
			color:white;
			text-align:center;
			padding:10px;
			margin-top:20px;
		}

		main{
			width:95%;
			margin:auto;
			padding:15px;
		}
	</style>
</head>
<body>
	<header>
		<h1>Online Entertainment</h1>
	</header>
	<jsp:include page="/layout/menu.jsp"/>
	<main>
		<article>
			<jsp:useBean id="view" scope="request" type="String"/>
			<jsp:include page="${view}"/>
		</article>
		<aside></aside>
	</main>
	<footer>
		<p>&copy; 2026 by FPT Polytechnic. All rights reserved.</p>
	</footer>
</body>
</html>