package handler;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.HttpStatus;

public class UsersHandler implements RequestHandler{

    @Override
    public HttpResponse handle(HttpRequest request) {
        if (request.getMethod().equalsIgnoreCase("GET")){
            return new HttpResponse(
                    HttpStatus.OK,
                    request.getHeader(),
                    "Hello from the user"
            );
        }else {
            return new HttpResponse(
                    HttpStatus.CREATED,
                    request.getHeader(),
                    request.getBody()
            );
        }
    }
}
//Example:
//
//Request:
//
//Accept: application/json
//User-Agent: Chrome
//Host: localhost
//
//Response:
//
//Content-Type: text/plain
//Content-Length: 22
//Connection: close
//
//So eventually your handlers should create a new header map for the response instead of copying the request headers.