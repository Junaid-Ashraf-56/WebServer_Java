package middleware;

import http.request.HttpRequest;
import http.response.HttpResponse;

public class Logging implements Middleware {

    @Override
    public HttpResponse handle(HttpRequest request) {
        System.out.println("========== REQUEST ==========");
        System.out.println("Method: " + request.getMethod());
        System.out.println("Path: " + request.getPath());
        System.out.println("Version: " + request.getHttpVersion());
        System.out.println("Host: " + request.getHeader().get("host"));
        System.out.println("User-Agent: " + request.getHeader().get("user-agent"));
        System.out.println("Content-Type: " + request.getHeader().get("content-type"));
        System.out.println("Body: " + request.getBody());
        System.out.println("=============================");

        return null;
    }
}