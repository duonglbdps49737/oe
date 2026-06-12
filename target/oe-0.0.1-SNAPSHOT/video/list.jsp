<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<article>
	<h3>Video List</h3>
	<div class="row">
		<jsp:useBean id="videos" scope="request" type="java.util.List"/>
		<c:forEach items="${videos}" var="v">
			<div class="col-4">
				<div class="card mb-3">
					<div class="ratio ratio-16x9">
						<img src="${v.poster}">
					</div>
					<div class="card-footer">
						<div>${v.title}</div>
						<div>
							<a href="/oe/video/detail/${v.id}">Chi tiết...</a>
						</div>
					</div>
				</div>			
			</div>
		</c:forEach>
	</div>
	
</article>