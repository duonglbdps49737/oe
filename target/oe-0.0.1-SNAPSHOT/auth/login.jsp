<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<article>
	<h2>Login
		<c:if test="${!empty user}">
			<a href="${pageContext.request.contextPath}/oe/auth/logout">Sign Out</a>
		</c:if>
	</h2>
	<i>${msg}${param.msg}</i>
	<form action="${pageContext.request.contextPath}/oe/auth/login" method="post">
	    <div>
	        <div>Username</div>
	        <input name="email">
	    </div>
	    <div>
	        <div>Password</div>
	        <input name="password">
	    </div>
	    <div>
	    	<label>
	        	<input name="remember" type="checkbox">
	        	Remember me?
	        </label>
	    </div>
	    <div>
	        <hr>
	        <button>Login</button>
	    </div>
	</form>
</article>