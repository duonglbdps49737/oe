package oe.Filter;

import java.io.IOException;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oe.helper.XHttp;

public interface HttpFilter extends Filter {
	default boolean preHandle(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		return true;
	}
	
	default void postHandle(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException{
	}
	
	@Override
	default void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		XHttp.add(request, response);
		
		var req = (HttpServletRequest)request;
		var resp = (HttpServletResponse)response;
		if(this.preHandle(req, resp)) {
			chain.doFilter(request, response);
		}
		this.postHandle(req, resp);
		
		XHttp.remove();
	}

	@Override
	default void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	default void destroy() {}
}