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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet({
    "/video/list",
    "/video/detail/*",
    "/video/like/*",
    "/video/unlike/*",
    "/video/share/*",
    "/video/favorites"
})
public class VideoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var path = req.getServletPath();
        if      (path.contains("favorites")) handleFavorites(req, resp);
        else if (path.contains("unlike"))    handleUnlike(req, resp);
        else if (path.contains("like"))      handleLike(req, resp);
        else if (path.contains("share"))     handleShare(req, resp);
        else if (path.contains("detail"))    handleDetail(req, resp);
        else resp.sendRedirect(req.getContextPath() + "/home/index");
    }

    // ── Chi tiết + sidebar đã xem ────────────────────────────────────────
    private void handleDetail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getPathInfo() != null
                ? req.getPathInfo().replaceFirst("/", "") : req.getParameter("id");

        var dao   = new VideoDAOImpl();
        Video video = dao.findById(id);

        // Tăng views 1 lần / trình duyệt
        String cookieName = "viewed_" + id;
        boolean seen = false;
        if (req.getCookies() != null)
            for (Cookie c : req.getCookies())
                if (c.getName().equals(cookieName)) { seen = true; break; }

        if (!seen) {
            dao.increaseViews(id);
            Cookie ck = new Cookie(cookieName, "1");
            ck.setMaxAge(60 * 60 * 24 * 30);
            resp.addCookie(ck);
        }

        // Lấy danh sách đã xem từ cookie "history"
        List<Video> viewedVideos = new ArrayList<>();
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (c.getName().equals("history")) {
                    String[] ids = c.getValue().split(",");
                    for (String vid : ids) {
                        if (!vid.isBlank() && !vid.equals(id)) {
                            try { viewedVideos.add(dao.findById(vid)); } catch (Exception ignored) {}
                        }
                    }
                    break;
                }
            }
        }

        // Cập nhật cookie history (tối đa 5)
        String history = id;
        if (req.getCookies() != null)
            for (Cookie c : req.getCookies())
                if (c.getName().equals("history")) {
                    String existing = Arrays.stream(c.getValue().split(","))
                            .filter(v -> !v.equals(id)).limit(4)
                            .reduce("", (a, b) -> a.isEmpty() ? b : a + "," + b);
                    history = id + (existing.isEmpty() ? "" : "," + existing);
                    break;
                }
        Cookie hCookie = new Cookie("history", history);
        hCookie.setMaxAge(60 * 60 * 24 * 30);
        resp.addCookie(hCookie);

        req.setAttribute("video", video);
        req.setAttribute("viewedVideos", viewedVideos);
        req.setAttribute("view", "/video/detail.jsp");
        req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
    }

    // ── Like ────────────────────────────────────────────────────────────────
    private void handleLike(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/auth/login"); return; }

        String videoId = req.getPathInfo() != null
                ? req.getPathInfo().replaceFirst("/", "") : req.getParameter("id");

        var favDao   = new FavoriteDAOImpl();
        var videoDao = new VideoDAOImpl();

        if (favDao.findByUserAndVideo(user.getEmail(), videoId) == null) {
            favDao.create(Favorite.builder()
                    .user(user).video(videoDao.findById(videoId))
                    .likeDate(new Date()).build());
        }

        String ref = req.getParameter("ref");
        resp.sendRedirect("detail".equals(ref)
                ? req.getContextPath() + "/video/detail/" + videoId
                : req.getContextPath() + "/home/index");
    }

    // ── Unlike ───────────────────────────────────────────────────────────────
    private void handleUnlike(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/auth/login"); return; }

        String favId = req.getPathInfo() != null
                ? req.getPathInfo().replaceFirst("/", "") : req.getParameter("id");

        var favDao = new FavoriteDAOImpl();
        try {
            Favorite fav = favDao.findById(Integer.parseInt(favId));
            if (fav != null && fav.getUser().getEmail().equals(user.getEmail()))
                favDao.delete(fav);
        } catch (Exception ignored) {}

        resp.sendRedirect(req.getContextPath() + "/video/favorites");
    }

    // ── Favorites ────────────────────────────────────────────────────────────
    private void handleFavorites(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login?ref="
                    + req.getContextPath() + "/video/favorites");
            return;
        }

        var favDao = new FavoriteDAOImpl();
        req.setAttribute("favorites", favDao.findByUser(user.getEmail()));
        req.setAttribute("view", "/video/favorites.jsp");
        req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
    }

    // ── Share ────────────────────────────────────────────────────────────────
    private void handleShare(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) { resp.sendRedirect(req.getContextPath() + "/auth/login"); return; }

        String videoId = req.getPathInfo() != null
                ? req.getPathInfo().replaceFirst("/", "") : req.getParameter("id");
        var videoDao = new VideoDAOImpl();
        Video video  = videoDao.findById(videoId);

        if ("GET".equalsIgnoreCase(req.getMethod())) {
            req.setAttribute("video", video);
            req.setAttribute("view", "/video/share.jsp");
            req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
        } else {
            String recipients = req.getParameter("recipients");
            new ShareDAOImpl().create(Share.builder()
                    .user(user).video(video)
                    .recipients(recipients).shareDate(new Date()).build());

            String link = req.getScheme() + "://" + req.getServerName()
                    + ":" + req.getServerPort()
                    + req.getContextPath() + "/video/detail/" + videoId;

            // TODO: MailUtil.send(recipients, "Xem tiểu phẩm: " + video.getTitle(), link);

            req.setAttribute("video", video);
            req.setAttribute("message", "Đã chia sẻ tới: " + recipients);
            req.setAttribute("link", link);
            req.setAttribute("view", "/video/share.jsp");
            req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
        }
    }
}
