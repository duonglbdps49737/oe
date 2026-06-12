<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<nav>
	<hr>
		<a href="${pageContext.request.contextPath}/oe/home/index">Home</a> |
		<a href="${pageContext.request.contextPath}/oe/home/about">About Us</a> |
		<a href="${pageContext.request.contextPath}/oe/home/contact">Contact Us</a>
		<c:if test="${!empty sessionScope.user}">
			| <a href="${pageContext.request.contextPath}/oe/auth/logout">Logout</a>
			<c:if test="${sessionScope.user.admin}">
				| <a href="${pageContext.request.contextPath}/oe/admin/user/index">Administration</a>
			</c:if>
		</c:if>
	<hr>
</nav>