package main.java.router;

import main.java.handler.RequestHandler;

public class Route {
    String httpMethod;
    String path;
    RequestHandler requestHandler;

    public Route(String httpMethod, String path, RequestHandler requestHandler) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.requestHandler = requestHandler;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }
}
