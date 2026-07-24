package main.java.handler;

import main.java.data.SeedData;
import main.java.http.request.HttpRequest;
import main.java.http.response.HttpResponse;
import main.java.http.response.HttpStatus;

import java.util.*;

public class UsersHandler implements RequestHandler{

    @Override
    public HttpResponse handle(HttpRequest request) {
        Map<String,String> header = new HashMap<>();

        if (request.getMethod().equalsIgnoreCase("GET")){
            List<String> list = SeedData.USERS;
            String body = list.toString();
            header.put("content-type","text/plain");
            return new HttpResponse(HttpStatus.OK,header,body);
        }else {
            String body = SeedData.USER_CREATED;
            header.put("content-type","text/json");
            return new HttpResponse(HttpStatus.CREATED,header,body);
        }
    }
}
