package oe.admin.servlet;

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
	"/admin/user/index", 
	"/admin/user/edit/*", 
	"/admin/user/create", 
	"/admin/user/update", 
	"/admin/user/delete/*"
})
public class UserServlet extends HttpServlet {
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var user = new User();
		var msg = "";
		
		var path = req.getServletPath();
		if(path.contains("edit")) {
			var email = req.getPathInfo().substring(1);
			user = userService.findById(email);
		} else if(path.contains("create")) {
			user = XHttp.getBean(User.class);
			userService.create(user);
			msg = "Tạo mới thành công!";
		} else if(path.contains("update")) {
			user = XHttp.getBean(User.class);
			userService.update(user);
			msg = "Cập nhật thành công!";
		} else if(path.contains("delete")) {
			var email = req.getPathInfo().substring(1);
			userService.deleteByEmail(email);
			msg = "Xóa thành công";
		}

        assert user != null;
        var attrs = Map.of(
			"view", "/admin/user/index.jsp", 
			"form", user, 
			"list", userService.findAll(),
			"msg", msg
		);
		XHttp.forward("/admin/shared/layout.jsp", attrs);
	}
}