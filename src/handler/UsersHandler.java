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
