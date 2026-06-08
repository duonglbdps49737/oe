package oe.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.helper.WebHelper;

import java.io.IOException;

@WebFilter("/*")
public class AppFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Before.doFilter");
        var req  = (HttpServletRequest) servletRequest;
        var res  = (HttpServletResponse) servletResponse;
        WebHelper.add(req, res);
        filterChain.doFilter(servletRequest, servletResponse);
        WebHelper.remove();
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        System.out.println("After.doFilter");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
