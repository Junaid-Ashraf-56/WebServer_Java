package main.java.middleware;

import main.java.http.request.HttpRequest;
import main.java.http.response.HttpResponse;

public interface Middleware {
    HttpResponse handle(HttpRequest request);
}
