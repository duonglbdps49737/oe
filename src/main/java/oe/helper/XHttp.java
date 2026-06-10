package oe.helper;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class XHttp{
	private static Map<Long, ServletRequest> requests = new HashMap<>();
	private static Map<Long, ServletResponse> responses = new HashMap<>();
	/**
	 * Bổ sung request/response
	 */
	public static void add(ServletRequest request, ServletResponse response){
		var key = Thread.currentThread().threadId();
		requests.put(key, request);
		responses.put(key, response);
	}
	/**
	 * Xóa request/response hiện tại
	 */
	public static void remove() {
		var key = Thread.currentThread().threadId();
		requests.remove(key);
		responses.remove(key);
	}
	/**
	 * Lấy request hiện tại
	 */
	public static HttpServletRequest getRequest() {
		var key = Thread.currentThread().threadId();
		return (HttpServletRequest) requests.get(key);
	}
	/**
	 * Lấy response hiện tại
	 */
	public static HttpServletResponse getResponse() {
		var key = Thread.currentThread().threadId();
		return (HttpServletResponse) responses.get(key);
	}
	/**
	 * Lấy session hiện tại
	 */
	public static HttpSession getSession() {
		return XHttp.getRequest().getSession();
	}
	/**
	 * Lấy application hiện tại
	 */
	public static ServletContext getApplication() {
		return XHttp.getRequest().getServletContext();
	}
	/**
	 * Chuyển tiếp servlet/jsp khác
	 * @param attrs là các request attributes cần truyền
	 */
	public static void forward(String path, Map<String, Object> attrs) throws ServletException, IOException {
		var req = XHttp.getRequest();
		attrs.forEach((key, value) -> req.setAttribute(key, value));
		req.getRequestDispatcher(path).forward(req, getResponse());
	}
	public static void forward(String path) throws ServletException, IOException {
		XHttp.forward(path, Map.of());
	}
	/**
	 * Bao hàm servlet/jsp khác
	 * @param attrs là các request attributes cần truyền
	 */
	public static void include(String path, Map<String, Object> attrs) throws ServletException, IOException {
		var req = XHttp.getRequest();
		attrs.forEach((key, value) -> req.setAttribute(key, value));
		req.getRequestDispatcher(path).forward(req, getResponse());
	}
	public static void include(String path) throws ServletException, IOException {
		XHttp.include(path, Map.of());
	}
	/**
	 * Chuyển hướng sang địa chỉ url khác
	 * @param params các tham số đính kèm
	 */
	public static void redirect(String uri, Map<String, String> params) throws IOException {
		var resp = XHttp.getResponse();
		uri = XHttp.getRequest().getContextPath() + uri +  "?";
		for(var name : params.keySet()) {
			var value = URLEncoder.encode(params.get(name), "utf-8");
			uri += String.format("%s=%s", name, value);
		}
		resp.sendRedirect(uri);
	}
	public static void redirect(String url) throws IOException {
		XHttp.redirect(url, Map.of());
	}
	/**
	 * Đọc giá trị tham số
	 */
	public static String getParam(String name) {
		return XHttp.getRequest().getParameter(name);
	}
	public static String getParam(String name, String defval) {
		var value = XHttp.getParam(name);
		return value == null ? defval : value;
	}
	/**
	 * Lưu trữ file upload
	 * @param folter thư mục lưu file
	 */
	public static File getFile(String name, String folter) throws IOException, ServletException {
		var part = XHttp.getRequest().getPart(name);
		var filename = part.getSubmittedFileName();
		filename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
		var file = new File (XHttp.getApplication().getRealPath(folter), filename);
		part.write(file.getAbsolutePath());
		return file;
	}
	/**
	 * Đọc tham số vào thuộc tính bean
	 */
	public static <T> T getBean(Class<T> beanClass) {
		try {
			DateConverter dateConverter = new DateConverter();
	        dateConverter.setPattern("MM/dd/yyyy");
	        ConvertUtils.register(dateConverter, Date.class);
	        
			var bean = beanClass.getDeclaredConstructor().newInstance();
			BeanUtils.populate(bean, getRequest().getParameterMap());
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	/**
	 * Đọc cookie từ client
	 */
	public static Cookie getCookie(String name) {
		var cookies = XHttp.getRequest().getCookies();
		if(cookies != null) {
			for(var cookie: cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					var decodedBytes = Base64.getDecoder().decode(cookie.getValue());
					cookie.setValue(new String(decodedBytes));
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * Tạo và gửi cookie về client
	 */
	public static Cookie addCookie(String name, String value, int maxAge) {
		var encodedValue = Base64.getEncoder().encodeToString(value.getBytes());
		var cookie = new Cookie(name, encodedValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		XHttp.getResponse().addCookie(cookie);
		return cookie;
	}
	/**
	 * Xóa cookie
	 */
	public static void removeCookie(String name) {
		XHttp.addCookie(name, "", 0);
	}
	
	/**
	 * Get/Set/Remove attribute from request scope
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRequest(String name) {
		return (T) XHttp.getRequest().getAttribute(name);
	}
	public static void setRequest(String name, Object value) {
		XHttp.getRequest().setAttribute(name, value);
	}
	public static void removeRequest(String name) {
		XHttp.getRequest().removeAttribute(name);
	}
	
	/**
	 * Get/Set/Remove attribute from session scope
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSession(String name) {
		return (T) XHttp.getSession().getAttribute(name);
	}
	public static void setSession(String name, Object value) {
		XHttp.getSession().setAttribute(name, value);
	}
	public static void removeSession(String name) {
		XHttp.getSession().removeAttribute(name);
	}
	
	/**
	 * Get/Set/Remove attribute from application scope
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getApplication(String name) {
		return (T) XHttp.getApplication().getAttribute(name);
	}
	public static void setApplication(String name, Object value) {
		XHttp.getApplication().setAttribute(name, value);
	}
	public static void removeApplication(String name) {
		XHttp.getApplication().removeAttribute(name);
	}
}