<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .auth-box { max-width: 400px; margin: 0 auto; background: #fff; border: 1px solid #ddd; border-radius: 8px; padding: 28px 32px; }
  .auth-box h2 { margin-bottom: 20px; font-size: 20px; color: #333; }
  .form-group  { margin-bottom: 14px; }
  .form-group label { display: block; font-size: 14px; color: #555; margin-bottom: 4px; }
  .form-group input { width: 100%; padding: 8px 10px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
  .btn-submit  { width: 100%; background: #f5a623; color: #fff; border: none; padding: 10px; border-radius: 4px; font-size: 15px; cursor: pointer; font-weight: bold; }
  .btn-submit:hover { background: #e8810a; }
  .error-msg   { background: #ffebee; color: #c62828; padding: 8px 12px; border-radius: 4px; font-size: 14px; margin-bottom: 14px; }
  .success-msg { background: #e8f5e9; color: #2e7d32; padding: 8px 12px; border-radius: 4px; font-size: 14px; margin-bottom: 14px; }
</style>
<div class="auth-box">
  <h2>Change Password</h2>
  <c:if test="${not empty error}">   <div class="error-msg">${error}</div>   </c:if>
  <c:if test="${not empty success}"> <div class="success-msg">${success}</div> </c:if>
  <form method="post" action="${pageContext.request.contextPath}/auth/change-password">
    <div class="form-group">
      <label>Mật khẩu cũ</label>
      <input type="password" name="oldPassword" required/>
    </div>
    <div class="form-group">
      <label>Mật khẩu mới</label>
      <input type="password" name="newPassword" required/>
    </div>
    <div class="form-group">
      <label>Xác nhận mật khẩu mới</label>
      <input type="password" name="confirm" required/>
    </div>
    <button type="submit" class="btn-submit">Đổi mật khẩu</button>
  </form>
</div>
