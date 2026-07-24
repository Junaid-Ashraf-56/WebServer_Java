package main.java.error;

import main.java.http.response.HttpResponse;
import main.java.http.response.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {

    public HttpResponse handle(Exception exception) {

        if (exception instanceof IllegalArgumentException) {
            return buildResponse(
                    HttpStatus.BAD_REQUEST,
                    "{\"main.java.error\":\"" + exception.getMessage() + "\"}"
            );
        }

        if (exception instanceof IOException) {
            return buildResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "{\"main.java.error\":\"I/O Error\"}"
            );
        }

        if (exception instanceof NullPointerException) {
            return buildResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "{\"main.java.error\":\"Unexpected null value\"}"
            );
        }

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "{\"main.java.error\":\"Internal Server Error\"}"
        );
    }

    private HttpResponse buildResponse(
            HttpStatus status,
            String body
    ) {

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