<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-4">
    <h3 class="mb-4 text-uppercase border-bottom pb-2">My Favorites</h3>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="vid" items="${favoriteList}">
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <a href="${pageContext.request.contextPath}/video/detail?id=${vid.id}">
                        <img src="https://img.youtube.com/vi/${vid.id}/maxresdefault.jpg" class="card-img-top" alt="${vid.title}">
                    </a>
                    <div class="card-body text-center bg-light">
                        <h5 class="card-title text-truncate" title="${vid.title}">${vid.title}</h5>
                    </div>
                    <div class="card-footer bg-white d-flex justify-content-center border-top-0 pt-0">
                        <a href="${pageContext.request.contextPath}/unlike?id=${vid.id}" class="btn btn-danger me-2">Unlike</a>
                        <a href="${pageContext.request.contextPath}/share?id=${vid.id}" class="btn btn-warning">Share</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty favoriteList}">
        <div class="alert alert-secondary text-center mt-4">
            Bạn chưa có tiểu phẩm yêu thích nào. Hãy về <a href="${pageContext.request.contextPath}/home">Trang chủ</a> để khám phá thêm!
        </div>
    </c:if>
</div>