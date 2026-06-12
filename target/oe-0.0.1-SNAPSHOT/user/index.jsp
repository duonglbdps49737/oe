<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<article>
	<h2>User CRUD</h2>
	<form method="post">
	    <div>
	        <div>Email</div>
			<label>
				<input name="email" value="${form.email}">
			</label>
		</div>
	    <div>
	        <div>Password</div>
			<label>
				<input name="password" value="${form.password}">
			</label>
		</div>
	    <div>
	        <div>Fullname</div>
			<label>
				<input name="fullname" value="${form.fullname}">
			</label>
		</div>
	    <div>
	        <div>Role</div>
			<label>
				<input ${form.admin?'checked' : ''} type="radio" name="admin" value="true">
			</label> Admin
			<label>
				<input ${form.admin?'' : 'checked'} type="radio" name="admin" value="false">
			</label> User
	    </div>
	    <div>
	        <div>Status</div>
			<label>
				<input ${form.enabled?'checked' : ''} type="radio" name="enabled" value="true">
			</label> Active
			<label>
				<input ${form.enabled?'' : 'checked'} type="radio" name="enabled" value="false">
			</label> Inactive
	    </div>
	    <div>
	        <button formaction="/oe/user/create">Create</button>
	        <button formaction="/oe/user/update">Update</button>
	        <a href="/oe/user/delete/${form.email}">Delete</a>
	        <a href="${pageContext.request.contextPath}/oe/user/list">Reset</a>
	    </div>
	</form>
	<hr>
	<table border="1" style="width:100%">
	    <thead>
	        <tr>
	            <th>No.</th>
	            <th>Email</th>
	            <th>Password</th>
	            <th>Fullname</th>
	            <th>Role</th>
	            <th>Status</th>
	            <th></th>
	        </tr>
	    </thead>
	    <tbody>
	    <c:forEach items="${list}" var="user" varStatus="st">
	        <tr>
	            <td>${st.index+1}</td>
	            <td>${user.email}</td>
	            <td>${user.password}</td>
	            <td>${user.fullname}</td>
	            <td>${user.admin}</td>
	            <td>${user.enabled}</td>
	            <td>
	                <a href="/oe/user/edit/${user.email}">Edit</a> |
	                <a href="/oe/user/delete/${user.email}">Delete</a>
	            </td>
	        </tr>
	    </c:forEach>
	    </tbody>
	</table>
</article>