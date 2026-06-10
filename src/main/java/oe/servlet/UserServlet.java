package oe.servlet;

import java.io.IOException;
import java.util.Map;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.entity.User;
import oe.helper.XHttp;
import oe.service.UserService;
import oe.service.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet({
	"/user/list", 
	"/user/edit/*", 
	"/user/create", 
	"/user/update", 
	"/user/delete/*"
})
public class UserServlet extends HttpServlet {
	UserService userService = new UserServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		var user = new User();
		
		if(path.contains("list")) {
		} else if(path.contains("edit")) {
			var email = req.getPathInfo().substring(1);
			user = userService.findById(email);
		} else if(path.contains("create")) {
			user = XHttp.getBean(User.class);
			userService.create(user);
		} else if(path.contains("update")) {
			user = XHttp.getBean(User.class);
			userService.create(user);
		} else if(path.contains("delete")) {
			var email = req.getPathInfo().substring(1);
			userService.deleteByEmail(email);
		}
		
		var attrs = Map.of("view", "/user/index.jsp", "form", user, "list", userService.findAll());
		XHttp.forward("/layout/index.jsp", attrs);
	}
}