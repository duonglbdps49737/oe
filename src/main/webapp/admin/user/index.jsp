<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<article>
	<c:set value="/oe/admin/user" var="path"/>
	<h2>User Management</h2>
	<i>${msg}</i>
	<form method="post">
	    <div>
	        <div>Email</div>
	        <input name="email" value="${form.email}">
	    </div>
	    <div>
	        <div>Password</div>
	        <input name="password" value="${form.password}">
	    </div>
	    <div>
	        <div>Fullname</div>
	        <input name="fullname" value="${form.fullname}">
	    </div>
	    <div>
	        <div>Role</div>
	        <input ${form.admin?'checked' : ''} type="radio" name="admin" value="true"> Admin
	        <input ${form.admin?'' : 'checked'} type="radio" name="admin" value="false"> User
	    </div>
	    <div>
	        <div>Status</div>
	        <input ${form.enabled?'checked' : ''} type="radio" name="enabled" value="true"> Active
	        <input ${form.enabled?'' : 'checked'} type="radio" name="enabled" value="false"> Inactive
	    </div>
	    <div>
	        <button formaction="${path}/create">Create</button>
	        <button formaction="${path}/update">Update</button>
	        <a href="${path}/delete/${form.email}">Delete</a>
	        <a href="${path}/list">Reset</a>
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
	            <td>${user.admin?'ADMIN':'USER'}</td>
	            <td>${user.enabled?'Activated':'Not Activated'}</td>
	            <td>
	                <a href="${path}/edit/${user.email}">Edit</a> |
	                <a href="${path}/delete/${user.email}">Delete</a>
	            </td>
	        </tr>
	    </c:forEach>
	    </tbody>
	</table>
</article>