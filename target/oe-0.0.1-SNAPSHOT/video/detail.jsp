<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<article>
	<h3>Video Detail</h3>
	<div class="row">
		<div class="col-8">
			<div class="ratio ratio-16x9">
				<iframe src="https://www.youtube.com/embed/${video.id}"></iframe>
			</div>
			<ul>
				<li>Id: ${video.id}</li>
				<li>Title: ${video.title}</li>
				<li>View Count: ${video.views}</li>
				<li>Description: ${video.description}</li>
			</ul>
			<h4>Các lượt share</h4>
			<ul>
			<c:forEach items="${video.shares}" var="s">
				<li>${s.user.email} => ${s.recipients}</li>
			</c:forEach>
			</ul>
		</div>
		<div class="col-4">
			<c:forEach items="${videos}" var="v">
			<div class="card mb-3">
			  <div class="card-body">
			  	<div class="row">
					<div class="col-3">
						<a href="/oe/video/detail/${v.id}" class="ratio ratio-16x9">
							<img src="${v.poster}">
						</a>
					</div>
					<div class="col-9">
						${v.title}
					</div>
				</div>
			  </div>
			</div>
				
			</c:forEach>
		</div>
	</div>
	
</article>