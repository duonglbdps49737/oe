package oe.servlet;

import java.io.IOException;
import java.util.Map;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.helper.XHttp;
import oe.service.UserService;
import oe.service.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet({"/auth/login", "/auth/logout"})
public class AuthServlet extends HttpServlet {
	UserService userService = new UserServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		if(path.contains("logout")) {
			XHttp.removeSession("user");
			XHttp.removeCookie("user");
			XHttp.redirect("/auth/login", Map.of("msg", "Đã đăng xuất!"));
		} else {
			if(req.getMethod().equalsIgnoreCase("get")) {
				XHttp.forward("/auth/login.jsp");
			} else {
				var username = XHttp.getParam("email");
				var password = XHttp.getParam("password");
				var remember = XHttp.getParam("remember");
				
				var user = userService.findById(username);
				var msg = "";
				if(user == null) {
					msg = "Sai địa chỉ email!";
				} else if(!user.getPassword().equals(password)) {
					msg = "Sai mật khẩu!";
				} else if(!user.isEnabled()) {
					msg = "Tài khoản chưa kích hoạt!";
				} else {
					msg = "Đăng nhập thành công!";
					
					XHttp.setSession("user", user);
					if(remember != null) {
						XHttp.addCookie("user", user.getEmail(), 30*24*60*60);
					} else {
						XHttp.removeCookie("user");
					}
					String authPath = XHttp.getSession("security-path");
					if(authPath != null) {
						XHttp.redirect(authPath);
						return;
					}
				}
				XHttp.forward("/auth/login.jsp", Map.of("msg", msg));
			}
		}
	}
}