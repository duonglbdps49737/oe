<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .detail-layout { display: flex; gap: 24px; }
  .detail-main { flex: 1; min-width: 0; }
  .detail-sidebar { width: 240px; flex-shrink: 0; }

  /* Video embed */
  .video-wrap {
    width: 100%; aspect-ratio: 16/9;
    background: #000; margin-bottom: 12px; overflow: hidden;
  }
  .video-wrap iframe { width: 100%; height: 100%; border: none; }
  .video-wrap .no-video {
    width: 100%; height: 100%; display: flex;
    align-items: center; justify-content: center; color: #fff; font-size: 14px;
  }

  /* Info */
  .video-title-bar {
    background: #d4edda; padding: 8px 12px;
    font-weight: bold; font-size: 15px; text-transform: uppercase;
    margin-bottom: 6px;
  }
  .video-desc { padding: 8px 0 12px; font-size: 14px; color: #444; line-height: 1.6; }
  .btn-like  { background: #4caf50; color: #fff; border: none; padding: 7px 20px; border-radius: 4px; cursor: pointer; font-size: 14px; margin-right: 8px; }
  .btn-share { background: #f44336; color: #fff; border: none; padding: 7px 20px; border-radius: 4px; cursor: pointer; font-size: 14px; }

  /* Sidebar — đã xem */
  .sidebar-title { font-weight: bold; font-size: 13px; margin-bottom: 10px; color: #555; border-bottom: 2px solid #e87722; padding-bottom: 4px; }
  .viewed-card { display: flex; gap: 8px; margin-bottom: 10px; align-items: center; text-decoration: none; color: #333; }
  .viewed-card:hover .viewed-title { text-decoration: underline; }
  .viewed-thumb {
    width: 72px; height: 48px; background: #eee; flex-shrink: 0;
    overflow: hidden; border: 1px solid #ddd;
    display: flex; align-items: center; justify-content: center;
    font-size: 10px; color: #999;
  }
  .viewed-thumb img { width: 100%; height: 100%; object-fit: cover; }
  .viewed-title { font-size: 12px; line-height: 1.4; }

  .btn-back { display: inline-block; margin-top: 14px; font-size: 13px; color: #555; text-decoration: none; }
  .btn-back:hover { text-decoration: underline; }
</style>

<div class="detail-layout">
  <%-- Cột chính --%>
  <div class="detail-main">
    <div class="video-wrap">
      <c:choose>
        <c:when test="${not empty video.youtubeId}">
          <iframe src="https://www.youtube.com/embed/${video.youtubeId}"
                  allowfullscreen allow="autoplay; encrypted-media"></iframe>
        </c:when>
        <c:when test="${not empty video.poster}">
          <img src="${video.poster}" style="width:100%;height:100%;object-fit:cover"/>
        </c:when>
        <c:otherwise>
          <div class="no-video">VIDEO</div>
        </c:otherwise>
      </c:choose>
    </div>

    <div class="video-title-bar">${video.title}</div>
    <div class="video-desc">${video.description}</div>

    <a href="${pageContext.request.contextPath}/video/like/${video.id}?ref=detail">
      <button class="btn-like">Like</button>
    </a>
    <a href="${pageContext.request.contextPath}/video/share/${video.id}">
      <button class="btn-share">Share</button>
    </a>

    <br/>
    <a class="btn-back" href="${pageContext.request.contextPath}/home/index">← Trở về trang chủ</a>
  </div>

  <%-- Sidebar: các tiểu phẩm đã xem --%>
  <div class="detail-sidebar">
    <div class="sidebar-title">Đã xem gần đây</div>
    <c:forEach var="v" items="${viewedVideos}">
      <a class="viewed-card"
         href="${pageContext.request.contextPath}/video/detail/${v.id}">
        <div class="viewed-thumb">
          <c:choose>
            <c:when test="${not empty v.poster}">
              <img src="${v.poster}" alt="${v.title}"/>
            </c:when>
            <c:otherwise>POSTER</c:otherwise>
          </c:choose>
        </div>
        <div class="viewed-title">${v.title}</div>
      </a>
    </c:forEach>
    <c:if test="${empty viewedVideos}">
      <p style="font-size:12px;color:#999">Chưa có tiểu phẩm nào.</p>
    </c:if>
  </div>
</div>
