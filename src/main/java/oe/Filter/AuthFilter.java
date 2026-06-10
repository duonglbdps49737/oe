package oe.Filter;

import java.io.IOException;
import java.util.Map;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.Filter.HttpFilter;
import oe.entity.User;
import oe.helper.XHttp;

@WebFilter({
	"/video/like/*", 
	"/video/share/*",
	"/admin/*"
})
public class AuthFilter implements HttpFilter {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var path = req.getServletPath();
		User user = XHttp.getSession("user");
		if(user == null) {
			XHttp.redirect("/auth/login", Map.of("msg", "Vui lòng đăng nhập!"));
		} else if(path.contains("/admin/") && !user.isAdmin()){
			XHttp.redirect("/auth/login", Map.of("msg", "Không có quyền truy cập!"));
		} else {
			return true;
		}
		XHttp.setSession("security-path", path);
		return false;
	}
}