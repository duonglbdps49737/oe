package oe.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import oe.entity.User;
import oe.service.UserService;
import oe.service.UserServiceImpl;

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
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			user.setFullname(req.getParameter("fullname"));
			user.setEnabled(Boolean.parseBoolean(req.getParameter("enabled")));
			user.setAdmin(Boolean.parseBoolean(req.getParameter("admin")));
            try {
                BeanUtils.populate(user, req.getParameterMap());
                userService.create(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                BeanUtils.populate(user, req.getParameterMap());
//                // Debug: kiểm tra xem email có được gán không
//                System.out.println("Create - Email: " + user.getEmail());
//                System.out.println("Create - Fullname: " + user.getFullname());
//
//                if (user.getEmail() == null || user.getEmail().isEmpty()) {
//                    System.out.println("ERROR: Email không được cấp!");
//                } else {
//                    userService.create(user);
//                    System.out.println("Create thành công!");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        } else if(path.contains("update")) {
            try {
                BeanUtils.populate(user, req.getParameterMap());
                userService.update(user);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if(path.contains("delete")) {
            var email = req.getPathInfo().substring(1);
            userService.deleteByEmail(email);
        }

        List<User> list = userService.findAll();

        req.setAttribute("form", user);
        req.setAttribute("list", list);

        req.setAttribute("view", "/user/index.jsp");
        req.getRequestDispatcher("/layout/index.jsp").forward(req, resp);
    }
}