package oe.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebHelper {
    static Map<Long, HttpServletRequest> requests = new HashMap<Long, HttpServletRequest>();
    static Map<Long, HttpServletResponse> responses = new HashMap<Long, HttpServletResponse>();
    public static void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var key = Thread.currentThread().threadId();
        requests.put(key, req);
        responses.put(key, resp);
    }
    public static void remove(){
        var key = Thread.currentThread().threadId();
        requests.remove(key);
        responses.remove(key);
    }
    public static HttpServletRequest getRequest(){
        var key = Thread.currentThread().threadId();
        return requests.get(key);
    }
    public static HttpServletResponse getResponse(){
        var key = Thread.currentThread().threadId();
        return responses.get(key);
    }
}
