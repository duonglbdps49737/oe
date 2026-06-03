<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/home">ONLINE ENTERTAINMENT</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav me-auto">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/favorite">MY FAVORITES</a>
				</li>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" data-bs-toggle="dropdown">
						MY ACCOUNT
					</a>
					<ul class="dropdown-menu dropdown-menu-end">
						<c:choose>
							<c:when test="${not empty sessionScope.user}">
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/change-password">Change Password</a></li>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/edit-profile">Edit Profile</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/logoff">Logoff</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Login</a></li>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/forgot-password">Forgot Password</a></li>
								<li><a class="dropdown-item" href="${pageContext.request.contextPath}/registration">Registration</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>