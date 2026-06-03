<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .share-wrap { max-width: 480px; }
  .share-wrap h3 { margin-bottom: 14px; color: #333; }
  .share-wrap label { display: block; font-size: 14px; color: #555; margin-bottom: 4px; }
  .share-wrap input[type=text] {
    width: 100%; padding: 8px 10px; border: 1px solid #ccc;
    border-radius: 4px; font-size: 14px;
    margin-bottom: 12px; box-sizing: border-box;
  }
  .btn-send { background: #4caf50; color: #fff; border: none; padding: 8px 22px; border-radius: 4px; cursor: pointer; font-size: 14px; }
  .msg-success { background: #e8f5e9; color: #2e7d32; padding: 10px 14px; border-radius: 4px; margin-bottom: 12px; }
  .share-link { word-break: break-all; color: #1565c0; }
  .btn-back { display: inline-block; margin-top: 16px; font-size: 13px; color: #555; text-decoration: none; }
  .btn-back:hover { text-decoration: underline; }
</style>

<div class="share-wrap">
  <h3>Chia sẻ: ${video.title}</h3>

  <c:if test="${not empty message}">
    <div class="msg-success">${message}</div>
    <p>Link chia sẻ:<br/>
      <a class="share-link" href="${link}">${link}</a>
    </p>
  </c:if>

  <c:if test="${empty message}">
    <form method="post"
          action="${pageContext.request.contextPath}/video/share/${video.id}">
      <label>Email người nhận (nhiều email cách nhau bằng dấu phẩy):</label>
      <input type="text" name="recipients"
             placeholder="vd: ban@gmail.com, nguoithan@gmail.com" required/>
      <button type="submit" class="btn-send">Gửi</button>
    </form>
  </c:if>

  <a class="btn-back" href="${pageContext.request.contextPath}/home/index">← Trở về trang chủ</a>
</div>
