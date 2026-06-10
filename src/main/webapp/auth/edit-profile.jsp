<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .auth-box { max-width: 440px; margin: 0 auto; background: #fff; border: 1px solid #ddd; border-radius: 8px; padding: 28px 32px; }
  .auth-box h2 { margin-bottom: 20px; font-size: 20px; color: #333; }
  .form-group  { margin-bottom: 14px; }
  .form-group label { display: block; font-size: 14px; color: #555; margin-bottom: 4px; }
  .form-group input { width: 100%; padding: 8px 10px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
  .form-group input[readonly] { background: #f5f5f5; color: #888; }
  .btn-submit  { width: 100%; background: #f5a623; color: #fff; border: none; padding: 10px; border-radius: 4px; font-size: 15px; cursor: pointer; font-weight: bold; }
  .btn-submit:hover { background: #e8810a; }
  .success-msg { background: #e8f5e9; color: #2e7d32; padding: 8px 12px; border-radius: 4px; font-size: 14px; margin-bottom: 14px; }
</style>
<div class="auth-box">
  <h2>Edit Profile</h2>
  <c:if test="${not empty success}"><div class="success-msg">${success}</div></c:if>
  <form method="post" action="${pageContext.request.contextPath}/auth/edit-profile">
    <div class="form-group">
      <label>Email</label>
      <input type="email" value="${sessionScope.user.email}" readonly/>
    </div>
    <div class="form-group">
      <label>Full Name</label>
      <input type="text" name="fullname" value="${sessionScope.user.fullname}" required/>
    </div>
    <button type="submit" class="btn-submit">Cập nhật</button>
  </form>
</div>
