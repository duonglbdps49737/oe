<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
  .auth-box { max-width: 440px; margin: 0 auto; background: #fff; border: 1px solid #ddd; border-radius: 8px; padding: 28px 32px; }
  .auth-box h2 { margin-bottom: 20px; font-size: 20px; color: #333; }
  .form-group { margin-bottom: 14px; }
  .form-group label { display: block; font-size: 14px; color: #555; margin-bottom: 4px; }
  .form-group input { width: 100%; padding: 8px 10px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
  .btn-submit { width: 100%; background: #f5a623; color: #fff; border: none; padding: 10px; border-radius: 4px; font-size: 15px; cursor: pointer; font-weight: bold; margin-top: 6px; }
  .btn-submit:hover { background: #e8810a; }
  .auth-links { margin-top: 14px; font-size: 13px; text-align: center; }
  .auth-links a { color: #1565c0; text-decoration: none; }
  .error-msg   { background: #ffebee; color: #c62828; padding: 8px 12px; border-radius: 4px; font-size: 14px; margin-bottom: 14px; }
  .success-msg { background: #e8f5e9; color: #2e7d32; padding: 8px 12px; border-radius: 4px; font-size: 14px; margin-bottom: 14px; }
</style>

<div class="auth-box">
  <h2>Registration</h2>

  <c:if test="${not empty error}">   <div class="error-msg">${error}</div>   </c:if>
  <c:if test="${not empty success}"> <div class="success-msg">${success}</div> </c:if>

  <c:if test="${empty success}">
    <form method="post" action="${pageContext.request.contextPath}/auth/register">
      <div class="form-group">
        <label>Full Name</label>
        <input type="text" name="fullname" value="${param.fullname}" placeholder="Nguyễn Văn A" required/>
      </div>
      <div class="form-group">
        <label>Email</label>
        <input type="email" name="email" value="${param.email}" placeholder="your@email.com" required/>
      </div>
      <div class="form-group">
        <label>Password</label>
        <input type="password" name="password" placeholder="••••••••" required/>
      </div>
      <div class="form-group">
        <label>Confirm Password</label>
        <input type="password" name="confirm" placeholder="••••••••" required/>
      </div>
      <button type="submit" class="btn-submit">Register</button>
    </form>
  </c:if>

  <div class="auth-links">
    Đã có tài khoản? <a href="${pageContext.request.contextPath}/auth/login">Login</a>
  </div>
</div>
