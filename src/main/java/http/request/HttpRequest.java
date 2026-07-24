package main.java.http.request;

import java.util.Map;

public class HttpRequest {
    String method;
    String path;
    String httpVersion;
    Map<String,String> header;
    Map<String,String> queryParameter;
    String body;

    public HttpRequest(String method, String path, String httpVersion, Map<String, String> header, Map<String, String> queryParameter, String body) {
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
        this.header = header;
        this.queryParameter = queryParameter;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }
    public String getPath() {
        return path;
    }
    public String getHttpVersion() {
        return httpVersion;
    }
    public Map<String, String> getHeader() {return header;}
    public Map<String, String> getQueryParameter() {
        return queryParameter;
    }
    public String getBody() {
        return body;
    }

}
