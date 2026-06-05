<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Online Entertainment</title>
  <style>
    * { box-sizing: border-box; margin: 0; padding: 0; }
    body { font-family: Arial, sans-serif; background: #f5f5f5; }
    header {
      background: linear-gradient(to right, #f5a623, #e8810a);
      padding: 0 24px; display: flex; align-items: center; height: 52px;
    }
    header a.brand {
      color: #fff; font-size: 20px; font-weight: bold;
      text-decoration: none; text-transform: uppercase;
      letter-spacing: 1px; margin-right: 32px;
    }
    nav { display: flex; align-items: center; gap: 4px; }
    nav a.nav-link {
      color: #fff; text-decoration: none; padding: 6px 14px;
      font-size: 14px; font-weight: bold; text-transform: uppercase;
      border-radius: 4px 4px 0 0;
    }
    nav a.nav-link:hover { background: rgba(255,255,255,0.2); }
    .dropdown { position: relative; }
    .dropdown-menu {
      display: none; position: absolute; top: 100%; left: 0;
      background: #fff; border: 1px solid #ddd; min-width: 180px;
      z-index: 100; box-shadow: 0 4px 8px rgba(0,0,0,0.12);
    }
    .dropdown:hover .dropdown-menu { display: block; }
    .dropdown-menu a {
      display: block; padding: 9px 16px; color: #333;
      text-decoration: none; font-size: 14px;
    }
    .dropdown-menu a:hover { background: #f0f0f0; }
    .dropdown-menu .divider { border-top: 1px solid #eee; }
    .user-name { padding: 8px 16px; display: block; font-size: 13px; color: #999; }
    main { max-width: 960px; margin: 24px auto; padding: 0 16px; }
    footer { text-align: center; padding: 16px; font-size: 13px; color: #888; border-top: 1px solid #ddd; margin-top: 40px; }
  </style>
</head>
<body>
<header>
  <a class="brand" href="${pageContext.request.contextPath}/home/index">Online Entertainment</a>
  <nav>
    <a class="nav-link" href="${pageContext.request.contextPath}/video/favorites">My Favorites</a>
    <div class="dropdown">
      <a class="nav-link" href="#">My Account ▾</a>
      <div class="dropdown-menu">
        <c:choose>
          <c:when test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/auth/login">Login</a>
            <a href="${pageContext.request.contextPath}/auth/register">Registration</a>
            <a href="${pageContext.request.contextPath}/auth/forgot-password">Forgot Password</a>
          </c:when>
          <c:otherwise>
            <span class="user-name">${sessionScope.user.fullname}</span>
            <div class="divider"></div>
            <a href="${pageContext.request.contextPath}/auth/change-password">Change Password</a>
            <a href="${pageContext.request.contextPath}/auth/edit-profile">Edit Profile</a>
            <div class="divider"></div>
            <a href="${pageContext.request.contextPath}/auth/logoff">Logoff</a>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </nav>
</header>
<main>
  <jsp:useBean id="view" scope="request" type="java.lang.String"/>
  <jsp:include page="${view}"/>
</main>
<footer><p>&copy; 2026 by FPT Polytechnic. All rights reserved.</p></footer>
</body>
</html>
