package oe.Filter;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.Filter.HttpFilter;
import oe.helper.XHttp;
import oe.service.UserService;
import oe.service.UserServiceImpl;

@WebFilter("/*")
public class AppFilter implements HttpFilter {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var user = XHttp.getSession("user");
		if(user == null) {
			var cookie = XHttp.getCookie("user");
			if(cookie != null) {
				UserService userService = new UserServiceImpl();
				user = userService.findById(cookie.getValue());
				XHttp.setSession("user", user);
			}
		}
		return true;
	}
}