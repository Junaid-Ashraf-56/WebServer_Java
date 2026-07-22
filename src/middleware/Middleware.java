package middleware;

import http.request.HttpRequest;
import http.response.HttpResponse;

public interface Middleware {
    HttpResponse handle(HttpRequest request);
}
