package middleware;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Validation implements Middleware {

    private static final Set<String> SUPPORTED_METHODS =
            Set.of("GET", "POST");

    @Override
    public HttpResponse handle(HttpRequest request) {

        if (request.getMethod() == null ||
                !SUPPORTED_METHODS.contains(request.getMethod().toUpperCase())) {
            return error(
                    HttpStatus.BAD_REQUEST,
                    "{\"main.java.error\":\"Unsupported or missing HTTP method\"}"
            );
        }

        if (request.getPath() == null ||
                request.getPath().isBlank() ||
                !request.getPath().startsWith("/")) {
            return error(
                    HttpStatus.BAD_REQUEST,
                    "{\"main.java.error\":\"Invalid request path\"}"
            );
        }

        if (!"HTTP/1.1".equals(request.getHttpVersion())) {
            return error(
                    HttpStatus.BAD_REQUEST,
                    "{\"main.java.error\":\"Only HTTP/1.1 is supported\"}"
            );
        }

        if ("POST".equalsIgnoreCase(request.getMethod()) &&
                (request.getBody() == null || request.getBody().isBlank())) {
            return error(
                    HttpStatus.BAD_REQUEST,
                    "{\"main.java.error\":\"POST request body is required\"}"
            );
        }

        return null;
    }

    private HttpResponse error(HttpStatus status, String body) {
        Map<String, String> headers = new HashMap<>();

        headers.put("content-type", "application/json");
        headers.put("connection", "close");

        return new HttpResponse(
                status,
                headers,
                body
        );
    }
}