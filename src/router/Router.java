package router;

import handler.HelloHandler;
import handler.RequestHandler;
import handler.UsersHandler;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, RequestHandler> routes = new HashMap<>();

    public Router() {
        seedData();
    }

    private void seedData() {
        routes.put("GET /hello", new HelloHandler());
        routes.put("GET /users", new UsersHandler());
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

    private String createKey(String method, String path) {
        return method.toUpperCase() + " " + path;
    }
}