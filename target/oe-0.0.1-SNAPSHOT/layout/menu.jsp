<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<nav>
	<hr>
		<a href="/oe/home/index">Home</a> |
		<a href="/oe/home/about">About Us</a> |
		<a href="/oe/home/contact">Contact Us</a>
	<hr>
	<a href="/oe/user/list">User CRUD</a>
	<c:if test="${! empty sessionScope.user}">
		| <a href="/oe/auth/logout">Logout</a>
	</c:if>
	<hr>
</nav>