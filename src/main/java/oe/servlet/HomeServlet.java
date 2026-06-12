package oe.servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.helper.XHttp;

@SuppressWarnings("serial")
@WebServlet({
	"/home/index", 
	"/home/about", 
	"/home/contact"
})
public class HomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		var view = "";
		if(path.contains("index")) {
			view = "/home/index.jsp";
		} else if(path.contains("about")) {
			view = "/home/about.jsp";
		} else if(path.contains("contact")) {
			view = "/home/contact.jsp";
		}
		XHttp.forward("/shared/layout.jsp", Map.of("view", view));
	}
}
