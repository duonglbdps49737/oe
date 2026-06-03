<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .video-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 16px;
    margin-bottom: 16px;
  }
  .video-card {
    border: 2px solid #e87722;
    border-radius: 6px;
    overflow: hidden;
    background: #fff;
  }
  .video-card a.poster-link {
    display: block;
    width: 100%;
    aspect-ratio: 4/3;
    background: #f0f0f0;
    overflow: hidden;
    text-decoration: none;
    color: #999;
    font-size: 13px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .video-card a.poster-link img {
    width: 100%; height: 100%; object-fit: cover;
  }
  .video-card .card-info {
    background: #d4edda;
    padding: 8px 10px 4px;
  }
  .video-card .card-title {
    font-weight: bold;
    font-size: 13px;
    text-transform: uppercase;
    color: #333;
    margin-bottom: 6px;
  }
  .video-card .card-actions {
    background: #d4edda;
    padding: 4px 10px 10px;
    display: flex;
    gap: 6px;
  }
  .btn-like  { background: #4caf50; color: #fff; border: none; padding: 5px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; }
  .btn-share { background: #f44336; color: #fff; border: none; padding: 5px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; }
  .pagination { text-align: center; margin: 8px 0 20px; }
  .pagination a, .pagination span {
    display: inline-block; padding: 5px 12px; margin: 0 2px;
    border: 1px solid #aaa; border-radius: 4px;
    background: #e0e0e0; color: #333;
    text-decoration: none; font-size: 13px;
  }
  .pagination a:hover { background: #ccc; }
  .pagination span.disabled { color: #bbb; }
</style>

<div class="video-grid">
  <c:forEach var="v" items="${videos}">
    <div class="video-card">
      <%-- Poster click → trang chi tiết + tăng views --%>
      <a class="poster-link"
         href="${pageContext.request.contextPath}/video/detail/${v.id}">
        <c:choose>
          <c:when test="${not empty v.poster}">
            <img src="${v.poster}" alt="${v.title}"/>
          </c:when>
          <c:otherwise>POSTER</c:otherwise>
        </c:choose>
      </a>

        <div class="card-info">
          <div class="card-title">${v.title}</div>
          <small>Lượt xem: ${v.views}</small>
        </div>

      <div class="card-actions">
        <%-- Like: yêu cầu đăng nhập --%>
        <a href="${pageContext.request.contextPath}/video/like/${v.id}?ref=home">
          <button class="btn-like">Like</button>
        </a>
        <%-- Share: yêu cầu đăng nhập --%>
        <a href="${pageContext.request.contextPath}/video/share/${v.id}">
          <button class="btn-share">Share</button>
        </a>
      </div>
    </div>
  </c:forEach>
</div>

<%-- Phân trang --%>
<div class="pagination">
  <c:choose>
    <c:when test="${page > 1}">
      <a href="${pageContext.request.contextPath}/home/index?page=1">|&lt;</a>
      <a href="${pageContext.request.contextPath}/home/index?page=${page - 1}">&lt;&lt;</a>
    </c:when>
    <c:otherwise>
      <span class="disabled">|&lt;</span>
      <span class="disabled">&lt;&lt;</span>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${page < totalPages}">
      <a href="${pageContext.request.contextPath}/home/index?page=${page + 1}">&gt;&gt;</a>
      <a href="${pageContext.request.contextPath}/home/index?page=${totalPages}">&gt;|</a>
    </c:when>
    <c:otherwise>
      <span class="disabled">&gt;&gt;</span>
      <span class="disabled">&gt;|</span>
    </c:otherwise>
  </c:choose>
</div>
