package oe.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.dao.VideoDAOImpl;

@WebServlet({"/home/index", "/home/about", "/home/contact"})
public class HomeServlet extends HttpServlet {

	private static final int PAGE_SIZE = 6;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		var path = req.getServletPath();

		if (path.contains("index")) {
			var dao = new VideoDAOImpl();

			// Lấy trang hiện tại, mặc định 1
			int page = 1;
			String p = req.getParameter("page");
			if (p != null && !p.isBlank()) {
				try { page = Integer.parseInt(p); } catch (NumberFormatException e) { page = 1; }
			}

			long total = dao.countAll();
			int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
			if (totalPages == 0) totalPages = 1;
			if (page < 1) page = 1;
			if (page > totalPages) page = totalPages;

			req.setAttribute("videos", dao.findByPage(page, PAGE_SIZE));
			req.setAttribute("page", page);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("view", "/home/index.jsp");

		} else if (path.contains("about")) {
			req.setAttribute("view", "/home/about.jsp");
		} else if (path.contains("contact")) {
			req.setAttribute("view", "/home/contact.jsp");
		}

		req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
	}
}
