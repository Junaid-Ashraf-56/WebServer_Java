package main.java.handler;

import main.java.http.request.HttpRequest;
import main.java.http.response.HttpResponse;

public interface RequestHandler {
    HttpResponse handle(HttpRequest request);
}
