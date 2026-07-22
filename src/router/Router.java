package router;

import handler.*;
import http.request.HttpRequest;
import http.response.*;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, RequestHandler> routes = new HashMap<>();

    public Router() {
        seedData();
    }

    public void seedData(){
        routes.put("/users",new UsersHandler());
        routes.put("/hello",new HelloHandler());
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

    public HttpResponse incomingRequest(HttpRequest request){
        if (routes.containsKey(request.getPath())){
            if (request.getPath().equals("/hello")){
                HelloHandler helloHandler = new HelloHandler();
                return helloHandler.handle(request);
            }else{
                UsersHandler usersHandler = new UsersHandler();
                return usersHandler.handle(request);
            }
        }else{
            return new HttpResponse(
                    HttpStatus.NOT_FOUND,
                    null,
                    null
            );
        }
    }

    private String createKey(String method, String path) {
        return method.toUpperCase() + " " + path;
    }
}