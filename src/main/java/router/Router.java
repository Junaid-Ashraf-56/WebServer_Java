package router;

import handler.*;
import http.request.HttpRequest;
import http.response.*;
import java.util.*;

public class Router {

    private final Map<String, RequestHandler> routes = new HashMap<>();

    public Router() {
        seedData();
    }

    public void seedData(){
        routes.put("GET /users",new UsersHandler());
        routes.put("POST /users",new UsersHandler());
        routes.put("GET /hello",new HelloHandler());
        routes.put("POST /hello",new HelloHandler());
    }
    public boolean register(
            String method,
            String path,
            RequestHandler handler
    ) {
        String key = createKey(method, path);

        if (routes.containsKey(key)) {
            return false;
        }

        routes.put(key, handler);
        return true;
    }

    public boolean unregister(String method, String path) {
        String key = createKey(method, path);
        return routes.remove(key) != null;
    }

    public RequestHandler find(String method, String path) {
        String key = createKey(method, path);
        return routes.get(key);
    }

    public HttpResponse incomingRequest(HttpRequest request) {
        String key = createKey(
                request.getMethod(),
                request.getPath()
        );

        RequestHandler handler = routes.get(key);

        if (handler == null) {
            Map<String, String> headers = new HashMap<>();
            headers.put("content-type", "application/json");
            headers.put("connection", "close");

            return new HttpResponse(
                    HttpStatus.NOT_FOUND,
                    headers,
                    "{\"main.java.error\":\"Route not found\"}"
            );
        }

        return handler.handle(request);
    }

    private String createKey(String method, String path) {
        return method.toUpperCase() + " " + path;
    }
}