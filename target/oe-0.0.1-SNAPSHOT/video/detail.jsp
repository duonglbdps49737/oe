<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .detail-wrap { max-width: 680px; }
  .detail-wrap h2 { margin-bottom: 10px; color: #333; }
  .detail-poster {
    width: 100%; aspect-ratio: 16/9;
    background: #111; overflow: hidden;
    display: flex; align-items: center; justify-content: center;
    margin-bottom: 12px;
  }
  .detail-poster img { width: 100%; height: 100%; object-fit: cover; }
  .detail-meta { color: #777; font-size: 13px; margin-bottom: 8px; }
  .detail-desc { line-height: 1.7; margin-bottom: 16px; color: #444; }
  .btn-like  { background: #4caf50; color: #fff; border: none; padding: 7px 20px; border-radius: 4px; cursor: pointer; font-size: 14px; margin-right: 8px; }
  .btn-share { background: #f44336; color: #fff; border: none; padding: 7px 20px; border-radius: 4px; cursor: pointer; font-size: 14px; }
  .btn-back  { display: inline-block; margin-top: 14px; font-size: 13px; color: #555; text-decoration: none; }
  .btn-back:hover { text-decoration: underline; }
</style>

<div class="detail-wrap">
  <h2>${video.title}</h2>

  <div class="detail-poster">
    <c:choose>
      <c:when test="${not empty video.poster}">
        <img src="${video.poster}" alt="${video.title}"/>
      </c:when>
      <c:otherwise><span style="color:#fff">POSTER</span></c:otherwise>
    </c:choose>
  </div>

  <div class="detail-meta">Lượt xem: ${video.views}</div>
  <div class="detail-desc">${video.description}</div>

  <a href="${pageContext.request.contextPath}/video/like/${video.id}?ref=detail">
    <button class="btn-like">Like</button>
  </a>
  <a href="${pageContext.request.contextPath}/video/share/${video.id}">
    <button class="btn-share">Share</button>
  </a>

  <br/>
  <a class="btn-back" href="${pageContext.request.contextPath}/home/index">← Trở về trang chủ</a>
</div>
