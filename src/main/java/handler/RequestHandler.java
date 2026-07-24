package handler;

import http.request.HttpRequest;
import http.response.HttpResponse;

public interface RequestHandler {
    HttpResponse handle(HttpRequest request);
}
