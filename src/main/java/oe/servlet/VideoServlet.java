package oe.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.dao.FavoriteDAOImpl;
import oe.dao.ShareDAOImpl;
import oe.dao.VideoDAOImpl;
import oe.entity.Favorite;
import oe.entity.Share;
import oe.entity.User;
import oe.entity.Video;

import java.io.IOException;
import java.util.Date;

@WebServlet({
	"/video/list",
	"/video/detail/*",
	"/video/like/*",
	"/video/share/*"
})
public class VideoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		var path = req.getServletPath();

		if (path.contains("list")) {
			// Redirect về trang chủ (list dùng HomeServlet)
			resp.sendRedirect(req.getContextPath() + "/home/index");

		} else if (path.contains("detail")) {
			handleDetail(req, resp);

		} else if (path.contains("like")) {
			handleLike(req, resp);

		} else if (path.contains("share")) {
			handleShare(req, resp);
		}
	}

	// ── Chi tiết + tăng views bằng cookie ──────────────────────────────────
	private void handleDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Lấy id từ path: /video/detail/{id}
		String id = req.getPathInfo() != null
				? req.getPathInfo().replaceFirst("/", "")
				: req.getParameter("id");

		var dao = new VideoDAOImpl();
		Video video = dao.findById(id);

		// Tăng views 1 lần / trình duyệt bằng cookie
		String cookieName = "viewed_" + id;
		boolean seen = false;
		if (req.getCookies() != null) {
			for (Cookie c : req.getCookies()) {
				if (c.getName().equals(cookieName)) { seen = true; break; }
			}
		}
		if (!seen) {
			dao.increaseViews(id);
			Cookie ck = new Cookie(cookieName, "1");
			ck.setMaxAge(60 * 60 * 24 * 30); // 30 ngày
			resp.addCookie(ck);
		}

		req.setAttribute("video", video);
		req.setAttribute("view", "/video/detail.jsp");
		req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
	}

	// ── Like (yêu cầu đăng nhập) ───────────────────────────────────────────
	private void handleLike(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/auth/login");
			return;
		}

		String videoId = req.getPathInfo() != null
				? req.getPathInfo().replaceFirst("/", "")
				: req.getParameter("id");

		var favDao  = new FavoriteDAOImpl();
		var videoDao = new VideoDAOImpl();

		// Chỉ lưu nếu chưa like
		if (favDao.findByUserAndVideo(user.getEmail(), videoId) == null) {
			favDao.create(Favorite.builder()
					.user(user)
					.video(videoDao.findById(videoId))
					.likeDate(new Date())
					.build());
		}

		// Trở về nguồn gọi
		String ref = req.getParameter("ref");
		if ("detail".equals(ref)) {
			resp.sendRedirect(req.getContextPath() + "/video/detail/" + videoId);
		} else {
			resp.sendRedirect(req.getContextPath() + "/home/index");
		}
	}

	// ── Share qua email (yêu cầu đăng nhập) ────────────────────────────────
	private void handleShare(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/auth/login");
			return;
		}

		String videoId = req.getPathInfo() != null
				? req.getPathInfo().replaceFirst("/", "")
				: req.getParameter("id");

		var videoDao = new VideoDAOImpl();
		Video video  = videoDao.findById(videoId);

		if ("GET".equalsIgnoreCase(req.getMethod())) {
			// Hiển thị form nhập email
			req.setAttribute("video", video);
			req.setAttribute("view", "/video/share.jsp");
			req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);

		} else {
			// POST: lưu share record
			String recipients = req.getParameter("recipients");

			new ShareDAOImpl().create(Share.builder()
					.user(user)
					.video(video)
					.recipients(recipients)
					.shareDate(new Date())
					.build());

			// Link chia sẻ gửi cho bạn bè
			String link = req.getScheme() + "://" + req.getServerName()
					+ ":" + req.getServerPort()
					+ req.getContextPath() + "/video/detail/" + videoId;

			// TODO: gửi email thực sự qua MailUtil.send(recipients, subject, link)

			req.setAttribute("video", video);
			req.setAttribute("message", "Đã chia sẻ tới: " + recipients);
			req.setAttribute("link", link);
			req.setAttribute("view", "/video/share.jsp");
			req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
		}
	}
}
