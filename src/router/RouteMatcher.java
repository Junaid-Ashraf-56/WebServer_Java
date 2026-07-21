package router;

import handler.HelloHandler;
import handler.UsersHandler;
import http.request.HttpRequest;
import http.response.HttpResponse;

public class RouteMatcher {
    public HttpResponse incomingRequest(HttpRequest request){
        if (request.getPath().equals("/hello")){
            HelloHandler helloHandler = new HelloHandler();
            return helloHandler.handle(request);
        }else {
            UsersHandler usersHandler = new UsersHandler();
            return usersHandler.handle(request);
        }
    }

}
