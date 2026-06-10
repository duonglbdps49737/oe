package oe.servlet;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.dao.VideoDAO;
import oe.dao.VideoDAOImpl;

@SuppressWarnings("serial")
@WebServlet({
	"/video/list", 
	"/video/detail/*", 
	"/video/like/*",
	"/video/share/*"
})
public class VideoServlet extends HttpServlet {
	VideoDAO dao = new VideoDAOImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		if(path.contains("list")) {
			var list = dao.findAll();
			req.setAttribute("videos", list);
			req.setAttribute("view", "/video/list.jsp");
		} else {
			var id = req.getPathInfo().substring(1);
			var video = dao.findById(id);
			if(path.contains("detail")) {
				req.setAttribute("video", video);
				var list = dao.findAll();
				req.setAttribute("videos", list);
				req.setAttribute("view", "/video/detail.jsp");
			} else {
				if(path.contains("like")) {
					
				} else if(path.contains("share")) {
					
				}
				resp.sendRedirect("/oe/video/list");
				return;
			}
		}
		req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
	}
}
