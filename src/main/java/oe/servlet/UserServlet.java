package oe.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.entity.User;
import oe.service.UserService;
import oe.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/user/index",
        "/user/create",
        "/user/update",
        "/user/delete/*",
        "/user/edit/*"
})
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var path = req.getPathInfo();
        var user = new User();
        if(path.contains("list")) {

        }else if(path.contains("create")) {

        }else if (path.contains("update")) {

        } else if (path.contains("edit")) {
            var email = req.getPathInfo().substring(1);
            user = userService.findById(email);
        }

        List<User> users = userService.findAll();
        req.setAttribute("form",new User());
        req.setAttribute("list", List.of());
        req.setAttribute("view","/user/index.jsp");
        req.getRequestDispatcher("layout/index.jsp").forward(req,resp);
    }
}
