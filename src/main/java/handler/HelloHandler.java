package handler;

import data.SeedData;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HelloHandler implements RequestHandler{

    @Override
    public HttpResponse handle(HttpRequest request) {
        Map<String,String> header = new HashMap<>();

        if (request.getMethod().equalsIgnoreCase("GET")){
            String body = SeedData.HELLO_MESSAGE;
            header.put("content-type","text/plain");
            return new HttpResponse(HttpStatus.OK,header,body);
        }else {
            String body = SeedData.USER_CREATED;
            header.put("content-type","text/json");
            return new HttpResponse(HttpStatus.CREATED,header,body);
        }
    }
}
