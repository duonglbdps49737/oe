package oe.servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.entity.Favorite;
import oe.entity.User;
import oe.entity.Video;
import oe.helper.XHttp;
import oe.service.FavoriteService;
import oe.service.FavoriteServiceImpl;
import oe.service.VideoService;
import oe.service.VideoServiceImpl;

@SuppressWarnings("serial")
@WebServlet({
		"/video/index",
		"/video/detail/*",
		"/video/like/*",
		"/video/share/*",
		"/video/list"
})
public class VideoServlet extends HttpServlet {
	VideoService dao = new VideoServiceImpl();
	FavoriteService favoriteService = new FavoriteServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var path = req.getServletPath();
		var info = req.getPathInfo();
		if(path.contains("detail")) {
			try {
				var video = dao.findById(info.substring(1));

				var attrs = Map.of("view", "/video/detail.jsp",
						"video", video, "videos", dao.findAll());
				XHttp.forward("/shared/layout.jsp", attrs);
			} catch (Exception e) {
				// Nếu không tìm thấy video, redirect về trang list
				XHttp.redirect("/oe/video/index");
			}
		} else {
			if(path.contains("like")) {
				var video = Video.builder().id(info.substring(1)).build();
				User user = XHttp.getSession("user");
				var favorite = Favorite.builder().user(user).video(video).build();
				favoriteService.create(favorite);
				XHttp.redirect("/oe/video/index");
			} else if(path.contains("share")) {
				XHttp.redirect("/oe/video/index");
			}

			var attrs = Map.of("view", "/video/list.jsp",
					"videos", dao.findAll());
			XHttp.forward("/shared/layout.jsp", attrs);
		}
	}
}
