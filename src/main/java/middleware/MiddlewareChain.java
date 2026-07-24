package middleware;

import http.request.HttpRequest;
import http.response.HttpResponse;
import router.Router;

import java.util.List;

public class MiddlewareChain {

    private final List<Middleware> middlewareList;
    private final Router router;

    public MiddlewareChain(Router router) {
        this.router = router;
        this.middlewareList = List.of(
                new Logging(),
                new Validation()
        );
    }

    public HttpResponse handle(HttpRequest request) {

        for (Middleware middleware : middlewareList) {
            HttpResponse errorResponse = middleware.handle(request);

            if (errorResponse != null) {
                return errorResponse;
            }
        }

        return router.incomingRequest(request);
    }
}