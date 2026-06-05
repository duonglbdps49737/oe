<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .fav-title { font-size: 20px; font-weight: bold; margin-bottom: 16px; color: #333; }
  .video-grid {
    display: grid; grid-template-columns: repeat(3, 1fr);
    gap: 16px; margin-bottom: 16px;
  }
  .video-card { border: 2px solid #e87722; border-radius: 6px; overflow: hidden; background: #fff; }
  .video-card a.poster-link {
    display: flex; align-items: center; justify-content: center;
    width: 100%; aspect-ratio: 4/3; background: #f0f0f0;
    overflow: hidden; text-decoration: none; color: #999; font-size: 13px;
  }
  .video-card a.poster-link img { width: 100%; height: 100%; object-fit: cover; }
  .card-info  { background: #d4edda; padding: 8px 10px 4px; }
  .card-title { font-weight: bold; font-size: 13px; text-transform: uppercase; color: #333; margin-bottom: 6px; }
  .card-actions { background: #d4edda; padding: 4px 10px 10px; display: flex; gap: 6px; }
  .btn-unlike { background: #2196f3; color: #fff; border: none; padding: 5px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; }
  .btn-share  { background: #f44336; color: #fff; border: none; padding: 5px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; }
  .empty-msg  { color: #888; font-size: 14px; padding: 20px 0; }
</style>

<div class="fav-title">My Favorites</div>

<c:choose>
  <c:when test="${empty favorites}">
    <p class="empty-msg">Bạn chưa có tiểu phẩm yêu thích nào.</p>
  </c:when>
  <c:otherwise>
    <div class="video-grid">
      <c:forEach var="fav" items="${favorites}">
        <div class="video-card">
          <a class="poster-link"
             href="${pageContext.request.contextPath}/video/detail/${fav.video.id}">
            <c:choose>
              <c:when test="${not empty fav.video.poster}">
                <img src="${fav.video.poster}" alt="${fav.video.title}"/>
              </c:when>
              <c:otherwise>POSTER</c:otherwise>
            </c:choose>
          </a>
          <div class="card-info">
            <div class="card-title">${fav.video.title}</div>
          </div>
          <div class="card-actions">
            <%-- Unlike: gỡ khỏi danh sách yêu thích --%>
            <a href="${pageContext.request.contextPath}/video/unlike/${fav.id}">
              <button class="btn-unlike">Unlike</button>
            </a>
            <a href="${pageContext.request.contextPath}/video/share/${fav.video.id}">
              <button class="btn-share">Share</button>
            </a>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:otherwise>
</c:choose>
